-- 建立实体表
-- student table
CREATE TABLE students(
    stu_id INT PRIMARY KEY,
    name VARCHAR(40),
    birth_day DATE,
    sex VARCHAR(1),
    major VARCHAR(40)
);
-- teacher table
CREATE TABLE teathers(
    tea_id INT PRIMARY KEY,
    name VARCHAR(40),
    sex VARCHAR(1)
);
-- courses table
CREATE TABLE courses(
    course_id INT PRIMARY KEY,
    course_name VARCHAR(40),
    credit INT,
    open_time VARCHAR(20)
);
-- temp class table
CREATE TABLE classes(
    class_id INT PRIMARY KEY,
    course_id INT,
    tea_id INT,
    number INT DEFAULT 0,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
    FOREIGN KEY (tea_id) REFERENCES teathers(tea_id) ON DELETE CASCADE
);

-- 建立多对多关系表
CREATE TABLE select_courses(
    stu_id INT,
    class_id INT,
    grade INT,
    PRIMARY KEY (stu_id, class_id),
    FOREIGN KEY (stu_id) REFERENCES students(stu_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE
);
