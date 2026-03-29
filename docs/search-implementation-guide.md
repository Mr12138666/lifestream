# 轻量搜索模块实施手册（不引入 Elasticsearch）

> 目标：在当前 `lifestream` 项目内，复用既有模块，分阶段实现“笔记搜索 + 用户搜索”，并可平滑演进。

## 1. 目标与范围

### 1.1 目标

- 支持笔记搜索（标题/内容）
- 支持用户搜索（昵称/简介）
- 不引入 Elasticsearch
- 尽量复用现有服务：`note`、`user`、`count`、`user-relation`、`data-align`、`gateway`

### 1.2 非目标（MVP 阶段）

- 不做复杂中文分词引擎
- 不做拼写纠错、同义词召回
- 不做复杂个性化推荐，仅提供轻量加权

## 2. 模块边界与职责

### 2.1 推荐服务边界

建议新增独立服务 `lifestream-search`（边界清晰，便于后续扩展）。

如果你希望更快落地，也可先放在 `lifestream-note-biz`（后续再拆分）。

### 2.2 依赖关系

- 写链路（索引更新）
  - 来源：`note`、`user` 的业务变更事件
  - 中间件：复用现有 RocketMQ
- 读链路（搜索查询）
  - 主数据：搜索索引表（MySQL）
  - 补充特征：`count`（热度）/ `user-relation`（社交）
- 一致性
  - `data-align` 周期性补偿

## 3. 数据模型设计（DDL 建议）

> 说明：MVP 先用“索引宽表 + 普通索引 + LIKE 前缀/模糊”，先可用，后优化。

