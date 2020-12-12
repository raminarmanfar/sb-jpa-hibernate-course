package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.repository.CourseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest(classes = Section5DemoApplication.class)
public class Section5JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testJpqlTyped() {
        TypedQuery<Course> query = em.createNamedQuery("find_all_courses", Course.class);
        List<Course> courseList = query.getResultList();
        Assert.assertEquals(
                courseRepository.findAll().size(),
                courseList.size());
    }

    @Test
    public void testJpqlTypedWhere() {
        TypedQuery<Course> query = em.createNamedQuery("find_all_courses_100_steps", Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("\n>>> All courses with typed query with where: {}", courseList);
    }

    @Test
    public void testNativeQuery() {
        Query query = em.createNativeQuery("select * from Course where id=?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("\n>>> Run native query: {}", resultList);
    }
}
