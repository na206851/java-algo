package com.github.na206851;

import java.util.Arrays;

public class Task_7 {
    public static void main(String[] args) {
        Task_7 test = new Task_7();
        System.out.println(Arrays.toString(test.binMethodSwapDigits2(Integer.parseInt("111", 2), Integer.parseInt("001", 2))));
    }

    public int[] arithmeticMethodSwapDigits1(int n, int m) {
        n = n + m;
        m = n - m;
        n = n - m;
        return new int[]{n, m};
    }

    public int[] arithmeticMethodSwapDigits2(int n, int m) {
        n = n * m;
        m = n / m;
        n = n / m;
        return new int[]{n, m};
    }

    public int[] binMethodSwapDigits1(int n, int m) {
        n = n ^ m;
        m = n ^ m;
        n = m ^ n;
        return new int[]{n, m};
    }

    public int[] binMethodSwapDigits2(int n, int m) {
        n = (n & m) + (n | m);
        m = n + (~m) + 1;
        n = n + (~m) + 1;
        return new int[]{n, m};
    }
}
