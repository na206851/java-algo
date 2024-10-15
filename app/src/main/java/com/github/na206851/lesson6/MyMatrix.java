package com.github.na206851.lesson6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyMatrix<V>
        extends HashMap implements Matrix<V> {
    HashMap<Integer, V> matrix;
    List<Pair> list = new ArrayList<>();
    int size;
    int currentSize = 0;

    public MyMatrix(int n) {
        this.matrix = new HashMap<>();
        this.size = n;
    }

    @Override
    public V get(int i, int j) {
        if ((i < 0 || i > size) && (j < 0 || j > size)) {
            throw new IndexOutOfBoundsException("выход за пределы матрицы в методе get");
        }
        return matrix.get(key(i, j));
    }

    @Override
    public void set(int i, int j, V value) {
        if ((i < 0 || i > size) && (j < 0 || j > size)) {
            throw new IndexOutOfBoundsException("выход за пределы матрицы в методе set");
        }
        if (matrix.get(key(i, j)) != null) {
            matrix.remove(key(i, j));
        }
        matrix.put(key(i, j), value);
    }

    private int key(Integer i, Integer j) {
        return i.hashCode() ^ j.hashCode() << 8;
    }

    public static void main(String[] args) {
        MyMatrix<String> arr = new MyMatrix<>(10);
        arr.set(0, 0, "value");
        arr.set(0, 1, "value");
        arr.set(1, 1, "value");
        arr.set(1, 2, "value");
        arr.set(2, 2, "value");
        System.out.println(arr.matrix);
class Pair {
    public final int row;
    public final int colon;

    public Pair(int row, int colon) {
        this.row = row;
        this.colon = colon;
    }

    public int getColon() {
        return this.colon;
    }

    public int getRow() {
        return this.row;
    }
}
