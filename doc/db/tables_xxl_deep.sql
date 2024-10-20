#
# XXL-DEEP v1.0.0-SNAPSHOT
# Copyright (c) 2015-present, xuxueli.

CREATE database if NOT EXISTS `xxl_deep` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `xxl_deep`;

SET NAMES utf8mb4;

## —————————————————————— for org + rbac ——————————————————
CREATE TABLE `xxl_deep_org` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组织ID',
    `parent_id` int(11) NOT NULL COMMENT '父组织ID',
    `name` varchar(50) NOT NULL COMMENT '名称',
    `order` int(11) NOT NULL COMMENT '顺序',
    `status` tinyint(4) NOT NULL COMMENT '状态：0-正常、1-禁用',
    `add_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_deep_user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(50) NOT NULL COMMENT '账号',
    `password` varchar(50) NOT NULL COMMENT '密码',
    `user_token` varchar(50) DEFAULT NULL COMMENT '登录token',
    `status` tinyint(4) NOT NULL COMMENT '状态：0-正常、1-禁用',
    `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
    `add_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_deep_role` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name` varchar(50) NOT NULL,
    `order` int(11) NOT NULL COMMENT '顺序',
    `add_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_deep_resource` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
    `parent_id` int(11) NOT NULL COMMENT '父节点ID',
    `name` varchar(50) NOT NULL COMMENT '名称',
    `type` tinyint(4) NOT NULL COMMENT '类型：0-菜单、1-按钮',
    `permission` varchar(50) DEFAULT NULL COMMENT '权限标识',
    `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
    `icon` varchar(50) DEFAULT NULL COMMENT '资源icon',
    `order` int(11) NOT NULL COMMENT '顺序',
    `status` tinyint(4) NOT NULL COMMENT '状态：0-正常、1-禁用',
    `add_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_deep_user_role` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    `add_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_deep_role_res` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `role_id` int(11) NOT NULL,
    `res_id` int(11) NOT NULL,
    `add_time` datetime NOT NULL COMMENT '更新时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `xxl_deep_user` (`id`, `username`, `password`, `user_token`, `status`, `real_name`, `add_time`, `update_time`)
VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', 0 , '吴彦祖', now(), now()),
       (2, 'user', 'e10adc3949ba59abbe56e057f20f883e', '', 0 , '张三', now(), now());
INSERT INTO `xxl_deep_role` (`id`, `name`, `order`, `add_time`, `update_time`)
VALUES (1, '管理员', 1, now(), now()),
       ( 2, '普通用户', 2, now(), now());
INSERT INTO `xxl_deep_user_role` (`id`, `user_id`, `role_id`, `add_time`, `update_time`)
VALUES (1, 1, 1, now(), now()),
       (2, 2, 2, now(), now());
INSERT INTO `xxl_deep_resource` (`id`, `parent_id`, `name`, `type`, `permission`, `url`, `icon`, `order`, `status`, `add_time`, `update_time`)
VALUES (1, 0,'首页', 1, 'index', '/index', 'fa fa-home', 200, 0, now(), now()),
        (2, 0,'组织管理', 0, 'org', '/org', 'fa-users', 210, 0, now(), now()),
        (3, 2,'用户管理', 1, 'org:user', '/org/user', "", 211, 0, now(), now()),
        (4, 2,'角色管理', 1, 'org:role', '/org/role', "", 212, 0, now(), now()),
        (5, 2, '资源管理', 1, 'org:resource', '/org/resource', "", 213, 0, now(), now()),
        (6, 2,'组织管理', 1, 'org:org', '/org/org', "", 214, 0, now(), now()),
        (7, 0,'系统管理', 0, 'system', '/system', 'fa-cogs', 220, 0, now(), now()),
        (8, 7,'通知公告', 1, 'system:notify', '/system/notify', 'fa-cogs', 221, 0, now(), now()),
        (9, 7,'审计日志', 1, 'system:log', '//system/log', 'fa-cogs', 222, 0, now(), now()),
        (10, 0,'系统工具', 0, 'tool', '/tool', 'fa-wrench', 230, 0, now(), now()),
        (11, 10,'代码生成', 1, 'tool:codegen', '/tool/codegen', "", 231, 0, now(), now()),
        (12, 0,'帮助中心', 1, 'help', '/help', 'fa-book', 240, 0, now(), now());
INSERT INTO `xxl_deep_role_res` (`id`, `role_id`, `res_id`, `add_time`, `update_time`)
VALUES (1, 1, 1, now(), now()),
       (2, 1, 2, now(), now()),
       (3, 1, 3, now(), now()),
       (4, 1, 4, now(), now()),
       (5, 1, 5, now(), now()),
       (6, 1, 6, now(), now()),
       (7, 1, 7, now(), now()),
       (8, 1, 8, now(), now()),
       (9, 1, 9, now(), now()),
       (10, 1, 10, now(), now()),
       (11, 1, 11, now(), now()),
       (12, 1, 12, now(), now()),
       (13, 2, 1, now(), now()),
       (14, 2, 10, now(), now()),
       (15, 2, 11, now(), now()),
       (16, 2, 12, now(), now());

commit;

