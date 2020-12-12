-- CREATE TABLE person (
--     id integer not null,
--     name varchar(255) not null,
--     location varchar(255),
--     birth_date timestamp,
--     primary key(id)
-- );

INSERT INTO person (id, name, location, birth_date) VALUES(10001, 'Ramin', 'goshachay', sysdate());
INSERT INTO person (id, name, location, birth_date) VALUES(10002, 'Amin', 'Alman', sysdate());
INSERT INTO person (id, name, location, birth_date) VALUES(10003, 'Roya', 'Rasht', sysdate());


INSERT INTO course (id, name, last_updated_date, created_date) VALUES(10001, 'Math in 120 Steps', sysdate(), sysdate());
INSERT INTO course (id, name, last_updated_date, created_date) VALUES(10002, 'C++ in 100 Steps', sysdate(), sysdate());
INSERT INTO course (id, name, last_updated_date, created_date) VALUES(10003, 'Python in 56 Steps', sysdate(), sysdate());
INSERT INTO course (id, name, last_updated_date, created_date) VALUES(10004, 'Angular in 100 Steps', sysdate(), sysdate());

INSERT INTO passport (id, number, last_updated_date, created_date) VALUES(40001, 'R123456', sysdate(), sysdate());
INSERT INTO passport (id, number, last_updated_date, created_date) VALUES(40002, 'A123456', sysdate(), sysdate());
INSERT INTO passport (id, number, last_updated_date, created_date) VALUES(40003, 'S123456', sysdate(), sysdate());
INSERT INTO passport (id, number, last_updated_date, created_date) VALUES(40004, 'Y123456', sysdate(), sysdate());

-- SELECT student.id, student.name, student.passport_id, passport.number FROM STUDENT INNER JOIN passport on passport.id = student.passport_id
INSERT INTO student (id, name, passport_id, last_updated_date, created_date) VALUES(20001, 'Ramin', 40001, sysdate(), sysdate());
INSERT INTO student (id, name, passport_id, last_updated_date, created_date) VALUES(20002, 'Amin', 40002, sysdate(), sysdate());
INSERT INTO student (id, name, passport_id, last_updated_date, created_date) VALUES(20003, 'Saber', 40003, sysdate(), sysdate());
INSERT INTO student (id, name, passport_id, last_updated_date, created_date) VALUES(20004, 'Yaser', 40004, sysdate(), sysdate());

INSERT INTO review (id, rating, description, course_id, last_updated_date, created_date) VALUES(50001, '5', 'This is desc of Course 1-1', 10001, sysdate(), sysdate());
INSERT INTO review (id, rating, description, course_id, last_updated_date, created_date) VALUES(50002, '2', 'This is desc of Course 1-2', 10001, sysdate(), sysdate());
INSERT INTO review (id, rating, description, course_id, last_updated_date, created_date) VALUES(50003, '4', 'This is desc of course 3-1', 10003, sysdate(), sysdate());
INSERT INTO review (id, rating, description, course_id, last_updated_date, created_date) VALUES(50004, '5', 'This is desc of course 4-1', 10004, sysdate(), sysdate());
INSERT INTO review (id, rating, description, course_id, last_updated_date, created_date) VALUES(50005, '1', 'This is desc of course 4-2', 10004, sysdate(), sysdate());

-- SELECT student_course.*, course.name, student.name FROM STUDENT_COURSE, student, course where STUDENT_COURSE.student_id=student.id and STUDENT_COURSE.course_id=course.id
INSERT INTO student_course(student_id, course_id) VALUES (20001, 10001);
INSERT INTO student_course(student_id, course_id) VALUES (20001, 10002);
INSERT INTO student_course(student_id, course_id) VALUES (20001, 10004);
INSERT INTO student_course(student_id, course_id) VALUES (20002, 10002);
INSERT INTO student_course(student_id, course_id) VALUES (20002, 10003);
INSERT INTO student_course(student_id, course_id) VALUES (20002, 10004);
INSERT INTO student_course(student_id, course_id) VALUES (20003, 10003);
INSERT INTO student_course(student_id, course_id) VALUES (20003, 10004);
INSERT INTO student_course(student_id, course_id) VALUES (20004, 10001);
INSERT INTO student_course(student_id, course_id) VALUES (20004, 10004);
