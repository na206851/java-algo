package com.github.na206851;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task_7Test {

    @Test
    public void arithmeticMethod1() {
        int n1 = 2;
        int m1 = 3;
        int[] expected1 = new int[]{m1, n1};
        int[] actual1 = new Task_7().arithmeticMethodSwapDigits1(n1, m1);
        Assertions.assertArrayEquals(expected1, actual1);

        int n2 = 0;
        int m2 = 4;
        int[] expected2 = new int[]{m2, n2};
        int[] actual2 = new Task_7().arithmeticMethodSwapDigits1(n2, m2);
        Assertions.assertArrayEquals(expected2, actual2);

        int n3 = -1;
        int m3 = 2;
        int[] expected3 = new int[]{m3, n3};
        int[] actual3 = new Task_7().arithmeticMethodSwapDigits1(n3, m3);
        Assertions.assertArrayEquals(expected3, actual3);
    }

    @Test
    public void arithmeticMethod2() {
        int n1 = 3;
        int m1 = 5;
        int[] expected1 = new int[]{m1, n1};
        int[] actual1 = new Task_7().arithmeticMethodSwapDigits2(n1, m1);
        Assertions.assertArrayEquals(expected1, actual1);

        int n2 = 10;
        int m2 = 5;
        int[] expected2 = new int[]{m2, n2};
        int[] actual2 = new Task_7().arithmeticMethodSwapDigits2(n2, m2);
        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    public void binMethodSwapDigits() {
        int n1 = Integer.parseInt("101", 2);
        int m1 = Integer.parseInt("111", 2);
        int[] expected1 = new int[]{Integer.parseInt("111", 2), Integer.parseInt("101", 2)};
        int[] actual1 = new Task_7().binMethodSwapDigits1(n1, m1);
        Assertions.assertArrayEquals(expected1, actual1);

        int n2 = Integer.parseInt("1011", 2);
        int m2 = Integer.parseInt("1101", 2);
        int[] expected2 = new int[]{Integer.parseInt("1101", 2), Integer.parseInt("1011", 2)};
        int[] actual2 = new Task_7().binMethodSwapDigits1(n2, m2);
        Assertions.assertArrayEquals(expected2, actual2);

        int n3 = Integer.parseInt("111", 2);
        int m3 = Integer.parseInt("000", 2);
        int[] expected3 = new int[]{Integer.parseInt("000", 2), Integer.parseInt("111", 2)};
        int[] actual3 = new Task_7().binMethodSwapDigits1(n3, m3);
        Assertions.assertArrayEquals(expected3, actual3);
    }

    @Test
    public void binMethodSwapDigits2() {
        int n1 = Integer.parseInt("111", 2);
        int m1 = Integer.parseInt("000", 2);
        int[] expected1 = new int[]{m1, n1};
        int[] actual1 = new Task_7().binMethodSwapDigits2(n1, m1);
        Assertions.assertArrayEquals(expected1, actual1);

        int n2 = Integer.parseInt("101", 2);
        int m2 = Integer.parseInt("011", 2);
        int[] expected2 = new int[]{m2, n2};
        int[] actual2 = new Task_7().arithmeticMethodSwapDigits2(n2, m2);
        Assertions.assertArrayEquals(expected2, actual2);

        int n3 = Integer.parseInt("001", 2);
        int m3 = Integer.parseInt("100", 2);
        int[] expected3 = new int[]{m3, n3};
        int[] actual3 = new Task_7().binMethodSwapDigits2(n3, m3);
        Assertions.assertArrayEquals(expected3, actual3);
    }
}