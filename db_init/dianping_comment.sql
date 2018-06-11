/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.6
Source Server Version : 50639
Source Host           : 192.168.22.6:3306
Source Database       : emotion

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-11 13:47:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dianping_comment
-- ----------------------------
DROP TABLE IF EXISTS `dianping_comment`;
CREATE TABLE `dianping_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(100) DEFAULT NULL,
  `store_name` varchar(100) DEFAULT NULL,
  `comment_time` varchar(20) DEFAULT NULL,
  `star` varchar(10) DEFAULT NULL,
  `comments` text,
  `response` text,
  `type` varchar(10) DEFAULT NULL,
  `score` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1478 DEFAULT CHARSET=utf8;
