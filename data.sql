CREATE TABLE `subscribe` (
                             `subscribe_id` int(11) DEFAULT NULL,
                             `room_id` varchar(128) DEFAULT NULL,
                             `room_title` varchar(255) DEFAULT NULL,
                             `user_name` varchar(255) DEFAULT NULL,
                             `start_time` datetime DEFAULT NULL,
                             `end_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : room

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 22/06/2021 11:42:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
                             `user_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                             `user_password` varchar(255) DEFAULT NULL,
                             `user_nickname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('admin', 'c4ca4238a0b923820dcc509a6f75849b', 'admin');
INSERT INTO `user_info` VALUES ('yang', 'c4ca4238a0b923820dcc509a6f75849b', 'yang');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;



