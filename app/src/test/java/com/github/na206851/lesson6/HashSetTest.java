package com.github.na206851.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    @Test
    void add() {
        Consumer<HashSet<Integer>> listConsumer = (HashSet<Integer> set) -> {

            for (int i = 0; i < 5; i++) {
                set.add(i);
            }
            assertEquals("[0, 1, 2, 3, 4]", set.toString());
            assertEquals(5, set.size());
        };
        listConsumer.accept(new HashSet<>());
        listConsumer.accept(new LinkedHashSet<>());
    }

    @Test
    void sizeTest() {
        Consumer<HashSet<Integer>> listConsumer = (HashSet<Integer> set) -> {
            set.add(1);
            assertEquals("[1]", set.clone().toString());
            set.add(1);
            assertEquals(1, set.size());
            assertFalse(false, String.valueOf(set.add(1)));
        };
        listConsumer.accept(new HashSet<>());
        listConsumer.accept(new LinkedHashSet<>());
    }

    @Test
    void doublyAddValue() {
        Consumer<HashSet<Integer>> listConsumer = (HashSet<Integer> set) -> {
            for (int i = 0; i < 10; i++) {
                set.add(i);
            }
            for (int i = 0; i < 10; i++) {
                set.add(i);
            }
            assertEquals(10, set.size());
        };
        listConsumer.accept(new HashSet<>());
        listConsumer.accept(new LinkedHashSet<>());
    }

    @Test
    void removeTest() {
        Consumer<HashSet<Integer>> listConsumer = (HashSet<Integer> set) -> {
            for (int i = 0; i < 10; i++) {
                set.add(i);
            }
            assertFalse(false, String.valueOf(set.remove(10)));
            assertTrue(true, String.valueOf(set.remove(0)));
            assertEquals(9, set.size());

            for (int i = set.size(); i > 0; i--) {
                set.remove(i);
            }
            assertEquals(0, set.size());
        };
        listConsumer.accept(new HashSet<>());
    }

    @Test
    void removeUncorrectValues() {
        Consumer<HashSet<Integer>> listConsumer = (HashSet<Integer> set) -> {
            for (int i = 0; i < 10; i++) {
                set.add(i);
            }
            assertFalse(false, String.valueOf(set.remove(100)));
            assertFalse(false, String.valueOf(set.remove(-1)));
            assertTrue(true, String.valueOf(set.remove(1)));
        };
        listConsumer.accept(new HashSet<>());
    }

    @Test
    void addAllTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }

        Assertions.assertArrayEquals(list.toArray(), set.toArray());
        assertEquals(10, set.size());
        assertEquals(list.size(), set.size());
    }

    @Test
    void addAllLinkedList() {
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(String.valueOf(i));
        }

        HashSet<Object> set = new HashSet<>();
        assertTrue(set.addAll(linkedList));
        assertEquals(10, set.size());
    }

//    @Test
//    void testAddNode() {
//        HashSet<Node> set = new HashSet<>();
//        int i = 0;
//        Node<Integer, String> node = new Node<>(0, String.valueOf(0));
//        while (i < 5) {
//            node.next = new Node<>(i, String.valueOf(i));
//            node = node.next;
//            set.add(node);
//            i++;
//        }
//        System.out.println(set + " это ноды в set");
//
//        int j = 0;
//        List<Node> list = new LinkedList<>();
//
//        while (j < 5) {
//            list.add(node);
//            node.next = new Node<>(j, String.valueOf(j));
//            node = node.next;
//            j++;
//        }
//        System.out.println(list + " это коллекция нод в связном списке ");
//
//        set.addAll(list);      //добавляем в HashSet связный список нод
//
//        StringBuilder tmp = new StringBuilder();
//        for (Object o : set) {
//            tmp.append(o + ", ");
//        }
//        System.out.println(tmp);
//    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + "/" + value;
        }
    }
}
