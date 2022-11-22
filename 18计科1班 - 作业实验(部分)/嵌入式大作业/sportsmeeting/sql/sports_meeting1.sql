/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : sports_meeting

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 20/11/2021 16:27:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `site` varchar(50) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `kind` char(20) DEFAULT NULL,
  `capita` int DEFAULT NULL COMMENT '限制报名人数',
  `isTeam` tinyint DEFAULT '0' COMMENT '0否1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition
-- ----------------------------
BEGIN;
INSERT INTO `competition` VALUES (1, '1000米', '111113', '操场', '2021-02-15 14:54:05', '个人项目', 1, 0);
INSERT INTO `competition` VALUES (2, '800米', '测试', '操场', '2021-12-25 07:35:08', '个人项目', 100, 0);
INSERT INTO `competition` VALUES (3, '50米', '比赛项目', '操场', '2021-12-01 07:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (4, '100米', '比赛项目', '操场', '2021-12-25 15:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (5, '200米', '比赛项目', '操场', '2021-12-25 15:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (6, '400米', '比赛项目', '操场', '2021-12-06 22:52:47', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (7, '1000米', '比赛项目', '操场', '2021-12-25 15:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (8, '跳高', '比赛项目', '操场', '2021-12-25 15:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (9, '跳远', '比赛项目', '操场', '2021-12-25 15:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (10, '实心球', '比赛项目', '操场', '2021-12-25 15:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (11, '接力', '比赛项目', '操场', '2021-12-03 07:35:08', '男子组个人', 100, 0);
INSERT INTO `competition` VALUES (12, '50米', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (13, '100米', '比赛项目', '操场', '2021-12-10 23:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (14, '200米', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (15, '400米', '比赛项目', '操场', '2021-12-14 07:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (16, '800米', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (17, '跳高', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (18, '跳远', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (19, '实心球', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (20, '接力', '比赛项目', '操场', '2021-12-25 15:35:08', '女子组个人', 100, 0);
INSERT INTO `competition` VALUES (25, '两人三足', '趣味比赛', '操场', '2021-02-02 22:52:07', '团体项目', 100, 1);
COMMIT;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `uid` int DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
BEGIN;
INSERT INTO `news` VALUES (2, '重要通知！！！！！！！', '<h1>12321321<span style=\"font-size: 14px; color: rgb(255, 0, 0);\">群群群群群群群群群</span><span style=\"font-size: 14px;\">群群群群群群群群群群群群1</span></h1>', NULL, '2021-09-22 07:04:27', '2021-11-20 15:55:17');
INSERT INTO `news` VALUES (21, '555关于第一届运动会的举行通知！！！', '<h1>注意事项</h1><p>11111111111111111111111<br></p><p>22222222222222222222</p><p>33333333333333333333</p><p><br></p>', NULL, '2021-09-26 07:04:27', '2021-11-20 15:55:27');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` char(20) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 'ggsz', '公告设置');
INSERT INTO `permission` VALUES (2, 'jsgl', '角色管理');
INSERT INTO `permission` VALUES (3, 'qxgl', '权限管理');
INSERT INTO `permission` VALUES (4, 'xmbp', '项目编排与管理');
INSERT INTO `permission` VALUES (5, 'zhgl', '账号管理');
INSERT INTO `permission` VALUES (6, 'bmgl', '报名管理');
INSERT INTO `permission` VALUES (7, 'xmbm', '项目报名');
INSERT INTO `permission` VALUES (8, 'ckybmxm', '查看已报名项目');
INSERT INTO `permission` VALUES (9, 'grcjgl', '个人成绩管理');
INSERT INTO `permission` VALUES (10, 'ckssjc', '查看赛事进程');
INSERT INTO `permission` VALUES (11, 'xmcjtj', '项目成绩统计');
INSERT INTO `permission` VALUES (12, 'xmmctj', '项目名次统计');
INSERT INTO `permission` VALUES (13, 'ckgrcj', '查看个人成绩');
INSERT INTO `permission` VALUES (14, 'ckxmmc', '查看项目名次');
INSERT INTO `permission` VALUES (15, 'xgmm', '修改密码');
INSERT INTO `permission` VALUES (16, 'grxxwh', '个人信息维护');
INSERT INTO `permission` VALUES (18, 'bbdc', '报表导出');
INSERT INTO `permission` VALUES (19, '/userCompetition/add', '(URL权限)项目报名');
INSERT INTO `permission` VALUES (20, 'tdcjgl', '团队成绩管理');
INSERT INTO `permission` VALUES (21, 'ydhgl', '运动会管理');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` char(20) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'user', '用户');
INSERT INTO `role` VALUES (3, 'score', '成绩录入员');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (1, 4);
INSERT INTO `role_permission` VALUES (1, 5);
INSERT INTO `role_permission` VALUES (1, 6);
INSERT INTO `role_permission` VALUES (1, 7);
INSERT INTO `role_permission` VALUES (2, 7);
INSERT INTO `role_permission` VALUES (1, 8);
INSERT INTO `role_permission` VALUES (2, 8);
INSERT INTO `role_permission` VALUES (1, 9);
INSERT INTO `role_permission` VALUES (3, 9);
INSERT INTO `role_permission` VALUES (14, 9);
INSERT INTO `role_permission` VALUES (1, 10);
INSERT INTO `role_permission` VALUES (2, 10);
INSERT INTO `role_permission` VALUES (1, 11);
INSERT INTO `role_permission` VALUES (1, 12);
INSERT INTO `role_permission` VALUES (1, 13);
INSERT INTO `role_permission` VALUES (2, 13);
INSERT INTO `role_permission` VALUES (1, 14);
INSERT INTO `role_permission` VALUES (2, 14);
INSERT INTO `role_permission` VALUES (1, 15);
INSERT INTO `role_permission` VALUES (2, 15);
INSERT INTO `role_permission` VALUES (1, 16);
INSERT INTO `role_permission` VALUES (2, 16);
INSERT INTO `role_permission` VALUES (1, 18);
INSERT INTO `role_permission` VALUES (1, 19);
INSERT INTO `role_permission` VALUES (2, 19);
INSERT INTO `role_permission` VALUES (1, 20);
INSERT INTO `role_permission` VALUES (3, 20);
INSERT INTO `role_permission` VALUES (1, 21);
COMMIT;

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` char(20) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  `status` tinyint DEFAULT '0' COMMENT '当前运动会  默认0   当前运动会为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of session
-- ----------------------------
BEGIN;
INSERT INTO `session` VALUES (1, '1', '第一届运动会', 1);
INSERT INTO `session` VALUES (5, '2', '第二届运动会', 0);
INSERT INTO `session` VALUES (6, '3', '第三届运动会', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` char(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sex` tinyint DEFAULT '0' COMMENT '0 男 1 女',
  `email` char(30) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `realname` char(30) DEFAULT NULL,
  `department` char(30) DEFAULT NULL,
  `team` char(30) DEFAULT NULL,
  `start_school_year` char(4) DEFAULT NULL,
  `leave_school_year` char(4) DEFAULT NULL,
  `enable` tinyint DEFAULT '1' COMMENT '是否启用  0 禁用 1 启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', 0, '1370797402@qq.com', '13357910833', '汤佳龙', '应用技术学院', '计科二班', '2018', '2022', 1);
INSERT INTO `user` VALUES (2, 'fht', '7061a069967351a90dec423fe421a37e', 0, '', '', '叶成宇', '应用技术学院', '计科一班', '2018', '2022', 1);
INSERT INTO `user` VALUES (3, '111', '879d6457d5315e047d842e5507c262b5', 0, '', '', '韩宏磊', '应用技术学院', '计科一班', '2018', '2022', 1);
COMMIT;

-- ----------------------------
-- Table structure for user_competition
-- ----------------------------
DROP TABLE IF EXISTS `user_competition`;
CREATE TABLE `user_competition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `cid` int NOT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `score` int DEFAULT NULL,
  `sessionid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `报名记录` (`uid`,`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_competition
-- ----------------------------
BEGIN;
INSERT INTO `user_competition` VALUES (64, 1, 24, '11分20', 12, 1);
INSERT INTO `user_competition` VALUES (65, 1, 19, '1分20', 1, 1);
INSERT INTO `user_competition` VALUES (66, 1, 20, '14分20', 21, 1);
INSERT INTO `user_competition` VALUES (69, 1, 26, '13分20', 2, 1);
INSERT INTO `user_competition` VALUES (72, 1, 25, '1分20', 1, 1);
INSERT INTO `user_competition` VALUES (91, 3, 5, '12分20', 54, 1);
INSERT INTO `user_competition` VALUES (92, 2, 4, '1分20', 88, 1);
INSERT INTO `user_competition` VALUES (94, 2, 2, '1分20', 99, 1);
INSERT INTO `user_competition` VALUES (95, 2, 25, '1分10', 10, 1);
INSERT INTO `user_competition` VALUES (96, 2, 3, '1分20', 34, 1);
INSERT INTO `user_competition` VALUES (97, 3, 25, '1分钟', 100, 1);
INSERT INTO `user_competition` VALUES (98, 3, 17, '1分20', 76, 1);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`uid`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (3, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
