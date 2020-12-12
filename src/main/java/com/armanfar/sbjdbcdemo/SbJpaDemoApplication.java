package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.jpa.Person;
import com.armanfar.sbjdbcdemo.jpa.PersonJpaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SbJpaDemoApplication implements CommandLineRunner {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository personJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SbJpaDemoApplication.class, args);
        System.out.println("Spring boot with JPA repo started at port 8081...");
    }

    @Override
    public void run(String... args) throws Exception {
/*
        Person personToAdd = Person.builder().name("Davar").location("Tovle").birthDate(new Date()).build();
        Person personToUpdate = Person.builder().id(10002).name("Jamooosh").location("Baggg").birthDate(new Date()).build();

        logger.info("\n>>> Person with id: 10002 -> {}", personJpaRepository.findById(10002));
        logger.info("\n>>> insert person with auto id -> {}", personJpaRepository.insert(personToAdd));
        logger.info("\n>>> Update person with id: 10002 -> {}", personJpaRepository.update(personToUpdate));
        personJpaRepository.deleteById(10003);
        logger.info("\n>>> Person with id: 10003 -> {}", personJpaRepository.findById(10003));
        logger.info("\n>>> Show all persons -> {}", personJpaRepository.findAll());
*/
    }
}
