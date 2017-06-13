/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60011
Source Host           : 127.0.0.1:3306
Source Database       : servletblog

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2014-11-22 21:36:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment_log
-- ----------------------------
DROP TABLE IF EXISTS `comment_log`;
CREATE TABLE `comment_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(800) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `log_id` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `log_id` (`log_id`),
  CONSTRAINT `comment_log_ibfk_2` FOREIGN KEY (`log_id`) REFERENCES `log` (`id`),
  CONSTRAINT `comment_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_log
-- ----------------------------
INSERT INTO `comment_log` VALUES ('1', '我顶你', '1', '1', '2014-10-16 22:56:23', '2014-10-25 16:40:51');
INSERT INTO `comment_log` VALUES ('2', '你好啊', '1', '1', '2014-10-25 22:57:56', '2014-11-22 21:25:39');
INSERT INTO `comment_log` VALUES ('3', '我很好', '2', '1', '2014-10-25 16:40:46', '2014-11-22 21:25:41');
INSERT INTO `comment_log` VALUES ('4', '是大法官', '2', '3', '2014-10-25 16:41:08', '2014-10-21 16:41:11');
INSERT INTO `comment_log` VALUES ('5', '高上大放撒大多数', '1', '2', '2014-10-25 19:49:44', '2014-11-22 21:25:44');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '星期六', '第一篇文章要好好写，但是今天心情不怎么好，先写这么多吧！', '2014-10-16 22:36:06', '2014-10-25 12:53:13', '1');
INSERT INTO `log` VALUES ('2', '我有一个想法', '想想好好虚席', '2014-10-16 22:39:59', '2014-11-22 21:25:10', '2');
INSERT INTO `log` VALUES ('3', '第2片文章', '10月24日，广东省纪委监察厅网站称，深圳市委常委、政法委书记蒋尊玉因涉嫌严重违纪问题，正在接受组织调查。\r\n　　', '2014-10-25 16:38:33', '2014-11-22 21:25:15', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(80) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nickname` varchar(40) DEFAULT NULL,
  `description` text,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  `headerpic` varchar(200) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `male` int(1) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'smalle', '123456', '小易Smalle', '小易正在学习java，欢迎和大家交流', '2014-10-24 10:30:28', '2014-10-24 11:00:34', 'pic.jpg', '18', '2014-10-24', '北京', '2312421@qq.com', '1', '12345678901');
INSERT INTO `user` VALUES ('2', 'old', '456', 'old', '无描述', '2014-10-24 10:37:38', '2014-10-25 12:23:41', 'peple.jpg', null, '2014-11-22', '', '', null, '');
