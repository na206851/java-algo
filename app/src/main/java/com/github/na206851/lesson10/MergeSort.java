package com.github.na206851.lesson10;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        MergeSort test = new MergeSort();
        int[] arr = new int[]{3, 1, 2, 5, 4};
        test.installIndex(arr);
        System.out.println(Arrays.toString(arr));

    }

    public int[] installIndex(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        installIndex(left);
        installIndex(right);
        return merge(arr, left, right);
    }

    public int[] merge(int[] result, int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
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
