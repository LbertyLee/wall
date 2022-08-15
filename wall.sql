/*
 Navicat Premium Data Transfer

 Source Server         : nailing
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : wall

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 12/08/2022 17:16:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_comments
-- ----------------------------
DROP TABLE IF EXISTS `article_comments`;
CREATE TABLE `article_comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_comments` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章的评论',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章ID',
  `comment_time` datetime NULL DEFAULT NULL COMMENT '品论的时间',
  `comment_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_comments
-- ----------------------------

-- ----------------------------
-- Table structure for photo_comments
-- ----------------------------
DROP TABLE IF EXISTS `photo_comments`;
CREATE TABLE `photo_comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_comments` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片的评论',
  `photo_id` int(11) NULL DEFAULT NULL COMMENT '图片的ID',
  `comment_time` datetime NULL DEFAULT NULL COMMENT '时间',
  `comment_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of photo_comments
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父类菜单(1=留言墙，2=照片墙)',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '留言', 1);
INSERT INTO `sys_menu` VALUES (2, '目标', 1);
INSERT INTO `sys_menu` VALUES (3, '理想', 1);
INSERT INTO `sys_menu` VALUES (4, '过去', 1);
INSERT INTO `sys_menu` VALUES (5, '将来', 1);
INSERT INTO `sys_menu` VALUES (6, '爱情', 1);
INSERT INTO `sys_menu` VALUES (7, '亲情', 1);
INSERT INTO `sys_menu` VALUES (8, '友情', 1);
INSERT INTO `sys_menu` VALUES (9, '秘密', 1);
INSERT INTO `sys_menu` VALUES (10, '信条', 1);
INSERT INTO `sys_menu` VALUES (11, '无题', 1);
INSERT INTO `sys_menu` VALUES (12, '我', 2);
INSERT INTO `sys_menu` VALUES (13, 'ta', 2);
INSERT INTO `sys_menu` VALUES (14, '最喜欢的', 2);
INSERT INTO `sys_menu` VALUES (15, '最有意义的', 2);
INSERT INTO `sys_menu` VALUES (16, '我的母校', 2);
INSERT INTO `sys_menu` VALUES (17, '我的生活', 2);
INSERT INTO `sys_menu` VALUES (18, '天空', 2);
INSERT INTO `sys_menu` VALUES (19, '大海', 2);
INSERT INTO `sys_menu` VALUES (20, '全部', 1);
INSERT INTO `sys_menu` VALUES (21, '全部', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'common');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2, 2);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `user_net_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户网名',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$rrpKk4IQPjG8pUi87bQNXONZZ6aEeYZZL/FrZuqj97H8cnKDu5MFG', NULL, '2022-08-12 16:07:58', NULL, 'admin');
INSERT INTO `sys_user` VALUES (2, 'cs', '$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', NULL, '2022-07-09 16:00:55', 'common', 'cs');
INSERT INTO `sys_user` VALUES (3, 'lh', '$10$ur9A4jmgrjNmilT5K24VXu0q6HtQWuPncRt0vElqdWtskmn8/sjyq', NULL, '2022-07-09 16:11:42', 'common', 'lh');
INSERT INTO `sys_user` VALUES (5, 'admins123', '$2a$10$bWwdiyF1v27y1X0zfe4pTuTF/91.MHlcI0fHlqImAlCQfisG1/.Gu', NULL, '2022-08-08 15:29:52', 'common', 'admin123');
INSERT INTO `sys_user` VALUES (6, 'admins', '$2a$10$rqicdP1m6WIMetaIaKeGKu/Q9USlI9UG/kJibhvj6v2AwABw.iIHK', NULL, '2022-08-08 15:31:25', 'common', 'admins');
INSERT INTO `sys_user` VALUES (8, 'lzd', '$2a$10$vMqEGXMAAmZT9NCzmxt2F.HN6NyhU6bzNp9u0l0/DLFRjFGBBob2C', NULL, '2022-08-11 14:40:35', 'common', 'lzd');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3, 2);

-- ----------------------------
-- Table structure for wall_article
-- ----------------------------
DROP TABLE IF EXISTS `wall_article`;
CREATE TABLE `wall_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '留言的用户id',
  `article` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言的内容',
  `article_time` datetime NULL DEFAULT NULL COMMENT '文章留言的时间',
  `article_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章的类型',
  `article_like` int(11) NULL DEFAULT NULL COMMENT '文章的点赞量',
  `article_comments` int(11) NULL DEFAULT NULL COMMENT '文章的评论条数',
  `user_net_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言用户的网名',
  `color` varchar(110) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章的背景色',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '1.留言墙\r\n2.照片墙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wall_article
-- ----------------------------
INSERT INTO `wall_article` VALUES (1, 8, 'lzd上传的第1篇文章', '2022-08-11 15:36:26', '回忆', 1, 9, 'lzd', '#FFFFF', 1);

-- ----------------------------
-- Table structure for wall_photo
-- ----------------------------
DROP TABLE IF EXISTS `wall_photo`;
CREATE TABLE `wall_photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户的Id',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片',
  `photo_time` datetime NULL DEFAULT NULL COMMENT '上传照片的时间',
  `photo_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片的类型',
  `photo_like` int(11) NULL DEFAULT NULL COMMENT '点赞量',
  `user_net_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传照片的用户网名',
  `photo_comments` int(11) NULL DEFAULT NULL COMMENT '评论条数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wall_photo
-- ----------------------------
INSERT INTO `wall_photo` VALUES (7, 1, 'profilePhoto/d5c30fb1-537c-477b-bff7-dba9918221c1.webp', '2022-08-12 15:29:55', '旅行', 1, 'lzd', 0);
INSERT INTO `wall_photo` VALUES (8, 1, 'profilePhoto/e6fc9b12-bb36-43bf-9e2a-31982d1c31a2.jpg', '2022-08-12 15:47:54', '旅行', 0, 'lzd', 0);

SET FOREIGN_KEY_CHECKS = 1;
