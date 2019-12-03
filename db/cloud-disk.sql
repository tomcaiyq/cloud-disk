/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : cloud-disk

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 03/12/2019 14:46:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `md5` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `type_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型id',
  `suffix` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件后缀名 eg.  .txt 后缀为空则表示为文件夹',
  `upload_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '文件上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file_type
-- ----------------------------
DROP TABLE IF EXISTS `file_type`;
CREATE TABLE `file_type`  (
  `id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型名',
  `name_en` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_type
-- ----------------------------
INSERT INTO `file_type` VALUES ('1', '图片', 'image');
INSERT INTO `file_type` VALUES ('2', '视频', 'video');
INSERT INTO `file_type` VALUES ('3', '文档', 'document');
INSERT INTO `file_type` VALUES ('4', '音频', 'audio');
INSERT INTO `file_type` VALUES ('5', '应用', 'application');
INSERT INTO `file_type` VALUES ('6', '种子', 'torrent');
INSERT INTO `file_type` VALUES ('7', '其它', 'other');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role_type` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，用于登录',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Email 可用于登录',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号，用于登录',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别 0女1男2保密',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `create_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '账号创建时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '账号状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'df655ad8d3229f3269fad2a8bab59b6c', 'admin', 'admin@qq.com', '110', 'https://i2.hdslb.com/bfs/face/80393534ae4a590f4ff4e25e17f463ce92aeea32.jpg@72w_72h.webp', 1, '1995-06-27', '2019-12-03 12:15:49', 0);
INSERT INTO `t_user` VALUES (2, 'tomcai', '7d2d914e2df54e72b4e9815db7b58cc0', 'tomcai', 'tomcai@qq.com', '120', 'https://i2.hdslb.com/bfs/face/80393534ae4a590f4ff4e25e17f463ce92aeea32.jpg@72w_72h.webp', 1, '1995-06-27', '2019-12-03 11:05:01', 0);

-- ----------------------------
-- Table structure for user_file
-- ----------------------------
DROP TABLE IF EXISTS `user_file`;
CREATE TABLE `user_file`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `file_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件id',
  `user_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户访问的路径',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `size_show` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件大小转换后用来显示字符串',
  `upload_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新文件信息时间，比如修改文件名',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父目录id, 为空则表示是根目录',
  `del` tinyint(1) NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
