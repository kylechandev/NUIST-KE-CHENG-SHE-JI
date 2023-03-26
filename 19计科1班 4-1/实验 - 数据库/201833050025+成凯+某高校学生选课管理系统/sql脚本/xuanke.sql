/*
 Navicat Premium Data Transfer

 Source Server         : xuanke-mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : xuanke

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 24/11/2022 13:44:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `class_id` int NOT NULL COMMENT '班级号',
  `course_id` int NOT NULL COMMENT '课程号',
  `tea_id` int NOT NULL COMMENT '教师号',
  `number` int NOT NULL DEFAULT '0' COMMENT '班级人数',
  PRIMARY KEY (`class_id`),
  KEY `course_id` (`course_id`),
  KEY `tea_id` (`tea_id`),
  CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE,
  CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teathers` (`tea_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of classes
-- ----------------------------
BEGIN;
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6001, 3001, 201, 3);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6002, 3001, 204, 0);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6003, 3002, 201, 1);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6004, 3002, 202, 1);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6005, 3003, 203, 0);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6006, 3004, 205, 1);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6007, 3005, 202, 1);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6008, 3005, 204, 2);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6009, 3006, 201, 0);
INSERT INTO `classes` (`class_id`, `course_id`, `tea_id`, `number`) VALUES (6010, 3006, 202, 2);
COMMIT;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `course_id` int NOT NULL COMMENT '课程号',
  `course_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `credit` int NOT NULL COMMENT '学分',
  `open_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开课时间',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of courses
-- ----------------------------
BEGIN;
INSERT INTO `courses` (`course_id`, `course_name`, `credit`, `open_time`) VALUES (3001, '数据库设计', 2, '2021-2022-1');
INSERT INTO `courses` (`course_id`, `course_name`, `credit`, `open_time`) VALUES (3002, 'C语言', 3, '2021-2022-1');
INSERT INTO `courses` (`course_id`, `course_name`, `credit`, `open_time`) VALUES (3003, 'Java程序设计', 3, '2021-2022-2');
INSERT INTO `courses` (`course_id`, `course_name`, `credit`, `open_time`) VALUES (3004, '声学', 2, '2021-2022-2');
INSERT INTO `courses` (`course_id`, `course_name`, `credit`, `open_time`) VALUES (3005, '操作系统', 3, '2020-2021-1');
INSERT INTO `courses` (`course_id`, `course_name`, `credit`, `open_time`) VALUES (3006, '美术艺术', 2, '2020-2021-1');
COMMIT;

-- ----------------------------
-- Table structure for select_courses
-- ----------------------------
DROP TABLE IF EXISTS `select_courses`;
CREATE TABLE `select_courses` (
  `stu_id` int NOT NULL COMMENT '学生号',
  `class_id` int NOT NULL COMMENT '教室号',
  `grade` int NOT NULL COMMENT '成绩',
  PRIMARY KEY (`stu_id`,`class_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `select_courses_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `students` (`stu_id`) ON DELETE CASCADE,
  CONSTRAINT `select_courses_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of select_courses
-- ----------------------------
BEGIN;
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (101, 6001, 90);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (101, 6003, 87);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (101, 6008, 80);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (102, 6001, 75);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (103, 6001, 93);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (103, 6008, 70);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (103, 6010, 85);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (104, 6004, 87);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (104, 6007, 85);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (104, 6010, 80);
INSERT INTO `select_courses` (`stu_id`, `class_id`, `grade`) VALUES (105, 6006, 90);
COMMIT;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `stu_id` int NOT NULL COMMENT '学生号',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `birth_day` date DEFAULT NULL COMMENT '生日',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `major` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业',
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of students
-- ----------------------------
BEGIN;
INSERT INTO `students` (`stu_id`, `name`, `birth_day`, `sex`, `major`) VALUES (101, '邓紫棋', '1991-08-16', '女', '音乐');
INSERT INTO `students` (`stu_id`, `name`, `birth_day`, `sex`, `major`) VALUES (102, '小红', '1999-01-03', '男', '软件工程');
INSERT INTO `students` (`stu_id`, `name`, `birth_day`, `sex`, `major`) VALUES (103, '小王', '1995-09-18', '男', '计算机科学与技术');
INSERT INTO `students` (`stu_id`, `name`, `birth_day`, `sex`, `major`) VALUES (104, '小李', '1998-08-30', '女', '计算机科学与技术');
INSERT INTO `students` (`stu_id`, `name`, `birth_day`, `sex`, `major`) VALUES (105, '小刘', '1999-04-21', '男', '艺术');
COMMIT;

-- ----------------------------
-- Table structure for teathers
-- ----------------------------
DROP TABLE IF EXISTS `teathers`;
CREATE TABLE `teathers` (
  `tea_id` int NOT NULL COMMENT '教师号',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of teathers
-- ----------------------------
BEGIN;
INSERT INTO `teathers` (`tea_id`, `name`, `sex`) VALUES (201, '冯启明', '男');
INSERT INTO `teathers` (`tea_id`, `name`, `sex`) VALUES (202, '赵宇舟', '男');
INSERT INTO `teathers` (`tea_id`, `name`, `sex`) VALUES (203, '仲淼', '男');
INSERT INTO `teathers` (`tea_id`, `name`, `sex`) VALUES (204, '张欢欣', '男');
INSERT INTO `teathers` (`tea_id`, `name`, `sex`) VALUES (205, '刘芳雨', '女');
COMMIT;

-- ----------------------------
-- View structure for not_selected_2006_2007
-- ----------------------------
DROP VIEW IF EXISTS `not_selected_2006_2007`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `not_selected_2006_2007` AS select `classes`.`class_id` AS `class_id`,`c`.`course_name` AS `course_name`,`t`.`name` AS `name` from ((`classes` join `courses` `c` on((`classes`.`course_id` = `c`.`course_id`))) join `teathers` `t` on((`classes`.`tea_id` = `t`.`tea_id`))) where ((`classes`.`number` = 0) and (`c`.`open_time` in ('2006-2007-1','2006-2007-2')));

-- ----------------------------
-- Procedure structure for get_total_credit
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_total_credit`;
delimiter ;;
CREATE PROCEDURE `get_total_credit`(IN term VARCHAR(20))
BEGIN
        SELECT students.stu_id, students.name, SUM(courses.credit)
        FROM select_courses
        JOIN classes ON classes.class_id = select_courses.class_id
        JOIN students ON select_courses.stu_id = students.stu_id
        JOIN courses ON classes.course_id = courses.course_id
        WHERE courses.open_time = term
        GROUP BY students.stu_id, name;
    END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for num_of_students
-- ----------------------------
DROP PROCEDURE IF EXISTS `num_of_students`;
delimiter ;;
CREATE PROCEDURE `num_of_students`()
BEGIN
    SELECT t.name, c.course_name,  SUM(number) num_of_stu
    FROM classes
    JOIN courses c on classes.course_id = c.course_id
    JOIN teathers t on classes.tea_id = t.tea_id
    GROUP BY c.course_id, t.tea_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table select_courses
-- ----------------------------
DROP TRIGGER IF EXISTS `non_repetition`;
delimiter ;;
CREATE TRIGGER `non_repetition` BEFORE INSERT ON `select_courses` FOR EACH ROW BEGIN
        IF (SELECT course_id 
            FROM classes 
            WHERE NEW.class_id = class_id)
            IN 
            (SELECT classes.course_id
             FROM select_courses
             JOIN classes ON classes.class_id = select_courses.class_id
             WHERE stu_id = NEW.stu_id)
            THEN
            SIGNAL SQLSTATE 'HX000' SET MESSAGE_TEXT = 'course was selected';
        END IF;
    END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table select_courses
-- ----------------------------
DROP TRIGGER IF EXISTS `class_number_insert`;
delimiter ;;
CREATE TRIGGER `class_number_insert` AFTER INSERT ON `select_courses` FOR EACH ROW BEGIN
        IF NEW.class_id IN (SELECT class_id FROM classes) THEN
            UPDATE classes SET number = number + 1 
            WHERE class_id = NEW.class_id;
        END IF;
    END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table select_courses
-- ----------------------------
DROP TRIGGER IF EXISTS `class_number_delete`;
delimiter ;;
CREATE TRIGGER `class_number_delete` AFTER DELETE ON `select_courses` FOR EACH ROW BEGIN
        IF OLD.class_id IN (SELECT class_id FROM classes) THEN
            UPDATE classes SET number = number - 1 
            WHERE class_id = OLD.class_id;
        END IF;
    END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
