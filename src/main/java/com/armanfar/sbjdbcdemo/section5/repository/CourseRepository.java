package com.armanfar.sbjdbcdemo.section5.repository;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Course> findAll() {
        return em.createNamedQuery("find_all_courses", Course.class).getResultList();
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        if(course != null) {
            em.remove(course);
        }
    }

    public void playWithEm() {
        String course1Name = "course 1";
        Course course1 = Course.builder().name(course1Name).build();
        em.persist(course1);
        String course2Name = "course 2";
        Course course2 = Course.builder().name(course2Name).build();
        em.persist(course2);

        em.flush();

        em.detach(course2);
        // em.clear(); // detach everything (course1 & course2).

        course1.setName(course1Name + " updated");
        em.flush();

        course2.setName(course2Name + " updated");
        em.flush();
    }

    @Transactional
    public void testTransactional() {
        List<Review> reviews = Arrays.asList(
                Review.builder().description("Desc 2-1").rating("5").build(),
                Review.builder().description("Desc 2-2").rating("4").build(),
                Review.builder().description("Desc 2-3").rating("1").build()
        );
        addReviewForCourse(10002L, reviews);
        logger.info("\n>>>>>>>>> Course (10002) Reviews: -> {}", findById(10002L));
        logger.info("\n>>>>>>>>> Course with id (10002) Reviews: -> {}", findById(10002L).getReviews());
    }

    public void addReviewForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        for(Review review: reviews) {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }
}
