/*
 Navicat Premium Data Transfer

 Source Server         : hx_mysql
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : schoolmis_new

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 20/03/2019 13:08:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actionfunction
-- ----------------------------
DROP TABLE IF EXISTS `actionfunction`;
CREATE TABLE `actionfunction`  (
  `functionId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `functionName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `functionClass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `functionMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `menuId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `addressId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `street` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `personId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`addressId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for auth
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth`  (
  `roleId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `functionId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roleId`, `functionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of auth
-- ----------------------------
INSERT INTO `auth` VALUES ('admin', '1001');
INSERT INTO `auth` VALUES ('admin', '1002');
INSERT INTO `auth` VALUES ('admin', '1003');
INSERT INTO `auth` VALUES ('admin', '1004');
INSERT INTO `auth` VALUES ('admin', '1005');
INSERT INTO `auth` VALUES ('admin', '1006');
INSERT INTO `auth` VALUES ('admin', '1007');
INSERT INTO `auth` VALUES ('admin', '1008');
INSERT INTO `auth` VALUES ('admin', '1009');
INSERT INTO `auth` VALUES ('admin', '1010');
INSERT INTO `auth` VALUES ('admin', '1011');
INSERT INTO `auth` VALUES ('admin', '1012');
INSERT INTO `auth` VALUES ('admin', '1013');
INSERT INTO `auth` VALUES ('admin', '1014');
INSERT INTO `auth` VALUES ('admin', '1015');
INSERT INTO `auth` VALUES ('admin', '1016');
INSERT INTO `auth` VALUES ('admin', '1017');
INSERT INTO `auth` VALUES ('admin', '1018');
INSERT INTO `auth` VALUES ('admin', '1019');
INSERT INTO `auth` VALUES ('admin', '1020');
INSERT INTO `auth` VALUES ('admin', '1021');
INSERT INTO `auth` VALUES ('guest', '1004');
INSERT INTO `auth` VALUES ('guest', '1008');
INSERT INTO `auth` VALUES ('guest', '1013');
INSERT INTO `auth` VALUES ('guest', '1017');
INSERT INTO `auth` VALUES ('guest', '1021');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseHour` int(11) NULL DEFAULT NULL,
  `courseMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `specialtyId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`courseId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employeeId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employeeName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employeeSex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employeeBirthday` datetime NULL DEFAULT NULL,
  `employeeTel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employeeAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employeeInDate` datetime NULL DEFAULT NULL,
  `employeeDuty` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employeeIsTeacher` tinyint(4) NULL DEFAULT NULL,
  `employeeMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `orgId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`employeeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menuId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menuName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `welcomePage` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('10', '机构管理', '机构类别的管理', NULL);
INSERT INTO `menu` VALUES ('1010', '机构类别设置', NULL, NULL);
INSERT INTO `menu` VALUES ('1011', '机构设置', NULL, NULL);
INSERT INTO `menu` VALUES ('1012', '机构信息检索', NULL, NULL);
INSERT INTO `menu` VALUES ('20', '课程管理', '课程管理', NULL);
INSERT INTO `menu` VALUES ('2010', '专业设置', NULL, NULL);
INSERT INTO `menu` VALUES ('2011', '课程设置', NULL, NULL);
INSERT INTO `menu` VALUES ('2012', '班级设置', NULL, NULL);
INSERT INTO `menu` VALUES ('2013', '专业课程查询', NULL, NULL);
INSERT INTO `menu` VALUES ('2014', '专业班级查询', NULL, NULL);
INSERT INTO `menu` VALUES ('30', '成员管理', NULL, NULL);
INSERT INTO `menu` VALUES ('3010', '教师管理', NULL, NULL);
INSERT INTO `menu` VALUES ('3011', '后勤人员管理', NULL, NULL);
INSERT INTO `menu` VALUES ('40', '学籍管理', NULL, NULL);
INSERT INTO `menu` VALUES ('4010', '学生管理', NULL, NULL);
INSERT INTO `menu` VALUES ('4011', '选课管理', NULL, NULL);
INSERT INTO `menu` VALUES ('4012', '学生课程查询', NULL, NULL);
INSERT INTO `menu` VALUES ('50', '统计查询', NULL, NULL);

-- ----------------------------
-- Table structure for misclass
-- ----------------------------
DROP TABLE IF EXISTS `misclass`;
CREATE TABLE `misclass`  (
  `classId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `className` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `employeeId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specialtyId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`classId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for misfunction
-- ----------------------------
DROP TABLE IF EXISTS `misfunction`;
CREATE TABLE `misfunction`  (
  `functionId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `functionName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `functionClass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `functionMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `menuId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of misfunction
-- ----------------------------
INSERT INTO `misfunction` VALUES ('1001', '增加机构类别', 'com.xx.sm.framework.control.action.org.OrgTypeCreateAction', '增加机构类别', '1010');
INSERT INTO `misfunction` VALUES ('1002', '修改机构类别', 'com.xx.sm.framework.control.action.org.OrgTypeModifyAction', '修改机构类别', '1010');
INSERT INTO `misfunction` VALUES ('1003', '删除机构类别', 'com.xx.sm.framework.control.action.org.OrgTypeRemoveAction', NULL, '1010');
INSERT INTO `misfunction` VALUES ('1004', '查询机构类别', 'com.xx.sm.framework.control.action.org.OrgTypeQueryAction', NULL, '1010');
INSERT INTO `misfunction` VALUES ('1005', '增加机构', NULL, NULL, '1011');
INSERT INTO `misfunction` VALUES ('1006', '删除机构', NULL, NULL, '1011');
INSERT INTO `misfunction` VALUES ('1007', '修改机构', NULL, NULL, '1011');
INSERT INTO `misfunction` VALUES ('1008', '查询机构', NULL, NULL, '1011');
INSERT INTO `misfunction` VALUES ('1009', '机构信息检索', NULL, NULL, '1012');
INSERT INTO `misfunction` VALUES ('1010', '增加专业', NULL, '增加专业', '2010');
INSERT INTO `misfunction` VALUES ('1011', '修改专业', NULL, NULL, '2010');
INSERT INTO `misfunction` VALUES ('1012', '删除专业', NULL, NULL, '2010');
INSERT INTO `misfunction` VALUES ('1013', '查询专业', NULL, NULL, '2010');
INSERT INTO `misfunction` VALUES ('1014', '增加课程', NULL, NULL, '2011');
INSERT INTO `misfunction` VALUES ('1015', '修改课程', NULL, NULL, '2011');
INSERT INTO `misfunction` VALUES ('1016', '删除课程', NULL, NULL, '2011');
INSERT INTO `misfunction` VALUES ('1017', '查询课程', NULL, NULL, '2011');
INSERT INTO `misfunction` VALUES ('1018', '增加班级', NULL, NULL, '2012');
INSERT INTO `misfunction` VALUES ('1019', '删除班级', NULL, NULL, '2012');
INSERT INTO `misfunction` VALUES ('1020', '修改班级', NULL, NULL, '2012');
INSERT INTO `misfunction` VALUES ('1021', '查询班级', NULL, NULL, '2012');

-- ----------------------------
-- Table structure for misuser
-- ----------------------------
DROP TABLE IF EXISTS `misuser`;
CREATE TABLE `misuser`  (
  `userId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userPwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `roleId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addressId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of misuser
-- ----------------------------
INSERT INTO `misuser` VALUES ('1', 'admin', 'admin', 'admin', 'admin', '105');
INSERT INTO `misuser` VALUES ('2', 'guest', 'guest', 'guest', 'guest', '106');

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org`  (
  `orgId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orgNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `orgTypeId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orgId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for orgtype
-- ----------------------------
DROP TABLE IF EXISTS `orgtype`;
CREATE TABLE `orgtype`  (
  `orgTypeId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orgTypeName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgTypeMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`orgTypeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orgtype
-- ----------------------------
INSERT INTO `orgtype` VALUES ('007', '机器学习', '算法课程');
INSERT INTO `orgtype` VALUES ('555', '软件开发', '基础课程');
INSERT INTO `orgtype` VALUES ('666', '数据库', '基础课程');
INSERT INTO `orgtype` VALUES ('888', '数据挖掘', '基础课程');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleMemo` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('admin', '管理员', 'admin');
INSERT INTO `role` VALUES ('guest', 'guest', 'guest');

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse`  (
  `studentId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`studentId`, `courseId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specialty
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty`  (
  `specialtyId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `specialtyName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specialtyMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `orgId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`specialtyId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentSex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentBirthday` datetime NULL DEFAULT NULL,
  `studentTel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentInDate` datetime NULL DEFAULT NULL,
  `studentMemo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `classId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`studentId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for teachcourse
-- ----------------------------
DROP TABLE IF EXISTS `teachcourse`;
CREATE TABLE `teachcourse`  (
  `employeeId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classId` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`employeeId`, `courseId`, `classId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for view1
-- ----------------------------
DROP VIEW IF EXISTS `view1`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view1` AS select `misuser`.`userName` AS `userName`,`role`.`roleId` AS `roleId` from (`misuser` join `role` on((`misuser`.`roleId` = `role`.`roleId`)));

SET FOREIGN_KEY_CHECKS = 1;
