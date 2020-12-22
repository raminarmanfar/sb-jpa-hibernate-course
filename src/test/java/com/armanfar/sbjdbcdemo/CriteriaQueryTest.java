package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.model.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest(classes = Section9DemoApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void test_criteria_query_get_all_courses() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Courses -> {}", courses);
    }

    @Test
    @Transactional
    public void test_criteria_query_with_condition() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate likeCondition = cb.like(courseRoot.get("name"), "%100 Steps%");
        cq.where(likeCondition);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Courses -> {}", courses);
    }

    @Test
    @Transactional
    public void test_criteria_query_courses_without_students() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
        cq.where(studentsIsEmpty);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Courses -> {}", courses);
    }

    @Test
    @Transactional
    public void test_criteria_query_join_courses_with_students() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        courseRoot.join("students");
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> courses = query.getResultList();

        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> Total Courses found -> {}", courses.size());
        for(Course course: courses) {
            logger.info("\n>>>>>>>>>>>>>> Courses -> {} -> students taken the course -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void test_criteria_query_left_join_courses_with_students() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        courseRoot.join("students", JoinType.LEFT);
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> courses = query.getResultList();

        logger.info("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> Total Courses found -> {}", courses.size());
        for(Course course: courses) {
            logger.info("\n>>>>>>>>>>>>>> Courses -> {} -> students taken the course -> {}", course, course.getStudents());
        }
    }
}
