-- 创建视图，查询2006－2007学年没有被选修的课程
-- 这里“未选修”对应到表上就是，课程人数为0。2006-2007学年即2006-2007-1和2006-2007-2两个学期。
CREATE VIEW not_selected_2006_2007
    AS 
    SELECT class_id, course_name, name
    FROM classes
    JOIN courses c on classes.course_id = c.course_id
    JOIN teathers t on classes.tea_id = t.tea_id
    WHERE number = 0 AND open_time in ('2006-2007-1','2006-2007-2');

-- 创建存储过程，查看指定学期的学生学分
CREATE
    PROCEDURE get_total_credit(IN term VARCHAR(20))
    BEGIN
        SELECT students.stu_id, students.name, SUM(courses.credit)
        FROM select_courses
        JOIN classes ON classes.class_id = select_courses.class_id
        JOIN students ON select_courses.stu_id = students.stu_id
        JOIN courses ON classes.course_id = courses.course_id
        WHERE courses.open_time = term
        GROUP BY students.stu_id, name;
    END;
		
-- 创建存储过程，各教师任课的学生人数
CREATE PROCEDURE num_of_students()
BEGIN
    SELECT t.name, c.course_name,  SUM(number) num_of_stu
    FROM classes
    JOIN courses c on classes.course_id = c.course_id
    JOIN teathers t on classes.tea_id = t.tea_id
    GROUP BY c.course_id, t.tea_id;
END;

-- 查看2021-2022-2学期的学生学分
CALL get_total_credit('2021-2022-2');

-- 查看各教师任课的学生人数（调用存储过程）
CALL num_of_students();
