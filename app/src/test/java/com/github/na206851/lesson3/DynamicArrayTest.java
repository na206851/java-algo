package com.github.na206851.lesson3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DynamicArrayTest {

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
        List<Integer> jdkList = new ArrayList<>();
        jdkList.add(1);
        Assertions.assertEquals(1, jdkList.get(0));
        jdkList.add(2);
        Assertions.assertEquals(2, jdkList.get(1));


        List<Integer> myList = new DynamicArray<>();
        myList.add(1);
        Assertions.assertEquals(1, myList.get(0));
        myList.add(2);
        Assertions.assertEquals(2, myList.get(1));
    }

    @Test
    void testForToArrayMethod() {
        List<Integer> jdkList = new ArrayList<>();
        List<Integer> myList = new DynamicArray<>();

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
