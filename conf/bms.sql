/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : bms

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-07-08 18:19:41
*/
DROP DATABASE IF EXISTS `bms` ;
CREATE DATABASE `bms` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `bms` ;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `left_copies` int(11) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_copies` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '6', '1212121', 'C++从入门到放弃', '12');
INSERT INTO `book` VALUES ('2', '0', '12121213', 'JAVA从入门到放弃', '3');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enumber` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '1111111111', '小李');
INSERT INTO `employee` VALUES ('2', '222222222222222', '小强');
INSERT INTO `employee` VALUES ('3', '33333333333', '小白');
INSERT INTO `employee` VALUES ('4', '444444444', '小红');
INSERT INTO `employee` VALUES ('5', '5555555555', '小王');
INSERT INTO `employee` VALUES ('6', '6666666', '李丽');
INSERT INTO `employee` VALUES ('7', '77777777777', '老陶');
INSERT INTO `employee` VALUES ('8', '888888888888', '阿斯顿');
INSERT INTO `employee` VALUES ('9', '999999999', '大傻');
INSERT INTO `employee` VALUES ('10', 's111111111', '大使');
INSERT INTO `employee` VALUES ('11', 's22222222', '移动');

-- ----------------------------
-- Table structure for `record`
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `add_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `eid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC8466C5128F35522` (`bid`),
  KEY `FKC8466C51FE1F0E2A` (`eid`),
  CONSTRAINT `FKC8466C5128F35522` FOREIGN KEY (`bid`) REFERENCES `book` (`id`),
  CONSTRAINT `FKC8466C51FE1F0E2A` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '2016-07-07 21:28:21', '0', '1', '1');
INSERT INTO `record` VALUES ('2', '2016-07-07 21:28:25', '0', '2', '1');
INSERT INTO `record` VALUES ('3', '2016-07-07 21:28:29', '0', '2', '1');
INSERT INTO `record` VALUES ('4', '2016-07-07 21:28:33', '0', '2', '1');
INSERT INTO `record` VALUES ('5', '2016-07-07 21:28:36', '0', '2', '1');
INSERT INTO `record` VALUES ('6', '2016-07-07 21:28:41', '1', '2', '1');
INSERT INTO `record` VALUES ('7', '2016-07-07 21:38:22', '0', '2', '2');
INSERT INTO `record` VALUES ('8', '2016-07-07 21:38:27', '1', '2', '2');
