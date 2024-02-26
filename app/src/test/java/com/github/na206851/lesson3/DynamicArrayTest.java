package com.github.na206851.lesson3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DynamicArrayTest {

    @Test
    void sizeTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            assertEquals(1, list.size());
            list.add(2);
            assertEquals(2, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void getFromNonEmptyList() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            assertEquals(1, list.get(0));
            list.add(2);
            assertEquals(2, list.get(1));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }


    @Test
    void getFromEmptyListExeption() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void sizeTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            assertEquals(1, list.size());
            list.add(2);
            assertEquals(2, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testWithContains() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            assertTrue(list.contains(1));
            list.add(2);
            assertTrue(list.contains(2));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void clearTestMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.clear();
            assertEquals(0, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void removeTestMethod1() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 5; i++) {
                list.add(i);
            }
            list.remove(0);
            assertEquals(4, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());

        Assertions.assertArrayEquals(jdkList.toArray(), myList.toArray());
    }

//    @Test
//    void concuredArrayTest() {      //иттерируемся и удаляем элементы , почитать про исключение concurrent nodification exception
//        List<String> jdkList = new ArrayList<>();
//        jdkList.add("a");
//        jdkList.add("b");
//        jdkList.add("c");
//
//        for (String i : jdkList) {
//            jdkList.remove(i);
//        }
//    }
//    @Test
//    public void exapmleTestAddMethod() {
//        ArrayList<Integer> expected1 = new ArrayList<>();
//        expected1.add(1);
//        ArrayList<Object> actual1 = new ArrayList<>().exampleIntegerAdd(1);
//        Assertions.assertArrayEquals(expected1.toArray(), actual1.toArray());
//
//        ArrayList<Object> expected2 = new ArrayList<>();
//        expected2.add(2);
//        ArrayList<Object> actual2 = new ArrayList<>().exampleIntegerAdd(2);
//        Assertions.assertArrayEquals(expected2.toArray(), actual2.toArray());
//
//        ArrayList<Object> expected3 = new ArrayList<>();
//        expected3.add("first");
//        ArrayList<Object> actual3 = new ArrayList<>().exampleIntegerAdd("second");
//        Assertions.assertFalse(Arrays.equals(expected3.toArray(), actual3.toArray()));
//    }
//
//    @Test
//    public void exampleRemoveMethodTest() {
//        ArrayList<Object> expected = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            expected.add(i);
//        }
//
//    }
//
//    @Test
//    public void exampleClearMethodTest() {
//
//    }
}
