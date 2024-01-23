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

    public int expressionG(int a, int n) {
        int mask = (1 << n) - 1;
        return a & mask;
    }

    public int expressionH(int a, int n) {
        return a & 1 << (n - 1);
    }

    public String expressionI(byte a) {
        StringBuilder result = new StringBuilder();
        while (a > 0) {
            result.append(a & 1);
            a = (byte) (a >> 1);
        }
        return result.reverse().toString();
    }
}