```sql
CREATE TABLE IF NOT EXISTS t_search_note_index (
    id BIGINT PRIMARY KEY COMMENT 'note_id',
    author_id BIGINT NOT NULL,
    author_nickname VARCHAR(64) NOT NULL DEFAULT '',
    title VARCHAR(200) NOT NULL DEFAULT '',
    content_plain TEXT,
    status TINYINT NOT NULL DEFAULT 0 COMMENT '与 note 状态对齐',
    visible TINYINT NOT NULL DEFAULT 0 COMMENT '可见性',
    publish_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL,
    like_count INT NOT NULL DEFAULT 0,
    collect_count INT NOT NULL DEFAULT 0,
    comment_count INT NOT NULL DEFAULT 0,
    KEY idx_update_time (update_time),
    KEY idx_author_id (author_id),
    KEY idx_status_visible_publish (status, visible, publish_time),
    KEY idx_title_prefix (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_search_user_index (
    id BIGINT PRIMARY KEY COMMENT 'user_id',
    nickname VARCHAR(64) NOT NULL DEFAULT '',
    bio VARCHAR(255) NOT NULL DEFAULT '',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '用户状态',
    follower_count INT NOT NULL DEFAULT 0,
    note_count INT NOT NULL DEFAULT 0,
    update_time DATETIME NOT NULL,
    KEY idx_update_time (update_time),
    KEY idx_status (status),
    KEY idx_nickname_prefix (nickname)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 3.1 设计要点

- `id` 与业务主键一致，方便 upsert
- 索引表存可排序的统计字段，避免查询阶段跨服务聚合过重
- 保留 `update_time`，用于增量补偿扫描

## 4. 索引更新链路（写路径）

### 4.1 事件源

- 笔记：发布、更新、删除、可见性变更
- 用户：昵称/简介更新、状态变更
- 计数：点赞、收藏、评论等变化

### 4.2 消费与落库

- 搜索服务消费 MQ 事件后做 `upsert` / `delete`
- 写入策略：幂等（`INSERT ... ON DUPLICATE KEY UPDATE`）
- 字段缺失时按需回源（调用 `note` / `user` API）

### 4.3 一致性兜底

- `data-align` 定时任务扫描 `update_time` 做补偿
- 对比逻辑：业务表 vs 索引表，发现差异即回填
- 建议每天低峰时段执行一次全量校验

## 5. 查询与排序流程（读路径）

### 5.1 查询流程

1. 参数校验（关键词、分页）
2. 关键词标准化（trim、小写）
3. 召回（标题/昵称优先；内容次之）
4. 过滤（状态、可见性）
5. 打分排序
6. 分页返回

### 5.2 MVP 召回策略

- 笔记
  - 标题前缀：`title LIKE 'kw%'`
  - 标题/内容模糊：`title LIKE '%kw%' OR content_plain LIKE '%kw%'`
- 用户
  - 昵称前缀：`nickname LIKE 'kw%'`
  - 昵称/简介模糊：`nickname LIKE '%kw%' OR bio LIKE '%kw%'`

> 性能建议：优先前缀命中，模糊匹配仅在必要时触发，且限制页深。

### 5.3 排序建议（可参数化）

笔记得分：

`score = textScore * 0.6 + freshnessScore * 0.2 + heatScore * 0.2`

- `textScore`：标题命中 > 内容命中
- `freshnessScore`：发布时间越近越高
- `heatScore`：点赞/收藏/评论加权

用户得分：

`score = textScore * 0.7 + freshnessScore * 0.1 + popularityScore * 0.2`

- `popularityScore`：粉丝数、笔记数

## 6. API 设计（建议）

### 6.1 笔记搜索

- `POST /search/note`
- 请求

```json
{
  "keyword": "mysql",
  "pageNo": 1,
  "pageSize": 10,
  "sortType": "comprehensive"
}
```

### 6.2 用户搜索

- `POST /search/user`
- 请求

```json
{
  "keyword": "alice",
  "pageNo": 1,
  "pageSize": 10
}
```

### 6.3 返回结构（统一）

```json
{
  "success": true,
  "data": {
    "total": 123,
    "list": [
      {
        "id": 1001,
        "title": "...",
        "score": 88.6
      }
    ]
  }
}
```

## 7. 分阶段实施步骤（按顺序）

### 阶段 A：基础设施

- 新建索引表：`t_search_note_index`、`t_search_user_index`
- 新建 mapper + xml + DO
- 新建搜索服务骨架（controller/service/repository）

### 阶段 B：写链路

- 接入 note/user 事件消费
- 完成索引 upsert/delete
- 联调幂等与异常重试

### 阶段 C：读链路

- 实现查询 SQL（前缀优先 + 模糊兜底）
- 实现打分与排序
- 打通 API + 网关路由

### 阶段 D：一致性与灰度

- 增加 `data-align` 补偿任务
- 灰度开关：支持旧方案回退
- 压测并调参（pageSize、模糊查询阈值）

## 8. 验证清单

### 8.1 功能验证

- 发布笔记后可搜索到
- 更新标题后结果实时变化
- 删除/仅自己可见后对外不可检索
- 修改昵称后用户搜索可命中

### 8.2 一致性验证

- 人工制造漏消息，补偿任务可修复
- 重复消费不产生脏数据（幂等）

### 8.3 性能验证

- 关键词高频查询 p95/p99
- 深分页限制生效
- 模糊查询占比与耗时可观测

## 9. 回滚与开关策略

- 功能开关：`search.enabled`
- 灰度开关：`search.useIndex`（可切回旧查询逻辑）
- 回滚动作
  - 停消费（防止继续写入）
  - 网关路由切回旧接口
  - 保留索引表，不立即删除（便于快速恢复）

## 10. 第一周落地建议（日计划）

- Day1: 建表 + mapper + 查询 demo SQL
- Day2: 笔记索引消费链路
- Day3: 用户索引消费链路
- Day4: 搜索 API + 打分
- Day5: 补偿任务 + 联调 + 灰度

---

如果你下一步要开始编码，建议先从“阶段 A + 阶段 B（笔记）”开始，先把一条完整链路跑通，再扩用户搜索。
