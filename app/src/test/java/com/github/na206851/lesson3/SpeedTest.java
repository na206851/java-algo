package com.github.na206851.lesson3;

import com.github.na206851.lesson4.DoublyLinkedList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class SpeedTest {

    @Test
    void addMillionElement() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {

            long start = System.nanoTime();
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " add one million element");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new LinkedList<>());
        listConsumer.accept(new DoublyLinkedList<>());
    }

    @Test
    void addElementMiddleIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
            long start = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                list.add(list.size() / 2, i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " add element in middle index (100 iteration)");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void addElementBeginIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
            long start = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                list.add(0, i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " add one million element");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new LinkedList<>());
        listConsumer.accept(new DoublyLinkedList<>());
    }

    @Test
    void addIndex() {       // add element in zero index
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {

            long start = System.nanoTime();
            for (int i = 0; i < 10_000; i++) {
                list.add(0, i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " time add(int index)");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void removeElementBeginIndex() { //remove element beggin index
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                list.remove(i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " remove begin index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void removeMillionIteration() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            for (int i = 999_999; i >= 0; i--) {
                list.remove(i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " remove speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void removeMiddleIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            for (int i = 1000; i >= 0; i--) {
                list.remove(list.size() / 2);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " remove speed middle index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void getBeginIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            list.get(0);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " speed get element for begin index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void getMiddleIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            list.get(list.size() / 2);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " speed get element for middle index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }

    @Test
    void getFinishIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            list.get(list.size() - 1);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " speed get element for begin index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
        listConsumer.accept(new DoublyLinkedList<>());
        listConsumer.accept(new LinkedList<>());
    }
}