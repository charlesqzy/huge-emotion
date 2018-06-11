/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.6
Source Server Version : 50639
Source Host           : 192.168.22.6:3306
Source Database       : emotion

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-11 13:47:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dianping_sentence
-- ----------------------------
DROP TABLE IF EXISTS `dianping_sentence`;
CREATE TABLE `dianping_sentence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `expression` varchar(100) DEFAULT NULL,
  `score` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5691 DEFAULT CHARSET=utf8;
