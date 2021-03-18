/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : security

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 18/03/2021 17:46:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('4028b8817844a3d4017844a3db110000', '案源权限', '/caseSource');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('4028b8817838bb3d017838bb49390000', 'admin');
INSERT INTO `roles` VALUES ('4028b8817838bbea017838bbf4ca0000', 'user');

-- ----------------------------
-- Table structure for roles_permission
-- ----------------------------
DROP TABLE IF EXISTS `roles_permission`;
CREATE TABLE `roles_permission`  (
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permision_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FK6vl3ic2pwn7hkesx4s3qmeflp`(`permision_id`) USING BTREE,
  INDEX `FKigkyo0gp095cm55sjfgy0i4lg`(`role_id`) USING BTREE,
  CONSTRAINT `FK6vl3ic2pwn7hkesx4s3qmeflp` FOREIGN KEY (`permision_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKigkyo0gp095cm55sjfgy0i4lg` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles_permission
-- ----------------------------
INSERT INTO `roles_permission` VALUES ('4028b8817838bbea017838bbf4ca0000', '4028b8817844a3d4017844a3db110000');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FKt7e7djp752sqn6w22i6ocqy6q`(`role_id`) USING BTREE,
  INDEX `FKj345gk1bovqvfame88rcx7yyx`(`user_id`) USING BTREE,
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4028b8817838bd19017838bd26510000', '4028b8817838bb3d017838bb49390000');
INSERT INTO `user_role` VALUES ('4028b8817838bd19017838bd26510000', '4028b8817838bbea017838bbf4ca0000');
INSERT INTO `user_role` VALUES ('4028b8817838be8a017838be961e0000', '4028b8817838bbea017838bbf4ca0000');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enable` bit(1) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('4028b8817838bd19017838bd26510000', b'1', '123456', 'admin');
INSERT INTO `users` VALUES ('4028b8817838be8a017838be961e0000', b'1', '123456', 'panjw');

SET FOREIGN_KEY_CHECKS = 1;
