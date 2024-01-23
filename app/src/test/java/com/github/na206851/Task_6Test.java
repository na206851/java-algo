package com.github.na206851;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task_6Test {

    @Test
    public void expressionA() {
        Task_6 test = new Task_6();

        int actual1 = test.expressionA(2);
        int expected1 = 4;
        Assertions.assertEquals(expected1, actual1);

        int actual2 = test.expressionA(0);
        int expected2 = 1;
        Assertions.assertEquals(expected2, actual2);

        int actual3 = test.expressionA(1);
        int expected3 = 2;
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void expressionB() {
        int expected1 = 8;
        int actual1 = new Task_6().expressionB(2, 2);
        Assertions.assertEquals(expected1, actual1);

        int expected2 = 2;
        int actual2 = new Task_6().expressionB(0, 0);
        Assertions.assertEquals(expected2, actual2);

        int expected3 = 3;
        int actual3 = new Task_6().expressionB(1, 0);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void expressionC() {
        int a1 = Integer.parseInt("011", 2);
        int n1 = 1;
        int expected1 = Integer.parseInt("010", 2);
        int actual1 = new Task_6().expressionC(a1, n1);
        Assertions.assertEquals(expected1, actual1);

        int a2 = Integer.parseInt("111", 2);
        int n2 = 2;
        int expected2 = Integer.parseInt("100", 2);
        int actual2 = new Task_6().expressionC(a2, n2);
        Assertions.assertEquals(expected2, actual2);

        int a3 = Integer.parseInt("010", 2);
        int n3 = 2;
        int expected3 = Integer.parseInt("000", 2);
        int actual3 = new Task_6().expressionC(a3, n3);
        Assertions.assertEquals(expected3, actual3);

        int a4 = Integer.parseInt("1101010", 2);
        int n4 = 2;
        int expected4 = Integer.parseInt("1101000", 2);
        int actual4 = new Task_6().expressionC(a4, n4);
        Assertions.assertEquals(expected4, actual4);

        int a5 = Integer.parseInt("111", 2);
        int n5 = 3;
        int expected5 = Integer.parseInt("000", 2);
        int actual5 = new Task_6().expressionC(a5, n5);
        Assertions.assertEquals(expected5, actual5);
    }

    @Test
    public void expressionD() {
        int a1 = Integer.parseInt("000", 2);
        int n1 = 2;
        int expected1 = Integer.parseInt("100", 2);
        int actual1 = new Task_6().expressionD(a1, n1);
        Assertions.assertEquals(expected1, actual1);

        int a2 = Integer.parseInt("100", 2);
        int n2 = 1;
        int expected2 = Integer.parseInt("110", 2);
        int actual2 = new Task_6().expressionD(a2, n2);
        Assertions.assertEquals(expected2, actual2);

        int a3 = Integer.parseInt("000", 2);
        int n3 = 0;
        int expected3 = Integer.parseInt("001", 2);
        int actual3 = new Task_6().expressionD(a3, n3);
        Assertions.assertEquals(expected3, actual3);

        int a4 = Integer.parseInt("111", 2);
        int n4 = 3;
        int expected4 = Integer.parseInt("1111", 2);
        int actual4 = new Task_6().expressionD(a4, n4);
        Assertions.assertEquals(expected4, actual4);
    }

    @Test
    public void expressionE() {
        int a1 = Integer.parseInt("001", 2);
        int n1 = 0;
        int expected1 = Integer.parseInt("000", 2);
        int actual1 = new Task_6().expressionE(a1, n1);
        Assertions.assertEquals(actual1, expected1);

        int a2 = Integer.parseInt("111", 2);
        int n2 = 2;
        int expected2 = Integer.parseInt("011", 2);
        int actual2 = new Task_6().expressionE(a2, n2);
        Assertions.assertEquals(expected2, actual2);

        int a3 = Integer.parseInt("101", 2);
        int n3 = 0;
        int expected3 = Integer.parseInt("100", 2);
        int actual3 = new Task_6().expressionE(a3, n3);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void expressionF() {
        int a1 = Integer.parseInt("111", 2);
        int n1 = 1;
        int expected1 = Integer.parseInt("101", 2);
        int actual1 = new Task_6().expressionF(a1, n1);
        Assertions.assertEquals(expected1, actual1);

        int a2 = Integer.parseInt("011", 2);
        int n2 = 0;
        int expected2 = Integer.parseInt("010", 2);
        int actual2 = new Task_6().expressionF(a2, n2);
        Assertions.assertEquals(expected2, actual2);

        int a3 = Integer.parseInt("000", 2);
        int n3 = 0;
        int expected3 = Integer.parseInt("000", 2);
        int actual3 = new Task_6().expressionF(a3, n3);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void expressionG() {
        int a1 = Integer.parseInt("101111", 2);
        int n1 = 3;
        int expected1 = Integer.parseInt("111", 2);
        int actual1 = new Task_6().expressionG(a1, n1);
        Assertions.assertEquals(expected1, actual1);

        int a2 = Integer.parseInt("101", 2);
        int n2 = 2;
        int expected2 = Integer.parseInt("01", 2);
        int actual2 = new Task_6().expressionG(a2, n2);
        Assertions.assertEquals(expected2, actual2);

        int a3 = Integer.parseInt("0101", 2);
        int n3 = 3;
        int expected3 = Integer.parseInt("101", 2);
        int actual3 = new Task_6().expressionG(a3, n3);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void expressionH() {
        int a1 = Integer.parseInt("101", 2);
        int n1 = 1;
        int expected1 = Integer.parseInt("1", 2);
        int actual1 = new Task_6().expressionH(a1, n1);
        Assertions.assertEquals(expected1, actual1);

        int a2 = Integer.parseInt("101100", 2);
        int n2 = 2;
        int expected2 = Integer.parseInt("0", 2);
        int actual2 = new Task_6().expressionH(a2, n2);
        Assertions.assertEquals(expected2, actual2);

        int a3 = Integer.parseInt("10101", 2);
        int n3 = 1;
        int expected3 = Integer.parseInt("1", 2);
        int actual3 = new Task_6().expressionH(a3, n3);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void expressionI() {
        byte a1 = 4;
        String expected1 = Integer.toBinaryString(a1);
        String actual1 = new Task_6().expressionI(a1);
        Assertions.assertEquals(expected1, actual1);

        byte a2 = 127;
        String expected2 = Integer.toBinaryString(a2);
        String actual2 = new Task_6().expressionI(a2);
        Assertions.assertEquals(expected2, actual2);

        byte a3 = 1;
        String expected3 = Integer.toBinaryString(a3);
        String actual3 = new Task_6().expressionI(a3);
        Assertions.assertEquals(expected3, actual3);
    }
}