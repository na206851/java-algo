package com.github.na206851.lesson10;

public class InsertionSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public E[] sort(E[] arr) {
        return insert(arr);
    }

    private E[] insert(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            E tmp = arr[i];
            int j;
            for (j = i - 1; j >= 0 && arr[j].compareTo(tmp) > 0; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
        return arr;
    }
}

