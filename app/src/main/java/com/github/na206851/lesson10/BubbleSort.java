package com.github.na206851.lesson10;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 3, 5};
        int[] arr1 = new int[]{5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(bubbleSort(arr)));
        System.out.println(Arrays.toString(bubbleSort(arr1)));
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

