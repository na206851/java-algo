package com.github.na206851;

public class Task_6 {

    public int expressionA(int n) {
        int a = 1;
        return a << n;
    }

    public int expressionB(int n, int m) {
        int a = 1;
        return (a << n) + (a << m);
    }

    public int expressionC(int a, int n) {
        return a & createMask(n);
    }

    private int createMask(int n) {
        return -1 << n;
    }

    public int expressionD(int a, int n) {
        return a | (1 << n);
    }

    public int expressionE(int a, int n) {
        return a ^ (1 << n);
    }

    public int expressionF(int a, int n) {
        return a & ~(1 << n);
    }

    public int expressionH(int a, int n) {
        return a & 1 << (n - 1);

    }
}
