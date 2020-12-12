package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Passport;
import com.armanfar.sbjdbcdemo.section5.model.Student;
import com.armanfar.sbjdbcdemo.section5.repository.StudentRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest(classes = Section5DemoApplication.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Transactional
    public void testRetrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20002L);
        logger.info("\n>>> testRetrieveStudentAndPassportDetails - Student -> {}", student);
        logger.info("\n>>> Passport -> {}", student.getPassport());
        Assert.assertEquals("Amin", student.getName());
        Assert.assertEquals("A123456", student.getPassport().getNumber());
    }

    @Test
    @Transactional
    public void testBidirectionalAccessFromPassportToStudent() {
        Passport passport = em.find(Passport.class, 40002L);
        Student student = passport.getStudent();
        logger.info("\n>>> testRetrieveStudentAndPassportDetails - Passport -> {}", passport);
        logger.info("\n>>> Passport -> {}", student);
        Assert.assertEquals("A123456", passport.getNumber());
        Assert.assertEquals("Amin", passport.getStudent().getName());
    }

    @Test
    @Transactional //means that all operations appended to the DB only if all of the transactions are successful.
    public void someTest() {
        Student student = em.find(Student.class,20001L);
        Passport passport = student.getPassport();

        passport.setNumber("R-654321");
        student.setName("Ramin Armanfar");
    }
}
