package com.github.na206851.lesson10;

public class BubbleSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public E[] sort(E[] arr) {
        return bubbleSort(arr);
    }

    public static int[] bubbleSort(int[] arr) {
        boolean replacement = true;
        while (replacement != false) {
            replacement = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i);
                    replacement = true;
                }
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i) {
        int tmp = arr[i - 1];
        arr[i - 1] = arr[i];
        arr[i] = tmp;

    }
}

