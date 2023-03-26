/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.3
Source Server Version : 50728
Source Host           : 192.168.100.3:3306
Source Database       : sports_meeting

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2021-11-09 21:26:23
*/
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `site` varchar(50) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `kind` char(20) DEFAULT NULL,
  `capita` int(11) DEFAULT NULL COMMENT '限制报名人数',
  `isTeam` tinyint(4) DEFAULT '0' COMMENT '0否1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES ('1', '1000米', '111113', '操场', '2018-02-15 14:54:05', '个人项目', '1', '0');
INSERT INTO `competition` VALUES ('2', '800米', '测试', '操场', '2019-12-25 07:35:08', '个人项目', '100', '0');
INSERT INTO `competition` VALUES ('3', '50米', '比赛项目', '操场', '2019-12-01 07:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('4', '100米', '比赛项目', '操场', '2019-12-25 15:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('5', '200米', '比赛项目', '操场', '2019-12-25 15:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('6', '400米', '比赛项目', '操场', '2020-02-06 22:52:47', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('7', '1000米', '比赛项目', '操场', '2019-12-25 15:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('8', '跳高', '比赛项目', '操场', '2019-12-25 15:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('9', '跳远', '比赛项目', '操场', '2019-12-25 15:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('10', '实心球', '比赛项目', '操场', '2019-12-25 15:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('11', '接力', '比赛项目', '操场', '2019-12-03 07:35:08', '男子组个人', '100', '0');
INSERT INTO `competition` VALUES ('12', '50米', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('13', '100米', '比赛项目', '操场', '2019-12-10 23:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('14', '200米', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('15', '400米', '比赛项目', '操场', '2019-12-14 07:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('16', '800米', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('17', '跳高', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('18', '跳远', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('19', '实心球', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('20', '接力', '比赛项目', '操场', '2019-12-25 15:35:08', '女子组个人', '100', '0');
INSERT INTO `competition` VALUES ('25', '两人三足', '趣味比赛', '操场', '2020-02-02 22:52:07', '团体项目', '100', '1');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `uid` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '听力讲义', '<p>你猜带图片讲义<br></p>', null, '2020-02-21 07:04:27', '2020-02-26 16:39:40');
INSERT INTO `news` VALUES ('2', '重要通知！！！！！！！', '<h1><img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif\" alt=\"[神马]\" data-w-e=\"1\">VB小啊啊啊啊<br></h1><p><span style=\"color:#ff0000\">11111111凄凄切切群群群群群群群群群群群群群群群群群群群</span>群群群群群群群群群群群群1</p>', null, '2020-02-22 07:04:27', '2020-02-23 05:49:16');
INSERT INTO `news` VALUES ('3', '通知', '<p><img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif\" alt=\"[神马]\" data-w-e=\"1\"><img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif\" alt=\"[草泥马]\" data-w-e=\"1\">通知阿瓦打我大大无多</p>', null, '2020-02-22 07:04:27', '2020-02-23 02:56:27');
INSERT INTO `news` VALUES ('20', '11111111111111111111', '<p><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"><br></p>', null, '2020-02-22 07:04:27', '2020-02-23 02:56:27');
INSERT INTO `news` VALUES ('21', '555关于第一届运动会的举行通知！！！', '<h1>注意事项</h1><p>11111111111111111111111<br></p><p>22222222222222222222</p><p>33333333333333333333</p><p><img src=\"/static/news-img/a96e66a4-8acd-4942-b321-9e232d09e988.jpg\" style=\"max-width:100%;\"><br></p>', null, '2020-02-26 07:04:27', '2020-04-03 16:03:56');
INSERT INTO `news` VALUES ('22', '11', '<p>1111111111111111111</p>', null, '2020-02-22 07:04:27', '2020-02-23 02:56:30');
INSERT INTO `news` VALUES ('23', '1', '<p>1111111111111111111111111111111111111111111111111111111111</p>', null, '2020-02-22 07:04:27', '2020-02-23 05:50:11');
INSERT INTO `news` VALUES ('24', '11111111111', '1241', null, '2020-02-22 07:04:27', '2020-02-22 07:08:21');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` char(20) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'ggsz', '公告设置');
INSERT INTO `permission` VALUES ('2', 'jsgl', '角色管理');
INSERT INTO `permission` VALUES ('3', 'qxgl', '权限管理');
INSERT INTO `permission` VALUES ('4', 'xmbp', '项目编排与管理');
INSERT INTO `permission` VALUES ('5', 'zhgl', '账号管理');
INSERT INTO `permission` VALUES ('6', 'bmgl', '报名管理');
INSERT INTO `permission` VALUES ('7', 'xmbm', '项目报名');
INSERT INTO `permission` VALUES ('8', 'ckybmxm', '查看已报名项目');
INSERT INTO `permission` VALUES ('9', 'grcjgl', '个人成绩管理');
INSERT INTO `permission` VALUES ('10', 'ckssjc', '查看赛事进程');
INSERT INTO `permission` VALUES ('11', 'xmcjtj', '项目成绩统计');
INSERT INTO `permission` VALUES ('12', 'xmmctj', '项目名次统计');
INSERT INTO `permission` VALUES ('13', 'ckgrcj', '查看个人成绩');
INSERT INTO `permission` VALUES ('14', 'ckxmmc', '查看项目名次');
INSERT INTO `permission` VALUES ('15', 'xgmm', '修改密码');
INSERT INTO `permission` VALUES ('16', 'grxxwh', '个人信息维护');
INSERT INTO `permission` VALUES ('18', 'bbdc', '报表导出');
INSERT INTO `permission` VALUES ('19', '/userCompetition/add', '(URL权限)项目报名');
INSERT INTO `permission` VALUES ('20', 'tdcjgl', '团队成绩管理');
INSERT INTO `permission` VALUES ('21', 'ydhgl', '运动会管理');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` char(20) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'user', '用户');
INSERT INTO `role` VALUES ('3', 'score', '成绩录入员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('2', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('2', '8');
INSERT INTO `role_permission` VALUES ('1', '9');
INSERT INTO `role_permission` VALUES ('3', '9');
INSERT INTO `role_permission` VALUES ('14', '9');
INSERT INTO `role_permission` VALUES ('1', '10');
INSERT INTO `role_permission` VALUES ('2', '10');
INSERT INTO `role_permission` VALUES ('1', '11');
INSERT INTO `role_permission` VALUES ('1', '12');
INSERT INTO `role_permission` VALUES ('1', '13');
INSERT INTO `role_permission` VALUES ('2', '13');
INSERT INTO `role_permission` VALUES ('1', '14');
INSERT INTO `role_permission` VALUES ('2', '14');
INSERT INTO `role_permission` VALUES ('1', '15');
INSERT INTO `role_permission` VALUES ('2', '15');
INSERT INTO `role_permission` VALUES ('1', '16');
INSERT INTO `role_permission` VALUES ('2', '16');
INSERT INTO `role_permission` VALUES ('1', '18');
INSERT INTO `role_permission` VALUES ('1', '19');
INSERT INTO `role_permission` VALUES ('2', '19');
INSERT INTO `role_permission` VALUES ('1', '20');
INSERT INTO `role_permission` VALUES ('3', '20');
INSERT INTO `role_permission` VALUES ('1', '21');

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` char(20) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '当前运动会  默认0   当前运动会为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES ('1', '1', '洛阳师范第一届运动会', '1');
INSERT INTO `session` VALUES ('5', '2', '洛阳师范第二届运动会', '0');
INSERT INTO `session` VALUES ('6', '3', '第三届运动会', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '0' COMMENT '0 男 1 女',
  `email` char(30) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `realname` char(30) DEFAULT NULL,
  `department` char(30) DEFAULT NULL,
  `team` char(30) DEFAULT NULL,
  `start_school_year` char(4) DEFAULT NULL,
  `leave_school_year` char(4) DEFAULT NULL,
  `enable` tinyint(4) DEFAULT '1' COMMENT '是否启用  0 禁用 1 启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', '1', '1185244279@qq.com', '13033991040', 'admin', '信息技术学院', '移动二班', '2016', '2020', '1');
INSERT INTO `user` VALUES ('2', 'fht', '7061a069967351a90dec423fe421a37e', '0', '', '', '范海腾', '化学学院', '化学一班', '2016', '2020', '1');
INSERT INTO `user` VALUES ('3', '111', '879d6457d5315e047d842e5507c262b5', '0', '', '', '111', '法学院', '法学一班', '2016', '2020', '1');

-- ----------------------------
-- Table structure for user_competition
-- ----------------------------
DROP TABLE IF EXISTS `user_competition`;
CREATE TABLE `user_competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `sessionid` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `报名记录` (`uid`,`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_competition
-- ----------------------------
INSERT INTO `user_competition` VALUES ('64', '1', '24', '11分20', '12', '1');
INSERT INTO `user_competition` VALUES ('65', '1', '19', '1分20', '1', '1');
INSERT INTO `user_competition` VALUES ('66', '1', '20', '14分20', '21', '1');
INSERT INTO `user_competition` VALUES ('69', '1', '26', '13分20', '2', '1');
INSERT INTO `user_competition` VALUES ('72', '1', '25', '1分20', '1', '1');
INSERT INTO `user_competition` VALUES ('91', '3', '5', '12分20', '54', '1');
INSERT INTO `user_competition` VALUES ('92', '2', '4', '1分20', '88', '1');
INSERT INTO `user_competition` VALUES ('94', '2', '2', '1分20', '99', '1');
INSERT INTO `user_competition` VALUES ('95', '2', '25', '1分10', '10', '1');
INSERT INTO `user_competition` VALUES ('96', '2', '3', '1分20', '34', '1');
INSERT INTO `user_competition` VALUES ('97', '3', '25', '1分钟', '100', '1');
INSERT INTO `user_competition` VALUES ('98', '3', '17', '1分20', '76', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '1');
INSERT INTO `user_role` VALUES ('3', '2');
