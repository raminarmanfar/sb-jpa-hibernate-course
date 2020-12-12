package com.armanfar.sbjdbcdemo.junit;

import lombok.Builder;

@Builder
public class MyMath {

    public int sum(int[] numbers) {
        int s = 0;
        for (int num: numbers) {
            s += num;
        }
        return s;
    }

    public double avg(int[] numbers) {
        return sum(numbers) / numbers.length;
    }
}
