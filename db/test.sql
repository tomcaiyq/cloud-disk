/*
 Navicat MySQL Data Transfer

 Source Server         : tomcai
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 106.13.41.208:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/11/2019 18:50:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件id',
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父目录id',
  `type_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型id',
  `suffix` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件后缀名 eg.  .txt 后缀为空则表示为文件夹',
  `uploader_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件上传者id',
  `upload_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '文件上传时间',
  `update_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '文件更新时间',
  `del` tinyint(1) NULL DEFAULT 0 COMMENT '文件删除标志 0正常1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('132ea791-57af-4399-b20a-97d665c3d837', 'JMM.png', 'e:\\upload\\tomcai\\JMM.png', 290129, NULL, '1', 'png', '2', '2019-11-22 18:46:45', '2019-11-22 18:46:45', 0);
INSERT INTO `file_info` VALUES ('1e23c10d-bbb4-456b-b02d-1aa0cb172766', '飞手信息管理数据导入模板.xlsx', '/usr/local/upload/飞手信息管理数据导入模板.xlsx', 10716, NULL, '3', 'xlsx', '1', '2019-11-22 18:41:41', '2019-11-22 18:41:41', 0);
INSERT INTO `file_info` VALUES ('a3e3880f-266f-4b3a-95a0-4e7e841a38a6', '1570546698452.mp4', '/usr/local/upload/1570546698452.mp4', 1536547, NULL, '2', 'mp4', '1', '2019-11-22 18:34:04', '2019-11-22 18:34:04', 0);

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
INSERT INTO `file_type` VALUES ('5', '种子', 'torrent');
INSERT INTO `file_type` VALUES ('6', '其它', 'other');

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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('00006fa6-091e-4ac5-85c4-ad1c7c7cf5f0', 'zs3351', 'user', '2019-11-18 13:22:07', '2019-11-18 13:22:07');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，用于登录',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Email 可用于登录',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号，用于登录',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别 0女1男2保密',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `create_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '账号创建时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '账号状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', 'admin', 'admin@admin.com', '123', NULL, 1, '2019-11-22', '2019-11-22 11:38:23', 0);
INSERT INTO `t_user` VALUES ('2', 'tomcai', '123456', 'tomcai', '1348870256@qq.com', '110', NULL, 1, '1995-06-27', '2019-11-20 20:52:35', 0);

SET FOREIGN_KEY_CHECKS = 1;
