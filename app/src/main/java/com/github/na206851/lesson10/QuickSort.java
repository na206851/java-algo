package com.github.na206851.lesson10;

public class QuickSort {
    public int[] sort(int[] arr) {
        int indexInsertion = -1;
        int j = 0;
        return fastSort(arr, indexInsertion, j);
import java.util.Arrays;

public class QuickSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public E[] sort(E[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    private E[] quickSort(E[] arr, int indexInsertion, int j) {
        if (indexInsertion < j) {
            int pivotIndex = division(arr, indexInsertion, j);
            quickSort(arr, indexInsertion, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, j);
        }
        return arr;

    }

    private int division(E[] arr, int left, int right) {
        int pivotIndex = left + (right - left) / 2;
        E pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int indexInsertion = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                indexInsertion++;
                swap(arr, indexInsertion, j);
            }
        }
        swap(arr, indexInsertion + 1, right);
        return ++indexInsertion;
    }

    private void swap(E[] arr, int indexInsertion, int j) {
        E tmp = arr[indexInsertion];
        arr[indexInsertion] = arr[j];
        arr[j] = tmp;
    }
}
