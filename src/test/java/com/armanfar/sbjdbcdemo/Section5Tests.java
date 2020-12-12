package com.armanfar.sbjdbcdemo;


import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.*;

@SpringBootTest(classes = Section5DemoApplication.class)
public class Section5Tests {

    @Autowired
    CourseRepository courseRepository;

    private final int initialRecordsCount = 6;

    @Test
    public void testFindCourses() {
        assertEquals(initialRecordsCount, courseRepository.findAll().size());
        assertEquals("Math in 120 Steps", courseRepository.findById(10001L).getName());
    }

    @Test
    @DirtiesContext
    public void testDeleteById() {
        System.out.println(courseRepository.findAll());
        courseRepository.deleteById(10001L);
        assertNull(courseRepository.findById(10001L));
    }

    @Test
    @DirtiesContext
    public void testAddCourses() {
        String newCourseName = "C Sharp";
        assertEquals(newCourseName, courseRepository.save(Course.builder().name(newCourseName).build()).getName());
        assertEquals(initialRecordsCount + 1, courseRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    public void testUpdateCourses() {
        String updatedCourseName = "HTML";
        Course currentCourse = courseRepository.findById(10002L);
        String currentCourseName = currentCourse.getName();
        currentCourse.setName(updatedCourseName);

        Course updatedCourse = courseRepository.save(currentCourse);
        assertEquals(updatedCourseName, updatedCourse.getName());
        assertNotEquals(currentCourseName, currentCourse.getName());
        assertEquals(initialRecordsCount, courseRepository.findAll().size());
    }

    @Test
    public void testPlayWithEm() {
        courseRepository.playWithEm();
    }
}
