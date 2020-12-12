package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.jdbc.Person;
import com.armanfar.sbjdbcdemo.jdbc.PersonJdbcDao;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SbJdbcDemoApplication implements CommandLineRunner {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(SbJdbcDemoApplication.class, args);
        System.out.println("Spring boot started at port 8081...");
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        logger.info("\n>>> All Users -> {}", personJdbcDao.findAll());
        logger.info("\n>>> Find person with id: 10001 -> {}", personJdbcDao.findById(10001));
        logger.info("\n>>> Deleting person with id: 10002 -> {}", personJdbcDao.deleteById(10002));
        logger.info("\n>>> Insert new person with id: 10004 -> {}", personJdbcDao.insert(new Person(10004, "Akbar", "tehran", new Date())));
        logger.info("\n>>> Update the person with id: 10003 -> {}", personJdbcDao.update(new Person(10003, "Kamal", "shoolivand", new Date())));
        logger.info("\n>>> All Users -> {}", personJdbcDao.findAll());
        logger.info("\n>>> Delete all Users -> {}", personJdbcDao.deleteAll());
        logger.info("\n>>> All Users -> {}", personJdbcDao.findAll());
        */
    }
}
