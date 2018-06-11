/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.6
Source Server Version : 50639
Source Host           : 192.168.22.6:3306
Source Database       : emotion

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-11 13:47:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dianping_negative
-- ----------------------------
DROP TABLE IF EXISTS `dianping_negative`;
CREATE TABLE `dianping_negative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `wechat_user` varchar(100) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;
