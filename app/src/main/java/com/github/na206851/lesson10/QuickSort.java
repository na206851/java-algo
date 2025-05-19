package com.github.na206851.lesson10;

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

    private int division(E[] arr, int leftIndex, int rightIndex) {
        int pivotIndex = leftIndex + (rightIndex - leftIndex) / 2;
        E pivot = arr[pivotIndex];
        swap(arr, pivotIndex, rightIndex);
        int indexInsertion = leftIndex - 1;
        for (int j = leftIndex; j < rightIndex; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                indexInsertion++;
                swap(arr, indexInsertion, j);
            }
        }
        swap(arr, indexInsertion + 1, rightIndex);
        return ++indexInsertion;
    }

    private void swap(E[] arr, int indexInsertion, int j) {
        E tmp = arr[indexInsertion];
        arr[indexInsertion] = arr[j];
        arr[j] = tmp;
    }
}
