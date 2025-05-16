package com.github.na206851.lesson10;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

public class PerformanceTest {
    @Test
    public void test() {
        Consumer<Sort<Integer>> SortConsumer = (Sort<Integer> testSort) -> {
            Integer[] arr = new Integer[10_000];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;

            }
            long start = (int) System.nanoTime();
            testSort.sort(arr);
            long end = (int) System.nanoTime();
            long result = end - start;
            System.out.println(result);
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new QuickSort<>());
        SortConsumer.accept(new MergeSort<>());
    }

}
