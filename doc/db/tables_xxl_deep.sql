#
# XXL-DEEP v1.0.0-SNAPSHOT
# Copyright (c) 2015-present, xuxueli.

CREATE database if NOT EXISTS `xxl_deep` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `xxl_deep`;

SET NAMES utf8mb4;


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


INSERT INTO `xxl_deep_user` (`id`, `username`, `password`, `user_token`, `status`, `real_name`, `add_time`, `update_time`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', 0 , '吴彦祖', now(), now());
INSERT INTO `xxl_deep_role` (`id`, `name`, `order`, `add_time`, `update_time`) VALUES (1, '管理员', 1, now(), now());
INSERT INTO `xxl_deep_user_role` (`id`, `user_id`, `role_id`, `add_time`, `update_time`) VALUES (1, 1, 1, now(), now());
INSERT INTO `xxl_deep_role_res` (`id`, `role_id`, `res_id`, `add_time`, `update_time`) VALUES (1, 1, 1, now(), now());


commit;

