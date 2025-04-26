package com.github.na206851.lesson10;

public class InsertionSort {

    public int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > tmp) {
                j--;
            }
            arr[j + 1] = tmp;
        }
        return arr;
    }
}

