package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Student;
import com.armanfar.sbjdbcdemo.section5.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = Section5DemoApplication.class)
public class StudentCourseTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        Student student = studentRepository.findById(20001L);
        logger.info("\n >>>>>>>>>>>>>>> STUDENT(20001) -> {}", student);
        logger.info("\n >>>>>>>>>>>>>>> Student(20001) Courses -> {}", student.getCourses());
    }
}
