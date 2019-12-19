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

 Date: 19/12/2019 18:57:55
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES (1, 'JPG_1575360094427_3e1a2.jpg', 'e:\\upload\\20191203\\JPG_1575360094427_3e1a2.jpg', 1028798, 'de88edf90600f2414fe0c3459076c83d', '1', 'jpg', '2019-12-03 16:01:34');
INSERT INTO `file_info` VALUES (2, 'JPG_1575363563073_df93d.jpg', 'e:\\upload\\20191203\\JPG_1575363563073_df93d.jpg', 350555, '9889608333571b54a8a92b9b2129a830', '1', 'jpg', '2019-12-03 16:59:23');
INSERT INTO `file_info` VALUES (3, 'XMIND_1576722658580_713ff.xmind', 'e:\\upload\\20191219\\XMIND_1576722658580_713ff.xmind', 53488, '3fc977ee2d0cfb1688d122218df7bf1f', '7', 'xmind', '2019-12-19 10:30:58');
INSERT INTO `file_info` VALUES (4, 'PDF_1576722658615_25626.pdf', 'e:\\upload\\20191219\\PDF_1576722658615_25626.pdf', 1143776, '792a6862daa4655bfb914c0de26710d1', '3', 'pdf', '2019-12-19 10:30:58');
INSERT INTO `file_info` VALUES (5, 'PDF_1576722658633_95dfd.pdf', 'e:\\upload\\20191219\\PDF_1576722658633_95dfd.pdf', 1014627, 'b0884e22ec64c0e91f7130cee92fff94', '3', 'pdf', '2019-12-19 10:30:58');
INSERT INTO `file_info` VALUES (6, 'XMIND_1576722658648_26286.xmind', 'e:\\upload\\20191219\\XMIND_1576722658648_26286.xmind', 704346, 'cb906633b7c67b8aa4f27065813f9026', '7', 'xmind', '2019-12-19 10:30:58');
INSERT INTO `file_info` VALUES (7, 'DOC_1576727401455_3e420.doc', 'e:\\upload\\20191219\\DOC_1576727401455_3e420.doc', 381952, 'e174331f6925f16855e2f7bb1dc24113', '3', 'doc', '2019-12-19 11:50:01');
INSERT INTO `file_info` VALUES (8, 'PNG_1576729202446_1835c.png', 'e:\\upload\\20191219\\PNG_1576729202446_1835c.png', 793019, '634afddbb10af95da94709852238dc8f', '1', 'png', '2019-12-19 12:20:02');

-- ----------------------------
-- Table structure for file_type
-- ----------------------------
DROP TABLE IF EXISTS `file_type`;
CREATE TABLE `file_type`  (
  `id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文件类型id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型名称',
  `name_en` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_type
-- ----------------------------
INSERT INTO `file_type` VALUES (1, '图片', 'image');
INSERT INTO `file_type` VALUES (2, '视频', 'video');
INSERT INTO `file_type` VALUES (3, '文档', 'document');
INSERT INTO `file_type` VALUES (4, '音频', 'audio');
INSERT INTO `file_type` VALUES (5, '应用', 'application');
INSERT INTO `file_type` VALUES (6, '种子', 'torrent');
INSERT INTO `file_type` VALUES (7, '其它', 'other');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

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
  `icon` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '文件或文件夹icon',
  `upload_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新文件信息时间，比如修改文件名',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父目录id, 为空则表示是根目录',
  `del` tinyint(1) NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_file
-- ----------------------------
INSERT INTO `user_file` VALUES (1, 'tomcai', '1', '20191203/JPG_1575360094427_3e1a2.jpg', 'Screenshot_2019-11-30-13-00-58-551_com.tencent.weishi.jpg', '1004KB', 'icon-image', '2019-12-03 16:01:34', '2019-12-19 11:11:31', NULL, 1);
INSERT INTO `user_file` VALUES (2, 'admin', '2', '20191203/JPG_1575363563073_df93d.jpg', 'Screenshot_2019-11-30-13-02-31-173_com.tencent.mobileqq.jpg', '342KB', 'icon-image', '2019-12-03 16:59:23', '2019-12-19 10:17:29', NULL, 0);
INSERT INTO `user_file` VALUES (3, 'tomcai', '3', '20191219/XMIND_1576722658580_713ff.xmind', 'java多线程并发编程知识导图笔记.xmind', '52KB', NULL, '2019-12-19 10:30:58', '2019-12-19 10:30:58', NULL, 0);
INSERT INTO `user_file` VALUES (4, 'tomcai', '4', '20191219/PDF_1576722658615_25626.pdf', 'java后端面试题答案.pdf', '1.1M', 'icon-pdf', '2019-12-19 10:30:58', '2019-12-19 10:49:35', NULL, 0);
INSERT INTO `user_file` VALUES (5, 'tomcai', '5', '20191219/PDF_1576722658633_95dfd.pdf', 'Java基础面试题.pdf', '990KB', 'icon-pdf', '2019-12-19 10:30:58', '2019-12-19 10:49:39', NULL, 0);
INSERT INTO `user_file` VALUES (6, 'tomcai', '6', '20191219/XMIND_1576722658648_26286.xmind', 'JVM和性能优化.xmind', '687KB', NULL, '2019-12-19 10:30:58', '2019-12-19 10:30:58', NULL, 0);
INSERT INTO `user_file` VALUES (7, 'tomcai', '7', '20191219/DOC_1576727401455_3e420.doc', '转换规则清单.doc', '373KB', 'icon-word', '2019-12-19 11:50:01', '2019-12-19 11:50:56', NULL, 0);
INSERT INTO `user_file` VALUES (8, 'tomcai', '8', '20191219/PNG_1576729202446_1835c.png', 'index.png', '774KB', NULL, '2019-12-19 12:20:02', '2019-12-19 12:20:02', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
