package com.github.na206851.lesson3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class SpeedTest {
    @Test
    void contatins() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
//            int element = (int) (Math.random() * 99999) + 1;
            int endElement = 99999;
            int middle = 50000;
            int first = 1;
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.currentTimeMillis();
            list.contains(endElement);
            long end = System.currentTimeMillis();
            long result = end - start;
            System.out.println(endElement + " element, " + result + " result speed contains");

        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void toArray() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.currentTimeMillis();
            list.toArray();
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " toArray speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void toArrayCollection() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }

            Integer[] in = new Integer[list.size() + 1];

            long start = System.currentTimeMillis();
            list.toArray(in);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + "toArrayCollection speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void add() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            int count = 0;
            long result = 0;
            while (count < 3) {
                long start = System.currentTimeMillis();

                for (int i = 0; i < 100000; i++) {
                    list.add(i);
                }
                long end = System.currentTimeMillis();

                result += (end - start) / 3;
                count++;
            }
            System.out.println(result + " add test speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addAddNotIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> { //измерение в нано секундах
            ArrayList<Integer> in = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            for (int j = 0; j < 1000; j++) {
                in.add(j);
            }

            long start = System.nanoTime();
            list.addAll(in);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " speed addAll no index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addAllIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            int index = (int) (Math.random() * 1000) + 1;
            ArrayList<Integer> in = new ArrayList<>();

            for (int i = 0, j = 0; i < 100000 || j < 1000; i++, j++) {
                in.add(j);
                list.add(i);
            }

            long start = System.currentTimeMillis();
            list.addAll(index, in);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(index + " index in, " + result + " time addAll(index)");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            int index = (int) (Math.random() * 99999) + 1;
            int in = -1;
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }

            long start = System.currentTimeMillis();
            list.add(index, in);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " time add(int index)" + index + " index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }


    @Test
    void remove() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }

            long start = System.currentTimeMillis();
            for (int i = 99999; i >= 0; i--) {
                list.remove(i);
            }
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " remove speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void removeObject() {
        Consumer<List<String>> listConsumer = (List<String> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add("test");
            }

            long start = System.currentTimeMillis();
            for (int i = 99999; i >= 0; i--) {
                list.remove("test");
            }
            long end = System.currentTimeMillis();
            long result = end - start;

            System.out.println(result + " removeObject speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void removeAll() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            ArrayList<Integer> in = new ArrayList<>();

            for (int i = 0, j = 0; i < 100000 || j < 100000; i++, j += 2) {
                list.add(i);
                in.add(j);
            }

            long start = System.currentTimeMillis();
            list.removeAll(in);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " removeAll speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void retainAll() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            List<Integer> in = new ArrayList<>();
            for (int i = 0, j = 0; i < 100000 || j < 100000; i++, j += 3) {
                list.add(i);
                in.add(j);
            }
            long start = System.currentTimeMillis();
            list.retainAll(in);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " retainAll speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void clear() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.currentTimeMillis();
            list.clear();
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " speed clear");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void get() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
//            int elementGet = (int) (Math.random() * 99999) + 1;
            int elementGet=50000;
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.currentTimeMillis();
            list.get(elementGet);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " result get");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void set() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            int firstElement = 0;
            int middleElement = 50000;
            int endElement = 99999;
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.currentTimeMillis();
            list.set(endElement, -1);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " speed set ");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void indexOf() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.currentTimeMillis();
            list.indexOf(50000);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " indexOf speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void lastIndexOf() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
            long start = System.nanoTime();
            list.lastIndexOf(50000);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " lastIndexOf speed");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }
}
