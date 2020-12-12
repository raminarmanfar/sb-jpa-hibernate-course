package com.armanfar.sbjdbcdemo;

import com.armanfar.sbjdbcdemo.junit.MyMath;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class MyMathTest {

    @Before
    public void before() {
        System.out.println(">>> Before");
    }

    @After
    public void after() {
        System.out.println(">>> After");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println(">>> Before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println(">>> After class");
    }

    @Test
    public void test3Sum() {
        System.out.println(">>> test3Sum");
        MyMath myMath = MyMath.builder().build();
        assertEquals(6, myMath.sum(new int[]{ 1, 2, 3 }));

    }

    @Test
    public void test1NumberSum() {
        System.out.println(">>> test1NumberSum");

        MyMath myMath = MyMath.builder().build();
        assertEquals(3, myMath.sum(new int[]{ 3 }));
    }

    @Test
    public void test3NumbersAvg() {
        System.out.println(">>> test3NumbersAvg");

        MyMath myMath = MyMath.builder().build();
        assertEquals(2, myMath.avg(new int[]{ 1, 2, 3 }), 0);
    }
}
