package com.github.na206851.lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BubbleSortTest {
    @Test
    public void testSort() {
        BubbleSort sort = new BubbleSort();
        int[] arr = new int[]{1, 2, 3, 5, 4};

        int[] exp = new int[]{1, 2, 3, 4, 5};
        int[] act = sort.bubbleSort(arr);
        assertArrayEquals(exp, act);
    }

    @Test
    public void testSortNegativeNumber() {
        BubbleSort sort = new BubbleSort();
        int[] arr = new int[]{-1, -2, -3, -4, -5};

        int[] exp = new int[]{-5, -4, -3, -2, -1};
        int[] act = sort.bubbleSort(arr);
        assertArrayEquals(exp, act);
    }

    @Test
    public void testSortNegativePositiveNumber() {
        BubbleSort sort = new BubbleSort();
        int[] arr = new int[]{-1, -4, 1, 5, 3};

        int[] exp = new int[]{-4, -1, 1, 3, 5};
        int[] act = sort.bubbleSort(arr);
        assertArrayEquals(exp, act);
    }

    @Test
    public void testDuplicateNumber() {
        BubbleSort sort = new BubbleSort();
        int[] arr = new int[]{4, 4, 1, 2, 5};

        int[] exp = new int[]{1, 2, 4, 4, 5};
        int[] act = sort.bubbleSort(arr);
        assertArrayEquals(exp, act);
    }
}