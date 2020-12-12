package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.model.Student;
import com.armanfar.sbjdbcdemo.section5.repository.CourseRepository;
import com.armanfar.sbjdbcdemo.section5.repository.StudentRepository;
import org.junit.Assert;
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

    @Autowired
    CourseRepository courseRepository;

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        Student student = studentRepository.findById(20001L);
        logger.info("\n >>>>>>>>>>>>>>> STUDENT(20001) -> {}", student);
        logger.info("\n >>>>>>>>>>>>>>> STUDENT(20001) Courses -> {}", student.getCourses());
    }

    @Test
    @Transactional
    public void retireveStudentsOFACourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("\n >>>>>>>>>>>>>>> COURSE(10001) -> {}", course);
        logger.info("\n >>>>>>>>>>>>>>> COURSE(10001) Students -> {}", course.getStudents());
    }

    @Test
    @Transactional
    public void addNewCourseToAStudent() {
        studentRepository.addCourse(20004L, 10004L);

        Student student = studentRepository.findById(20004L);
        String courseName = courseRepository.findById(10004L).getName();
        boolean courseIsExists = student.getCourses().stream().anyMatch(item -> item.getName().equals(courseName));
        Assert.assertTrue(courseIsExists);
        logger.info("\n >>>>>>>>>>>>>>> STUDENT(20004) Courses -> {}", student.getCourses());
    }
}
