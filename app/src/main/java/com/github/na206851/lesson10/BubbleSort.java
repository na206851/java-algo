package com.github.na206851.lesson10;

public class BubbleSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public E[] sort(E[] arr) {
        return bubbleSort(arr);
    }

    public E[] bubbleSort(E[] arr) {
        boolean replacement = true;
        while (replacement != false) {
            replacement = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i);
                    replacement = true;
                }
            }
        }
        return arr;
    }

    public void swap(E[] arr, int i) {
        E tmp = arr[i - 1];
        arr[i - 1] = arr[i];
        arr[i] = tmp;
    }

    }
}

