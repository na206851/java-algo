package com.github.na206851.lesson3;

import org.junit.jupiter.api.Test;

import java.util.*;
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
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);

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
            List<Integer> example = new ArrayList<Integer>();
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
    void sublist() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);

            List<Integer> sublist = list.subList(0, 3);
            assertEquals(list.get(0), sublist.get(0));
            assertEquals(list.get(1), sublist.get(1));
            assertEquals(list.get(2), sublist.get(2));
            assertEquals(3, sublist.size());
            assertThrows(IndexOutOfBoundsException.class, () -> sublist.get(3));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void retainAllTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            ArrayList<Integer> example = new ArrayList<>(Arrays.asList(1, 2, 3));
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

    @Test
    void addToEmptyList2() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(0, 2);

            assertEquals(2, list.get(0));
            assertEquals(1, list.get(1));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testMethodNextWithIterator() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test = list.listIterator(0);

            assertEquals(1, test.next());
            assertEquals(2, test.next());
            assertEquals(3, test.next());
            assertFalse(test.hasNext());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testPreviousMethodWithIterator() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test = list.listIterator();
            ListIterator<Integer> testForIndex = list.listIterator(2);

            assertFalse(test.hasPrevious());

            assertTrue(testForIndex.hasPrevious());
            assertEquals(2, testForIndex.previous());
            assertEquals(1, testForIndex.previous());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testNextAndPreviousIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test = list.listIterator();

            assertEquals(0, test.nextIndex());
            assertEquals(1, test.next());
            assertEquals(1, test.nextIndex());
            assertEquals(2, test.next());
            assertEquals(2, test.nextIndex());

            ListIterator<Integer> testPrevious = list.listIterator(2);
            assertEquals(1, testPrevious.previousIndex());

        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testNextIndexAfterDelete() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> iterator = list.listIterator();

            iterator.next();
            iterator.remove();

            assertEquals(0, iterator.nextIndex());
            assertEquals(2, iterator.next());
            assertTrue(iterator.hasPrevious());
            assertTrue(iterator.hasNext());

        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testWithSetIterator() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test = list.listIterator();
            ListIterator<Integer> testWithIndex = list.listIterator(0);

            assertThrows(IllegalStateException.class, () -> test.set(-1));

            testWithIndex.next();
            testWithIndex.set(0);
            assertEquals(0, list.get(0));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testListIteratorAdd() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> testAddJdkIt = list.listIterator();

            testAddJdkIt.add(-1);
            testAddJdkIt.next();
            assertEquals(-1, list.get(0));
            assertEquals(4, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testListIteratorAdd2() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test2 = list.listIterator();
            test2.add(-1);
            test2.next();

            assertEquals(4, list.size());
            assertEquals(1, list.get(1));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testListIteratorAdd3() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test3 = list.listIterator();

            test3.add(0);
            test3.next();

            assertEquals(4, list.size());
            assertEquals(1, list.get(1));

            assertEquals(2, test3.next());
        };
        listConsumer.accept((new ArrayList<>()));
        listConsumer.accept((new DynamicArray<>()));
    }

    @Test
    public void testIteratorRemoveMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test1 = list.listIterator();
            list.remove(0);

            assertEquals(2, list.size());
            assertEquals(2, list.get(0));
            assertEquals(3, list.get(1));
            assertTrue(test1.hasNext());

            ListIterator<Integer> newIterator = list.listIterator();
            assertEquals(2, newIterator.next());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    public void testIteratorRemoveMethodSecondElementRemove() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test2 = list.listIterator();
            list.remove(1);

            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(3, list.get(1));
            assertTrue(test2.hasNext());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    public void testIteratorRemoveMethodLastElementRemove() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test2 = list.listIterator();
            test2.next();
            test2.next();
            test2.next();
            test2.remove();

            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    public void testIteratorRemoveMethodMiddleElementRemove() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> test2 = list.listIterator();
            test2.next();
            test2.next();
            test2.remove();


            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(3, list.get(1));
            assertTrue(test2.hasNext());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testThrowsInRemoveMethod() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {

            ListIterator<Integer> iterator = list.listIterator();
            assertThrows(IllegalStateException.class, () -> iterator.remove());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testToArrayWithType1() {
        Consumer<List<String>> listConsumer = (List<String> list) -> {
            list.add("1");
            list.add("2");
            list.add("3");

            String[] typeString = new String[list.size()];
            list.toArray(typeString);

            assertEquals("1", typeString[0]);
            assertEquals(3, typeString.length);
            assertEquals("3", typeString[2]);
            assertEquals(3, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void testToArrayWithType2() {
        Consumer<List<Employee>> listConsumer = (List<Employee> list) -> {
            Employee employee1 = new Employee("peter");
            Employee employee2 = new Employee("pasha");
            Employee employee3 = new Employee("julia");
            Employee employee4 = new Employee("amur");

            list.add(employee1);
            list.add(employee2);
            list.add(employee3);
            list.add(employee4);

            Employee[] typeEmployee = new Employee[list.size()];
            list.toArray(typeEmployee);

            assertEquals(employee1.toString(), list.get(0).toString());
            assertEquals(employee2.toString(), typeEmployee[1].toString());
            assertEquals(list.get(2).toString(), typeEmployee[2].toString());
            assertEquals(4, list.size());
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    public void removeAll() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            List<Integer> in = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
            assertTrue(list.removeAll(in));
            assertEquals(0, list.size());
        };
        listConsumer.accept(new LinkedList<>());
        listConsumer.accept(new DoublyLinkedList<>());
    }

    @Test
    public void removeAllNegativeExampe() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {

            List<Integer> in = new LinkedList<>(Arrays.asList(1, 2, 3, 4));

            assertFalse(list.removeAll(in));
        };
        listConsumer.accept(new LinkedList<>());
        listConsumer.accept(new DoublyLinkedList<>());
    }

    @Test
    public void removeAllNegativeTest() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            List<Integer> in = new LinkedList<>(Arrays.asList(4, 5, 6));

            assertFalse(list.removeAll(in));
            assertEquals(3, list.size());
        };
        listConsumer.accept(new LinkedList<>());
        listConsumer.accept(new DoublyLinkedList<>());
    }
}
