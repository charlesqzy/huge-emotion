/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.6
Source Server Version : 50639
Source Host           : 192.168.22.6:3306
Source Database       : emotion

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-11 13:47:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dianping_word
-- ----------------------------
DROP TABLE IF EXISTS `dianping_word`;
CREATE TABLE `dianping_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sentence_id` bigint(20) NOT NULL,
  `content` varchar(200) NOT NULL,
  `nature` varchar(50) NOT NULL,
  `factor` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15501 DEFAULT CHARSET=utf8;
