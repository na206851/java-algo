package com.github.na206851.lesson3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SpeedTest {

    @Test
    void addOneIteration() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {

            long start = System.nanoTime();
            list.add(1);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " add one operation");
        };
        //listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

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
        //listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addAllInTheEnd() { //конец списка
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> { //измерение в нано секундах
            ArrayList<Integer> in = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
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
    void addAllBegginIndex() { //начало списка
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            ArrayList<Integer> in = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
            for (int j = 0; j < 1000; j++) {
                in.add(j);
            }

            long start = System.nanoTime();
            list.addAll(0, in);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " speed addAll no index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addAllMiddleIndex() { //середина списка
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            ArrayList<Integer> in = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
            for (int j = 0; j < 1000; j++) {
                in.add(j);
            }

            long start = System.nanoTime();
            list.addAll(500_000, in);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " speed addAll no index");
        };
        listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addIndex() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {

            long start = System.nanoTime();
            for (int i = 0; i < 10_000; i++) {
                list.add(0, i);
            }
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " time add(int index)");
        };
        //listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void addIndexOneIteration() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.nanoTime();
            list.add(500_000, -1);
            long end = System.nanoTime();

            long result = end - start;
            System.out.println(result + " time add(int index)");
        };
        listConsumer.accept(new ArrayList<>());
        // listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void removeOneIteration() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.currentTimeMillis();
            list.remove(500_000);
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " remove speed");
        };
        // listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }

    @Test
    void removeMillionIteration() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }

            long start = System.currentTimeMillis();
            for (int i = 999_999; i >= 0; i--) {
                list.remove(i);
            }
            long end = System.currentTimeMillis();

            long result = end - start;
            System.out.println(result + " remove speed");
        };
        // listConsumer.accept(new ArrayList<>());
        listConsumer.accept(new DynamicArray<>());
    }
}