package com.github.na206851.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMatrixTest {
    @Test
    void setTestSparceAbility() {
        MyMatrix<Integer> matrix = new MyMatrix<>(1_000_000);
        matrix.set(0, 0, -1);
        matrix.set(999_999, 999_999, -1);

        assertEquals(-1, matrix.get(0, 0));
        assertEquals(-1, matrix.get(999_999, 999_999));
        assertEquals("row = 0, colon = 0, value = -1\nrow = 999999, colon = 999999, value = -1", matrix.toString());
    }

    @Test
    void setTest() {    //устанавливаем значения через метод set
        MyMatrix<Integer> matrix = new MyMatrix<>(10);
        matrix.set(0, 0, 1);
        assertEquals(1, matrix.get(0, 0));

        matrix.set(0, 0, 100);
        assertEquals(matrix.get(0, 0), 100);
    }

    @Test
    void getTest() {        //пытаемся получить значения которых нет
        MyMatrix<Integer> matrix = new MyMatrix<>(10);
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(11, 1));
        assertNull(matrix.get(0, 1));

        matrix.set(0, 0, 0);
        assertEquals("row = 0, colon = 0, value = 0", matrix.get(0, 0));
    }
}