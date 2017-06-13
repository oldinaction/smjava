/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : smalle

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2014-10-31 00:12:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tm_province
-- ----------------------------
DROP TABLE IF EXISTS `tm_province`;
CREATE TABLE `tm_province` (
  `id` bigint(11) NOT NULL,
  `province` varchar(200) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `province_code` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_province
-- ----------------------------
INSERT INTO `tm_province` VALUES ('110000', '北京市', '9');
INSERT INTO `tm_province` VALUES ('120000', '天津市', '11');
INSERT INTO `tm_province` VALUES ('130000', '河北省', '12');
INSERT INTO `tm_province` VALUES ('140000', '山西省', '13');
INSERT INTO `tm_province` VALUES ('150000', '内蒙古自治区', '14');
INSERT INTO `tm_province` VALUES ('210000', '辽宁省', '15');
INSERT INTO `tm_province` VALUES ('220000', '吉林省', '16');
INSERT INTO `tm_province` VALUES ('230000', '黑龙江省', '17');
INSERT INTO `tm_province` VALUES ('310000', '上海市', '2');
INSERT INTO `tm_province` VALUES ('320000', '江苏省', '7');
INSERT INTO `tm_province` VALUES ('330000', '浙江省', '8');
INSERT INTO `tm_province` VALUES ('340000', '安徽省', '18');
INSERT INTO `tm_province` VALUES ('350000', '福建省', '19');
INSERT INTO `tm_province` VALUES ('360000', '江西省', '20');
INSERT INTO `tm_province` VALUES ('370000', '山东省', '21');
INSERT INTO `tm_province` VALUES ('410000', '河南省', '22');
INSERT INTO `tm_province` VALUES ('420000', '湖北省', '6');
INSERT INTO `tm_province` VALUES ('430000', '湖南省', '1');
INSERT INTO `tm_province` VALUES ('440000', '广东省', '5');
INSERT INTO `tm_province` VALUES ('450000', '广西壮族自治区', '23');
INSERT INTO `tm_province` VALUES ('460000', '海南省', '24');
INSERT INTO `tm_province` VALUES ('500000', '重庆市', '4');
INSERT INTO `tm_province` VALUES ('510000', '四川省', '3');
INSERT INTO `tm_province` VALUES ('520000', '贵州省', '25');
INSERT INTO `tm_province` VALUES ('530000', '云南省', '26');
INSERT INTO `tm_province` VALUES ('540000', '西藏自治区', '27');
INSERT INTO `tm_province` VALUES ('610000', '陕西省', '28');
INSERT INTO `tm_province` VALUES ('620000', '甘肃省', '29');
INSERT INTO `tm_province` VALUES ('630000', '青海省', '30');
INSERT INTO `tm_province` VALUES ('640000', '宁夏回族自治区', '31');
INSERT INTO `tm_province` VALUES ('650000', '新疆维吾尔自治区', '32');
INSERT INTO `tm_province` VALUES ('710000', '台湾省', '33');
INSERT INTO `tm_province` VALUES ('810000', '香港特别行政区', '34');
INSERT INTO `tm_province` VALUES ('820000', '澳门特别行政区', '35');
