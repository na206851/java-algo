package com.github.na206851.lesson10;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public E[] sort(E[] arr) {
        return installIndex(arr);
    }

    private E[] installIndex(E[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        E[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        E[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        installIndex(left);
        installIndex(right);
        return merge(arr, left, right);
    }

    private E[] merge(E[] result, E[] left, E[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
                result[resultIndex] = left[leftIndex++];
            } else {
                result[resultIndex] = right[rightIndex++];
            }
            resultIndex++;
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
        return result;
    }
}
