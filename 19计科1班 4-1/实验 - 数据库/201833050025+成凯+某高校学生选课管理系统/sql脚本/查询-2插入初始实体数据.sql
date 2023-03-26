-- 插入除select_courses表以外的数据
-- insert data into students
INSERT INTO students VALUES(101, '邓紫棋', '1991-08-16', '女', '音乐'); 
INSERT INTO students VALUES(102, '小红', '1999-01-03', '男', '软件工程');
INSERT INTO students VALUES(103, '小王', '1995-9-18', '男', '计算机科学与技术');
INSERT INTO students VALUES(104, '小李', '1998-08-30', '女', '计算机科学与技术');
INSERT INTO students VALUES(105, '小刘', '1999-4-21', '男', '艺术');

-- insert data into teachers
INSERT INTO teathers VALUES(201, '冯启明','男');
INSERT INTO teathers VALUES(202, '赵宇舟','男');
INSERT INTO teathers VALUES(203, '仲淼','男');
INSERT INTO teathers VALUES(204, '张欢欣','男');
INSERT INTO teathers VALUES(205, '刘芳雨','女');

-- insert data into courses
-- 2021-2022-2 表示：2021-2022学年的第2学期
INSERT INTO courses VALUES(3001, '数据库设计', 2, '2021-2022-1');
INSERT INTO courses VALUES(3002, 'C语言', 3, '2021-2022-1');
INSERT INTO courses VALUES(3003, 'Java程序设计', 3, '2021-2022-2');
INSERT INTO courses VALUES(3004, '声学', 2, '2021-2022-2');
INSERT INTO courses VALUES(3005, '操作系统', 3, '2020-2021-1');
INSERT INTO courses VALUES(3006, '美术艺术', 2, '2020-2021-1');

-- insert data into classes
-- 作为临时表，管理多个学生和多个老师的选课，这里插入初始数据暂时先不插入班级人数属性，等后面添加触发器后自动更新
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6001, 3001, 201);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6002, 3001, 204);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6003, 3002, 201);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6004, 3002, 202);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6005, 3003, 203);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6006, 3004, 205);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6007, 3005, 202);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6008, 3005, 204);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6009, 3006, 201);
INSERT INTO classes(class_id, course_id, tea_id) VALUES(6010, 3006, 202);
