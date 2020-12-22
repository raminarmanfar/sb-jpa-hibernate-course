package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.repository.CourseJpaRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Section9DemoApplication.class)
public class FirstLevelCacheTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Test
    public void test_findById_firstLevelCache() {
        Course course1 = courseJpaRepository.findById(10001L).orElseThrow();
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>> Course By Id 10001: -> {}", course1);
        Course course2 = courseJpaRepository.findById(10001L).orElseThrow();
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>> Same course: -> {}", course2);
    }
}
