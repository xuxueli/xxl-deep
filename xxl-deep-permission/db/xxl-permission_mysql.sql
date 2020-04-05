/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50631
 Source Host           : 127.0.0.1
 Source Database       : xxl-permission

 Target Server Type    : MySQL
 Target Server Version : 50631
 File Encoding         : utf-8

 Date: 04/13/2017 22:50:52 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `xxl_permission_menu`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_permission_menu`;
CREATE TABLE `xxl_permission_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` int(11) NOT NULL COMMENT '父节点ID',
  `order` int(11) NOT NULL COMMENT '序号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `permession_url` varchar(50) DEFAULT NULL COMMENT '权限URL',
  `permession_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xxl_permission_menu`
-- ----------------------------
BEGIN;
INSERT INTO `xxl_permission_menu` VALUES ('1', '0', '1', '系统', '', '0'), ('2', '0', '2', '业务分组', '', '0'), ('4', '1', '1', '后台权限管理', '', '0'), ('5', '4', '1', '后台用户管理', 'userPermission/userMain.do', '1000100'), ('6', '4', '2', '后台角色管理', 'userPermission/roleMain.do', '1000200'), ('7', '4', '3', '后台菜单管理', 'userPermission/menuMain.do', '1000300'), ('23', '2', '1', '菜单分组', '', '0');
COMMIT;

-- ----------------------------
--  Table structure for `xxl_permission_role`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_permission_role`;
CREATE TABLE `xxl_permission_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xxl_permission_role`
-- ----------------------------
BEGIN;
INSERT INTO `xxl_permission_role` VALUES ('-1', '1', '超级管理员'), ('8', '2', '业务管理员');
COMMIT;

-- ----------------------------
--  Table structure for `xxl_permission_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_permission_role_menu`;
CREATE TABLE `xxl_permission_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xxl_permission_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `xxl_permission_role_menu` VALUES ('40', '8', '2'), ('41', '8', '23');
COMMIT;

-- ----------------------------
--  Table structure for `xxl_permission_user`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_permission_user`;
CREATE TABLE `xxl_permission_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_token` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5019 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xxl_permission_user`
-- ----------------------------
BEGIN;
INSERT INTO `xxl_permission_user` VALUES ('5000', 'xuxueli', '123456', null, '小刀'), ('5001', 'xuxueli5001', '123456', '', '小刀1'), ('5002', 'xuxueli5002', '123456', null, '小刀2'), ('5003', 'xuxueli5003', '123456', null, '小刀3'), ('5005', 'xuxueli5005', '12345688', null, '小刀5');
COMMIT;

-- ----------------------------
--  Table structure for `xxl_permission_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `xxl_permission_user_role`;
CREATE TABLE `xxl_permission_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xxl_permission_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `xxl_permission_user_role` VALUES ('20', '5000', '-1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
