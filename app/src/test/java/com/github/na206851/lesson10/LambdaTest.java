package com.github.na206851.lesson10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;

public class LambdaTest {
    @Test
    public void sortPositiveNumber() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] exp = new Integer[]{1, 2, 3, 4, 5};
            Integer[] act = testSort.sort(new Integer[]{5, 3, 4, 2, 1});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void sortNegativeNumber() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] exp = new Integer[]{-10, -9, -8, -7, -6, -5, -4, -3, -2, -1};
            Integer[] act = testSort.sort(new Integer[]{-1, -3, -2, -10, -7, -8, -4, -6, -5, -9});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testSortArray() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] exp = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
            Integer[] act = testSort.sort(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testPartSortedArray() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] exp = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
            Integer[] act = testSort.sort(new Integer[]{1, 2, 3, 9, 8, 5, 7, 4, 6});

            Assertions.assertArrayEquals(exp, testSort.sort(act));
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testDuplicateNumber() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] exp = new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
            Integer[] act = testSort.sort(new Integer[]{5, 4, 5, 4, 1, 1, 2, 3, 2, 3});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testIdenticalNumber() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] exp = new Integer[]{5, 5, 5, 5, 5, 5, 5, 5};
            Integer[] act = testSort.sort(new Integer[]{5, 5, 5, 5, 5, 5, 5, 5});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testString() {
        Consumer<Sort<String>> SortConsumer = (Sort<String> testSort) -> {
            String[] exp = new String[]{"a", "b", "c", "d", "e"};
            String[] act = testSort.sort(new String[]{"e", "d", "b", "c", "a"});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testIntegerInViewString() {
        Consumer<Sort<String>> SortConsumer = (Sort<String> testSort) -> {
            String[] exp = new String[]{"1", "2", "3", "4", "5"};
            String[] act = testSort.sort(new String[]{"5", "3", "4", "1", "2"});

            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }

    @Test
    public void testIntegerInViewString2() {
        Consumer<Sort<String>> SortConsumer = (Sort<String> testSort) -> {
            String[] exp = new String[]{"aa", "bb", "cc", "dd", "ee"};
            String[] act = testSort.sort(new String[]{"aa", "ee", "bb", "cc", "dd"});
            Assertions.assertArrayEquals(exp, act);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new MergeSort<>());
        SortConsumer.accept(new QuickSort<>());
    }


}
