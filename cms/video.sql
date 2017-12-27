/*
Navicat MySQL Data Transfer

Source Server         : GG
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : video

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2017-12-27 14:00:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `list`
-- ----------------------------
DROP TABLE IF EXISTS `list`;
CREATE TABLE `list` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `age` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of list
-- ----------------------------
INSERT INTO `list` VALUES ('1', '小猫', '21');
INSERT INTO `list` VALUES ('2', '小狗', '23');
INSERT INTO `list` VALUES ('3', '小猪', '11');
INSERT INTO `list` VALUES ('4', 'hh', '33');
INSERT INTO `list` VALUES ('5', 'gg', '23');
INSERT INTO `list` VALUES ('6', 'gg', '23');
INSERT INTO `list` VALUES ('7', '测试', '34');
INSERT INTO `list` VALUES ('8', '测试', '7');
INSERT INTO `list` VALUES ('9', '测试', '70');
INSERT INTO `list` VALUES ('10', 'rr', '32');
INSERT INTO `list` VALUES ('11', 'xiaozhang', '25');

-- ----------------------------
-- Table structure for `movie`
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `name` varchar(32) DEFAULT NULL,
  `picUrl` varchar(64) DEFAULT NULL,
  `descripe` varchar(32) DEFAULT NULL,
  `orderid` int(8) DEFAULT NULL,
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `time` varchar(32) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('星际穿越', 'http://192.168.85.236/sp/timg.jpg', '这是一个神奇的科幻电影，这是一个神奇的科幻电影，这是一个神奇的科', '1', '1', '2017-5-15', '科幻');
INSERT INTO `movie` VALUES ('机器纪元', 'http://192.168.85.236/sp/timg.jpg', '这是个神话科幻片，有点夸张', '2', '2', '2017-6-9', '科幻');
INSERT INTO `movie` VALUES ('少数派报告', 'http://192.168.85.236/sp/timg.jpg', '这是个神奇的电影', '22', '6', '2018.3.6', '科幻');
INSERT INTO `movie` VALUES ('别有动机', 'http://192.168.85.236/sp/timg.jpg', '别有动机，这是个都市心理系列的电影', '16', '12', '2017.8.6', '都市');
INSERT INTO `movie` VALUES ('独立日2', 'http://192.168.85.236/sp/timg.jpg', '这是个神奇的电影独立日2 卷土重来', '21', '7', '2017.1.6', '科幻');
INSERT INTO `movie` VALUES ('三体', 'http://192.168.85.236/sp/timg.jpg', '三体，刘慈欣力作电影科幻', '11', '8', '2016.6.6', '科幻');
INSERT INTO `movie` VALUES ('变形金刚4', 'http://192.168.85.236/sp/timg.jpg', '变形金刚系列第四部', '12', '9', '2016.8.6', '科幻');
INSERT INTO `movie` VALUES ('黄金罗盘', 'http://192.168.85.236/sp/timg.jpg', '黄金罗盘，这是个冒险系列的电影', '15', '10', '2015.8.6', '科幻');
INSERT INTO `movie` VALUES ('异星觉醒', 'http://192.168.85.236/sp/timg.jpg', '异星觉醒，这是个科幻冒险系列的电影', '16', '11', '2017.8.6', '科幻');
INSERT INTO `movie` VALUES ('盗梦空间', 'http://192.168.85.236/sp/timg.jpg', '盗梦空间，这是个科幻悬疑的电影', '17', '13', '2016.11.6', '科幻');
INSERT INTO `movie` VALUES ('生化危机3', 'http://192.168.85.236/sp/timg.jpg', '生化危机3，这是个科幻悬疑的电影', '17', '14', '2017.11.6', '科幻');
INSERT INTO `movie` VALUES ('哈利波特', 'http://192.168.85.236/sp/timg.jpg', '哈利波特，这是个魔幻系列的电影', '18', '15', '2014.11.6', '魔幻');
INSERT INTO `movie` VALUES ('未来警察', 'http://192.168.85.236/sp/timg.jpg', '未来警察，这是个警匪系列的电影', '18', '16', '2016.11.6', '犯罪');
INSERT INTO `movie` VALUES ('飞跃老人院', 'http://192.168.85.236/sp/timg.jpg', '飞跃老人院，这是个奇葩系列的电影', '18', '17', '2017.3.6', '奇葩');
