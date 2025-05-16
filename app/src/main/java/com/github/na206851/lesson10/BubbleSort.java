package com.github.na206851.lesson10;

public class BubbleSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public E[] sort(E[] arr) {
        return bubble(arr);
    }

    private E[] bubble(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    E tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
}

