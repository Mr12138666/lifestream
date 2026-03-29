-- ==========================================
-- lifestream 数据库初始化脚本
-- 创建时间: 2026-03-17
-- 字符集: utf8mb4
-- 校验集: utf8mb4_unicode_ci
-- ==========================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS lifestream
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE lifestream;

create table t_channel
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    name        varchar(12)                        not null comment '频道名称',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    is_deleted  bit      default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)'
)
    comment '频道表';

create table t_channel_topic_rel
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    channel_id  bigint unsigned                    not null comment '频道ID',
    topic_id    bigint unsigned                    not null comment '话题ID',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
    comment '频道-话题关联表';

create table t_data_align_fans_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：粉丝数';

create table t_data_align_fans_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：粉丝数';

create table t_data_align_fans_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：粉丝数';

create table t_data_align_following_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：关注数';

create table t_data_align_following_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：关注数';

create table t_data_align_following_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：关注数';

create table t_data_align_note_collect_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id bigint unsigned not null comment '笔记ID',
    constraint uk_note_id
        unique (note_id)
)
    comment '数据对齐日增量表：笔记获得收藏数';

create table t_data_align_note_collect_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id bigint unsigned not null comment '笔记ID',
    constraint uk_note_id
        unique (note_id)
)
    comment '数据对齐日增量表：笔记获得收藏数';

create table t_data_align_note_collect_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id bigint unsigned not null comment '笔记ID',
    constraint uk_note_id
        unique (note_id)
)
    comment '数据对齐日增量表：笔记获得收藏数';

create table t_data_align_note_like_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id bigint unsigned not null comment '笔记ID',
    constraint uk_note_id
        unique (note_id)
)
    comment '数据对齐日增量表：笔记获得点赞数';

create table t_data_align_note_like_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id bigint unsigned not null comment '笔记ID',
    constraint uk_note_id
        unique (note_id)
)
    comment '数据对齐日增量表：笔记获得点赞数';

create table t_data_align_note_like_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id bigint unsigned not null comment '笔记ID',
    constraint uk_note_id
        unique (note_id)
)
    comment '数据对齐日增量表：笔记获得点赞数';

create table t_data_align_note_publish_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户发布笔记数';

create table t_data_align_note_publish_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户发布笔记数';

create table t_data_align_note_publish_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户发布笔记数';

create table t_data_align_user_collect_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户获得收藏数';

create table t_data_align_user_collect_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户获得收藏数';

create table t_data_align_user_collect_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户获得收藏数';

create table t_data_align_user_like_count_temp_20260329_0
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户获得点赞数';

create table t_data_align_user_like_count_temp_20260329_1
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户获得点赞数';

create table t_data_align_user_like_count_temp_20260329_2
(
    id      bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id bigint unsigned not null comment '用户ID',
    constraint uk_user_id
        unique (user_id)
)
    comment '数据对齐日增量表：用户获得点赞数';

create table t_fans
(
    id           bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id      bigint unsigned                    not null comment '用户ID',
    fans_user_id bigint unsigned                    not null comment '粉丝的用户ID',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint uk_user_id_fans_user_id
        unique (user_id, fans_user_id)
)
    comment '用户粉丝表';

create index idx_user_id
    on t_fans (user_id);

create table t_following
(
    id                bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id           bigint unsigned                    not null comment '用户ID',
    following_user_id bigint unsigned                    not null comment '关注的用户ID',
    create_time       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint uk_user_id_following_user_id
        unique (user_id, following_user_id)
)
    comment '用户关注表';

create index idx_user_id
    on t_following (user_id);

create table t_note
(
    id               bigint unsigned                       not null comment '主键ID'
        primary key,
    title            varchar(64)                           not null comment '标题',
    is_content_empty bit         default b'0'              not null comment '内容是否为空(0：不为空 1：空)',
    creator_id       bigint unsigned                       not null comment '发布者ID',
    topic_id         bigint unsigned                       null comment '话题ID',
    topic_name       varchar(32) default ''                null comment '话题名称',
    is_top           bit         default b'0'              not null comment '是否置顶(0：未置顶 1：置顶)',
    type             tinyint     default 0                 null comment '类型(0：图文 1：视频)',
    img_uris         varchar(660)                          null comment '笔记图片链接(逗号隔开)',
    video_uri        varchar(120)                          null comment '视频链接',
    visible          tinyint     default 0                 null comment '可见范围(0：公开,所有人可见 1：仅对自己可见)',
    create_time      datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time      datetime    default CURRENT_TIMESTAMP not null comment '更新时间',
    status           tinyint     default 0                 not null comment '状态(0：待审核 1：正常展示 2：被删除(逻辑删除) 3：被下架)',
    content_uuid     varchar(36) default ''                null comment '笔记内容UUID'
)
    comment '笔记表';

