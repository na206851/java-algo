package com.github.na206851.lesson10;

public class QuickSort {
    public int[] sort(int[] arr) {
        int indexInsertion = -1;
        int j = 0;
        return fastSort(arr, indexInsertion, j);
    }

    public int[] fastSort(int[] arr, int indexInsertion, int j) {
        if (indexInsertion < j) {
            int pivotIndex = division(arr, indexInsertion, j);
            fastSort(arr, indexInsertion, pivotIndex - 1);
            fastSort(arr, pivotIndex + 1, arr.length - 1);
        }
        return arr;

    }

    public int division(int[] arr, int left, int right) {
        int pivot = arr[right];
        int indexInsertion = -1;
        for (int j = 0; j < right; j++) {
            if (arr[j] <= pivot) {
                indexInsertion++;
                swap(arr, indexInsertion, j);
            }
        }
        swap(arr, indexInsertion + 1, right);
        return ++indexInsertion;
    }

    public static void swap(int[] arr, int indexInsertion, int j) {
        int tmp = arr[indexInsertion];
        arr[indexInsertion] = arr[j];
        arr[j] = tmp;
    }
}
