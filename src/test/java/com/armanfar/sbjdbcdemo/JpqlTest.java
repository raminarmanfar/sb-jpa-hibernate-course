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
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = Section9DemoApplication.class)
public class JpqlTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void test_jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Courses not taken -> {}", courses);
    }

    @Test
    @Transactional
    public void test_jpql_course_taken_more_than_one_student() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students.size >= 2", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Courses taken by more than 2 student -> {}", courses);
    }

    @Test
    @Transactional
    public void test_jpql_course_ordered_by_no_of_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by c.students.size desc", Course.class);
        List<Course> courses = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Courses ordered by the students taken it -> {}", courses);
    }

    @Test
    @Transactional
    public void test_jpql_students_with_passport_in_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> students = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Students with passport contain 1234 -> {}", students);
    }

    @Test
    @Transactional
    public void test_join() {
        Query query = em.createQuery("select c, s from Course c join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Students join Course -> {}", resultList.size());

        for(Object[] result: resultList) {
            Course course = (Course) result[0];
            Student student = (Student) result[1];
            logger.info("\n>>>>>>>>>>>>>> {}, {}", course, student);
        }
    }

    @Test
    @Transactional
    public void test_left_join() {
        Query query = em.createQuery("select c, s from Course c left join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("\n>>>>>>>>>>>>>> Students left join Course -> {}", resultList.size());

        for(Object[] result: resultList) {
            Course course = (Course) result[0];
            Student student = (Student) result[1];
            logger.info("\n>>>>>>>>>>>>>> {}, {}", course, student);
        }
    }
}
