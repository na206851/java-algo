package com.github.na206851;

public class Task_7 {
    public static void main(String[] args) {
        Task_7 test = new Task_7();
        test.arithmeticMethodSwapDigits1(1, 2);
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

    public int[] binMethod1SwapDigits(int n, int m) {
        n = n ^ m;
        m = n ^ m;
        n = m ^ n;
        return new int[]{n, m};
    }
}
