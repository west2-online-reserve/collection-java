/*
 Navicat MySQL Data Transfer

 Source Server         : new
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : weather

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 23/12/2023 10:39:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cityinformation
-- ----------------------------
DROP TABLE IF EXISTS `cityinformation`;
CREATE TABLE `cityinformation`  (
  `cityId` int NOT NULL COMMENT '和风天气中城市的id',
  `cityname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市的名称',
  `latitude` double(10, 5) NOT NULL COMMENT '城市的纬度',
  `longitude` double(10, 5) NOT NULL COMMENT '城市的经度',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市的位置',
  PRIMARY KEY (`cityId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cityinformation
-- ----------------------------
INSERT INTO `cityinformation` VALUES (101010100, '北京', 39.90499, 116.40529, '中国-北京市-北京');
INSERT INTO `cityinformation` VALUES (101020100, '上海', 31.23171, 121.47264, '中国-上海市-上海');
INSERT INTO `cityinformation` VALUES (101230101, '福州', 26.07530, 119.30624, '中国-福建省-福州');

-- ----------------------------
-- Table structure for weatherinformation
-- ----------------------------
DROP TABLE IF EXISTS `weatherinformation`;
CREATE TABLE `weatherinformation`  (
  `cityid` int NOT NULL COMMENT '城市的id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '0表示今天，1表示明天，2表示后天',
  `fxdate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '日期',
  `tempmax` int NOT NULL DEFAULT 0 COMMENT '最高气温',
  `tempmin` int NOT NULL DEFAULT 0 COMMENT '最低气温',
  `textday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '白天天气情况',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '无意义，作为主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weatherinformation
-- ----------------------------
INSERT INTO `weatherinformation` VALUES (101230101, '0', '2023-12-23', 12, 5, '多云', 4);
INSERT INTO `weatherinformation` VALUES (101230101, '1', '2023-12-24', 16, 7, '多云', 5);
INSERT INTO `weatherinformation` VALUES (101230101, '2', '2023-12-25', 14, 6, '多云', 6);
INSERT INTO `weatherinformation` VALUES (101010100, '0', '2023-12-23', -1, -9, '晴', 13);
INSERT INTO `weatherinformation` VALUES (101010100, '1', '2023-12-24', 1, -9, '晴', 14);
INSERT INTO `weatherinformation` VALUES (101010100, '2', '2023-12-25', 2, -6, '晴', 15);
INSERT INTO `weatherinformation` VALUES (101020100, '0', '2023-12-23', 3, -1, '阴', 16);
INSERT INTO `weatherinformation` VALUES (101020100, '1', '2023-12-24', 6, -2, '晴', 17);
INSERT INTO `weatherinformation` VALUES (101020100, '2', '2023-12-25', 7, 0, '晴', 18);

SET FOREIGN_KEY_CHECKS = 1;
