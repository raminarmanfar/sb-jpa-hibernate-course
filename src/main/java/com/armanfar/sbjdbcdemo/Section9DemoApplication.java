package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.section9.Employee;
import com.armanfar.sbjdbcdemo.section9.EmployeeRepository;
import com.armanfar.sbjdbcdemo.section9.FullTimeEmployee;
import com.armanfar.sbjdbcdemo.section9.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;


@SpringBootApplication
public class Section9DemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Section9DemoApplication.class, args);
        System.out.println("Spring boot section 9 started at port 8081...");
    }

    @Override
    public void run(String... args) {
        employeeRepository.insert(FullTimeEmployee.builder().name("Ramin Armanfar").salary(new BigDecimal(10000)).build());
        employeeRepository.insert(PartTimeEmployee.builder().name("Ramin Armanfar").hourlyWage(new BigDecimal(50)).build());

        // logger.info("\n>>>>>>>>>>>>>> ALL EMPLOYEES -> {}", employeeRepository.retrieveAllEmployees());
        logger.info("\n>>>>>>>>>>>>>> ALL FULL TIME EMPLOYEES -> {}", employeeRepository.retrieveFullTimeEmployees());
        logger.info("\n>>>>>>>>>>>>>> ALL PART TIME EMPLOYEES -> {}", employeeRepository.retrievePartTimeEmployees());
    }
}
