-- 学生信息管理

-- 更新学号为101的学生的生日信息
UPDATE students SET birth_day='1991-08-16' where stu_id = 101;

-- 更新学号为102的学生的姓名信息
UPDATE students SET `name`='小刚' where stu_id = 102;

-- 删除学号为103的学生
DELETE FROM students WHERE stu_id = 103;



-- 教师管理

-- 更新教师号为202的教师的姓名信息
UPDATE teathers SET `name`='邓超' where tea_id = 202;

-- 删除教师号为203的教师
DELETE FROM teathers WHERE tea_id = 203;

-- 教师课程信息管理

-- 教师添加课程
INSERT INTO courses VALUES(3007, 'Android开发', 3, '2022-2023-1');

-- 更新课程号为3001的课程的名称信息
UPDATE courses SET `course_name`='数据库课程设计原理' where course_id = 3001;

-- 更新课程号为3002的课程的学分信息
UPDATE courses SET credit = 3 where course_id = 3002;

-- 删除课程号为3003的课程
DELETE FROM courses WHERE course_id = 3003;

-- 管理学生课程成绩
UPDATE select_courses SET grade = 95 where students = 101 and class_id 6001;

