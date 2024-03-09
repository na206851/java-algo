package com.github.na206851.lesson3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

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
            list.remove(3);
            assertEquals(3, list.size());
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void removeTestMethod2() {
        Consumer<List<Integer>> listConsumer1 = (List<Integer> list1) -> {
            for (int i = 0; i < 6; i++) {
                list1.add(i);
            }
            list1.remove(5);
            list1.remove(0);
            assertEquals(1, list1.get(0));
            assertEquals(2, list1.get(1));
            assertEquals(3, list1.get(2));
            list1.remove(1);
            assertEquals(3, list1.get(1));
            assertEquals(3, list1.size());
        };
        listConsumer1.accept(new ArrayList<>());
        listConsumer1.accept(new DynamicArray<>());
    }

    @Test
    void setTestMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 6; i++) {
                list.add(i);
            }
            list.set(0, 9);
            assertEquals(9, list.get(0));
            list.set(5, 0);
            assertEquals(0, list.get(5));
            list.set(3, 0);
            assertEquals(0, list.get(3));
            assertThrows(IndexOutOfBoundsException.class, () -> list.set(10, -1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, -1));

        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testGetMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(3);
            list.add(5);
            assertEquals(1, list.get(0));
            assertEquals(3, list.get(1));
            assertEquals(5, list.get(2));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(9));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void AddAllForIndexTestMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            List<Integer> example = new ArrayList<>();
            int j = -1;
            for (int i = 0; i < 5; i++) {
                example.add(j);
                list.add(i);
            }
            list.addAll(0, example);
            assertEquals(10, list.size());
        };
        listConsumer.accept(new ArrayList<>());
         listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void AddAllFforIndexTestMethod2() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            List<Integer> example = new ArrayList<>();
            int j = 0;
            example.add(0);

            for (int i = 0; i < 3; i++) {
                list.add(i);
            }
            list.addAll(0, example);
            assertEquals(0, list.get(0));
            assertEquals(0, list.get(1));
            assertEquals(1, list.get(2));
            assertEquals(2, list.get(3));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void indexOfMethodTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(1);
            list.add(1);
            list.indexOf(1);
            assertEquals(0, list.indexOf(1));
            assertEquals(-1, list.indexOf(2));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void lastIndexOfMethodTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            assertEquals(2, list.lastIndexOf(3));
            assertEquals(-1, list.lastIndexOf(5));
            assertEquals(0, list.lastIndexOf(1));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void containsAllMethodTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            List example = new ArrayList<>();
            example.add(2);
            example.add(1);
            example.add(3);
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            assertTrue(list.containsAll(example));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void sublistTestMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            list.subList(0, 3);

        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void retainAllTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            ArrayList example = new ArrayList();
            example.addAll(Arrays.asList(1, 2, 3));
            list.addAll(Arrays.asList(1, 2, 3, 4, 5));
            list.retainAll(example);

            assertEquals(3, list.size());
            assertEquals(example.get(0), list.get(0));
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
            assertEquals(3, example.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

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
