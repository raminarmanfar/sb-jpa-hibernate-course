package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.repository.CourseJpaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Section9DemoApplication.class)
public class CourseJpaRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Test
    public void testFindAllCourses() {
        List<Course> courses = courseJpaRepository.findAll();
        long count = courseJpaRepository.count();

        logger.info("\n\n========================> All Course -> {}", courses);
        logger.info("\n\n========================> Courses count -> {}", count);
        Assert.assertEquals(6, count);
    }

    @Test
    public void testSortCourses() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        List<Course> courses = courseJpaRepository.findAll(sort);
        long count = courseJpaRepository.count();

        logger.info("\n\n========================> All Course -> {}", courses);
        logger.info("\n\n========================> Courses count -> {}", count);
        Assert.assertEquals(6, count);
    }

    @Test
    @DirtiesContext
    public void testCoursesPagination() {
        for (int i = 0; i < 10; ++i) {
            courseJpaRepository.save(Course.builder().name("Dummy course " + (i + 1)).build());
        }

        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPageCourses = courseJpaRepository.findAll(pageRequest);
        logger.info("\n\n========================> First Page -> {}", firstPageCourses.getContent());
        logger.info("\n\n========================> Page count -> {}", firstPageCourses.getSize());

        Pageable secondPageable = firstPageCourses.nextPageable();
        Page<Course> secondPageCourses = courseJpaRepository.findAll(secondPageable);

        logger.info("\n\n========================> Second Page -> {}", secondPageCourses.getContent());
    }

    private Optional<Course> findCourseById(Long id) {
        Optional<Course> optionalCourse = courseJpaRepository.findById(id);
        logger.info("\n\n========================> Is course exists? -> {}", optionalCourse.isPresent());
        return optionalCourse;
    }

    @Test
    public void testFindByIdIsPresent() {
        Optional<Course> optionalCourse = findCourseById(10001L);
        if (optionalCourse.isPresent()) {
            logger.info("\n\n========================> Course is -> {}", optionalCourse.get());
        } else {
            logger.error("\n\n ==========> Course is not exists.");
        }
    }

    @Test
    public void testFindByIdIsNotPresent() {
        Optional<Course> optionalCourse = findCourseById(20001L);
        if (optionalCourse.isPresent()) {
            logger.info("\n\n========================> Course is -> {}", optionalCourse.get());
        } else {
            logger.error("\n\n ==========> Course is not exists.");
        }
    }

    @Test
    @DirtiesContext
    public void testSaveNewCourse() {
        long count = courseJpaRepository.count();
        Assert.assertEquals(6, count);

        courseJpaRepository.save(Course.builder().name("New Course").build());

        count = courseJpaRepository.count();
        Assert.assertEquals(7, count);
        List<Course> courses = courseJpaRepository.findAll();
        logger.info("\n\n========================> All Course -> {}", courses);
        logger.info("\n\n========================> Courses count -> {}", count);
    }

    @Test
    public void testFindCoursesByName() {
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>> Find Course By Name -> {}", courseJpaRepository.findByName("Python in 56 Steps"));
    }

    @Test
    public void testQueries() {
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>> Find All Course Ordered Id -> {}", courseJpaRepository.findAllByOrderByIdDesc());
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>> Find Top 4 Course Ordered Id -> {}", courseJpaRepository.findTop4ByOrderByIdDesc());
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>> Find using Query -> {}", courseJpaRepository.courseWith100StepsInName());
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>> Find using Named Query -> {}", courseJpaRepository.courseWith100StepsInNameWithNamedQuery());
        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>> Find using native Query -> {}", courseJpaRepository.courseWith100StepsInNameWithNativeQuery());
    }
}
