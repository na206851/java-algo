package com.github.na206851.lesson1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task_8Test {

    @Test
    public void expressionTypeIntroduction() {
        int expected = 0b11111111_11111111_11111111_11111110;
        int actual = (int) (short) -2;
        Assertions.assertEquals(expected, actual);
    }
}
