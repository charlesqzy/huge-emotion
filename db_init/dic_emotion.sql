/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.6
Source Server Version : 50639
Source Host           : 192.168.22.6:3306
Source Database       : emotion

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-11 13:47:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dic_emotion
-- ----------------------------
DROP TABLE IF EXISTS `dic_emotion`;
CREATE TABLE `dic_emotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '词',
  `nature` varchar(50) NOT NULL COMMENT '词性标注',
  `freq` int(11) NOT NULL DEFAULT '1' COMMENT '词频',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9847 DEFAULT CHARSET=utf8 COMMENT='情感词典表';
