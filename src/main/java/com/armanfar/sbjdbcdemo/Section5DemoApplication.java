package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section5.model.Passport;
import com.armanfar.sbjdbcdemo.section5.model.Student;
import com.armanfar.sbjdbcdemo.section5.repository.CourseRepository;
import com.armanfar.sbjdbcdemo.section5.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Section5DemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Section5DemoApplication.class, args);
        System.out.println("Spring boot section 5 started at port 8081...");
    }

    @Override
    public void run(String... args) {
/*
        logger.info("\n>>> All Courses -> {}", courseRepository.findAll());
        logger.info("\n>>> Course with id: 10001 -> {}", courseRepository.findById(10001L));

        courseRepository.playWithEm();
        logger.info("\n --->>> All Courses: -> {}", courseRepository.findAll());

        Student student = Student.builder().name("Milad").build();
        Passport passport = Passport.builder().number("M10000").build();
        studentRepository.save(student, passport);
*/
        courseRepository.testTransactional();
    }
}