create index idx_creator_id
    on t_note (creator_id);

create index idx_status
    on t_note (status);

create index idx_topic_id
    on t_note (topic_id);

create table t_note_collection
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id     bigint                             not null comment '用户ID',
    note_id     bigint                             not null comment '笔记ID',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    status      tinyint  default 0                 not null comment '收藏状态(0：取消收藏 1：收藏)',
    constraint uk_user_id_note_id
        unique (user_id, note_id)
)
    comment '笔记收藏表';

create table t_note_count
(
    id            bigint unsigned auto_increment comment '主键ID'
        primary key,
    note_id       bigint unsigned  not null comment '笔记ID',
    like_total    bigint default 0 null comment '获得点赞总数',
    collect_total bigint default 0 null comment '获得收藏总数',
    comment_total bigint default 0 null comment '被评论总数',
    constraint uk_note_id
        unique (note_id)
)
    comment '笔记计数表';

create table t_note_like
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id     bigint                             not null comment '用户ID',
    note_id     bigint                             not null comment '笔记ID',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    status      tinyint  default 0                 not null comment '点赞状态(0：取消点赞 1：点赞)',
    constraint uk_user_id_note_id
        unique (user_id, note_id)
)
    comment '笔记点赞表';

create table t_permission
(
    id             bigint unsigned auto_increment comment '主键ID'
        primary key,
    parent_id      bigint unsigned  default '0'               not null comment '父ID',
    name           varchar(16)                                not null comment '权限名称',
    type           tinyint unsigned                           not null comment '类型(1：目录 2：菜单 3：按钮)',
    menu_url       varchar(32)      default ''                not null comment '菜单路由',
    menu_icon      varchar(255)     default ''                not null comment '菜单图标',
    sort           int unsigned     default '0'               not null comment '管理系统中的显示顺序',
    permission_key varchar(64)                                not null comment '权限标识',
    status         tinyint unsigned default '0'               not null comment '状态(0：启用；1：禁用)',
    create_time    datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime         default CURRENT_TIMESTAMP not null comment '更新时间',
    is_deleted     bit              default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)'
)
    comment '权限表';

create table t_role
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    role_name   varchar(32)                            not null comment '角色名',
    role_key    varchar(32)                            not null comment '角色唯一标识',
    status      tinyint      default 0                 not null comment '状态(0：启用 1：禁用)',
    sort        int unsigned default '0'               not null comment '管理系统中的显示顺序',
    remark      varchar(255)                           null comment '备注',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP not null comment '最后一次更新时间',
    is_deleted  bit          default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)',
    constraint uk_role_key
        unique (role_key)
)
    comment '角色表';

create table t_role_permission_rel
(
    id            bigint unsigned auto_increment comment '主键ID'
        primary key,
    role_id       bigint unsigned                    not null comment '角色ID',
    permission_id bigint unsigned                    not null comment '权限ID',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    is_deleted    bit      default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)'
)
    comment '用户权限表';

create table t_topic
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    name        varchar(12)                        not null comment '话题名称',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    is_deleted  bit      default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)'
)
    comment '话题表';

create table t_user
(
    id             bigint unsigned auto_increment comment '主键ID'
        primary key,
    lifestream_id  varchar(15)                        not null comment '时光溪号(唯一凭证)',
    password       varchar(64)                        null comment '密码',
    nickname       varchar(24)                        not null comment '昵称',
    avatar         varchar(120)                       null comment '头像',
    birthday       date                               null comment '生日',
    background_img varchar(120)                       null comment '背景图',
    phone          varchar(11)                        null comment '手机号',
    mail           varchar(150)                       not null comment '邮箱',
    sex            tinyint  default 0                 null comment '性别(0：女 1：男)',
    status         tinyint  default 0                 not null comment '状态(0：启用 1：禁用)',
    introduction   varchar(100)                       null comment '个人简介',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    is_deleted     bit      default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)',
    constraint uk_lifestream_id
        unique (lifestream_id),
    constraint uk_mail
        unique (mail),
    constraint uk_phone
        unique (phone)
)
    comment '用户表';

create table t_user_count
(
    id              bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id         bigint unsigned  not null comment '用户ID',
    fans_total      bigint default 0 null comment '粉丝总数',
    following_total bigint default 0 null comment '关注总数',
    note_total      bigint default 0 null comment '发布笔记总数',
    like_total      bigint default 0 null comment '获得点赞总数',
    collect_total   bigint default 0 null comment '获得收藏总数',
    constraint uk_user_id
        unique (user_id)
)
    comment '用户计数表';

