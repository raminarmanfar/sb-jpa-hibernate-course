package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Review;
import com.armanfar.sbjdbcdemo.section5.repository.CourseRepository;
import com.armanfar.sbjdbcdemo.section5.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = Section5DemoApplication.class)
public class CourseAndReviewTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ReviewRepository reviewRepository;


    @Test
    @Transactional
    public void testReadingReviewOfCourse() {
        List<Review> reviews = Arrays.asList(
                Review.builder().description("Desc 2-1").rating("5").build(),
                Review.builder().description("Desc 2-2").rating("4").build(),
                Review.builder().description("Desc 2-3").rating("1").build()
        );
        courseRepository.addReviewForCourse(10002L, reviews);
        logger.info("\n>>>>>>>>> Course (10002) Reviews: -> {}", courseRepository.findById(10002L));
        logger.info("\n>>>>>>>>> Course with id (10002) Reviews: -> {}", courseRepository.findById(10002L).getReviews());
    }

    @Test
    @Transactional
    public void fetchCourseOfReview() {
        Review review = reviewRepository.findById(50003L);
        logger.info("\n>>>>>>>>> Course of the Review with id 50001: {}", review.getCourse());
    }
}
