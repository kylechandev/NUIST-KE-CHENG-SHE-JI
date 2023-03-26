-- 建立改变班级人数的辅助触发器
CREATE TRIGGER class_number_insert AFTER INSERT
    ON select_courses
    FOR EACH ROW
    BEGIN
        IF NEW.class_id IN (SELECT class_id FROM classes) THEN
            UPDATE classes SET number = number + 1 
            WHERE class_id = NEW.class_id;
        END IF;
    END;
-- 建立改变班级人数的辅助触发器
CREATE TRIGGER class_number_delete AFTER DELETE
    ON select_courses
    FOR EACH ROW
    BEGIN
        IF OLD.class_id IN (SELECT class_id FROM classes) THEN
            UPDATE classes SET number = number - 1 
            WHERE class_id = OLD.class_id;
        END IF;
    END;
	
-- CORE	
-- 建立触发器，实现学生不能选择课程号相同的课程。若选择学生选择已选的课程，抛出course was selected的异常
CREATE TRIGGER non_repetition BEFORE INSERT
    ON select_courses
    FOR EACH ROW
    BEGIN
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
    END;