create table t_user_role_rel
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id     bigint unsigned                    not null comment '用户ID',
    role_id     bigint unsigned                    not null comment '角色ID',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    is_deleted  bit      default b'0'              not null comment '逻辑删除(0：未删除 1：已删除)'
)
    comment '用户角色表';

CREATE TABLE `t_comment`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `note_id`          bigint(20) unsigned NOT NULL COMMENT '关联的笔记ID',
    `user_id`          bigint(20) unsigned NOT NULL COMMENT '发布者用户ID',
    `content_uuid`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '评论内容UUID',
    `is_content_empty` bit(1)              NOT NULL                                 DEFAULT b'0' COMMENT '内容是否为空(0：不为空 1：为空)',
    `image_url`        varchar(60)         NOT NULL                                 DEFAULT '' COMMENT '评论附加图片URL',
    `level`            tinyint(2)          NOT NULL                                 DEFAULT '1' COMMENT '级别(1：一级评论 2：二级评论)',
    `reply_total`      bigint(20) unsigned                                          DEFAULT 0 COMMENT '评论被回复次数，仅一级评论需要',
    `like_total`       bigint(20)                                                   DEFAULT 0 COMMENT '评论被点赞次数',
    `parent_id`        bigint(20) unsigned                                          DEFAULT 0 COMMENT '父ID (若是对笔记的评论，则此字段存储笔记ID; 若是二级评论，则此字段存储一级评论的ID)',
    `reply_comment_id` bigint(20) unsigned                                          DEFAULT 0 COMMENT '回复哪个的评论 (0表示是对笔记的评论，若是对他人评论的回复，则存储回复评论的ID)',
    `reply_user_id`    bigint(20) unsigned                                          DEFAULT 0 COMMENT '回复的哪个用户, 存储用户ID',
    `is_top`           tinyint(2)          NOT NULL                                 DEFAULT '0' COMMENT '是否置顶(0：不置顶 1：置顶)',
    `create_time`      datetime            NOT NULL                                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime            NOT NULL                                 DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_note_id` (`note_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_parent_id` (`parent_id`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_reply_comment_id` (`reply_comment_id`) USING BTREE,
    KEY `idx_reply_user_id` (`reply_user_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '评论表';

CREATE TABLE `t_comment_like` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `comment_id` bigint NOT NULL COMMENT '评论ID',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_user_id_comment_id` (`user_id`,`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论点赞表';


CREATE TABLE `t_comment` (
                             `id` bigint (20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `note_id` bigint (20) unsigned NOT NULL COMMENT '关联的笔记ID',
                             `user_id` bigint (20) unsigned NOT NULL COMMENT '发布者用户ID',
                             `content_uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '评论内容UUID',
                             `is_content_empty` bit(1) NOT NULL DEFAULT b'0' COMMENT '内容是否为空(0：不为空 1：为空)',
                             `image_url` varchar(60) NOT NULL DEFAULT '' COMMENT '评论附加图片URL',
                             `level` tinyint (2) NOT NULL DEFAULT '1' COMMENT '级别(1：一级评论 2：二级评论)',
                             `reply_total` bigint (20) unsigned DEFAULT 0 COMMENT '评论被回复次数，仅一级评论需要',
                             `like_total` bigint (20) DEFAULT 0 COMMENT '评论被点赞次数',
                             `parent_id` bigint (20) unsigned DEFAULT 0 COMMENT '父ID (若是对笔记的评论，则此字段存储笔记ID; 若是二级评论，则此字段存储一级评论的ID)',
                             `reply_comment_id` bigint (20) unsigned DEFAULT 0 COMMENT '回复哪个的评论 (0表示是对笔记的评论，若是对他人评论的回复，则存储回复评论的ID)',
                             `reply_user_id` bigint (20) unsigned DEFAULT 0 COMMENT '回复的哪个用户, 存储用户ID',
                             `is_top` tinyint (2) NOT NULL DEFAULT '0' COMMENT '是否置顶(0：不置顶 1：置顶)',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `idx_note_id` (`note_id`) USING BTREE,
                             KEY `idx_user_id` (`user_id`) USING BTREE,
                             KEY `idx_parent_id` (`parent_id`) USING BTREE,
                             KEY `idx_create_time` (`create_time`) USING BTREE,
                             KEY `idx_reply_comment_id` (`reply_comment_id`) USING BTREE,
                             KEY `idx_reply_user_id` (`reply_user_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '评论表';
