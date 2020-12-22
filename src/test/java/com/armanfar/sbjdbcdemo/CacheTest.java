package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.repository.CourseJpaRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = Section9DemoApplication.class)
public class CacheTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Test
    @Transactional
    public void testSecondLevelCache() {
        Course course1 = courseJpaRepository.findById(10001L).orElseThrow();
        logger.info("\n\n Course 10001: -> {}", course1);
        Course course2 = courseJpaRepository.findById(10001L).orElseThrow();
        logger.info("\n\n Second Fetch of Course 10001: -> {}", course2);
    }
}
