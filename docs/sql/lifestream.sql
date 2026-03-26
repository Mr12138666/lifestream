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

CREATE TABLE `t_user` (
                          `id` BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
                          `username` VARCHAR (32) NOT NULL COMMENT '用户名',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB COMMENT = '用户测试表';
