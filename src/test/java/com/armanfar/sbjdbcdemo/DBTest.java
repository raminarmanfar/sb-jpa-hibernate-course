package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.jpa.PersonJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = SbJpaDemoApplication.class)
public class DBTest {

    @Autowired
    private PersonJpaRepository personJpaRepository;

    @Test
    public void testDB() {
        assertEquals(3, personJpaRepository.findAll().size());
    }

}
