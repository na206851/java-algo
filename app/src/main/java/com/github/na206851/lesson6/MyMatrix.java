package com.github.na206851.lesson6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyMatrix<V>
        extends HashMap implements Matrix<V> {
    HashMap<Integer, V> matrix;
    List<Pair> coordinatesOfSetValues = new ArrayList<>();
    int size;
    int currentSize = 0;

    private static class Pair {
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

    public MyMatrix(int n) {
        this.matrix = new HashMap<>();
        this.size = n;
    }

    @Override
    public V get(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("выход за пределы матрицы в методе get");
        }
        return matrix.get(key(i, j));
    }

    @Override
    public void set(int i, int j, V value) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("выход за пределы матрицы в методе set");
        } else {
            if (matrix.containsKey(key(i, j))) {
                matrix.remove(key(i, j));
                matrix.put(key(i, j), value);
            } else {
                matrix.put(key(i, j), value);
                coordinatesOfSetValues.add(new Pair(i, j));
                currentSize++;
            }
        }
    }

    private int key(Integer i, Integer j) {
        return i.hashCode() ^ j.hashCode() << 8;
    }

    public int size() {
        return currentSize;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        int count = 0;
        for (Pair pair : coordinatesOfSetValues) {
            str.append("row = " + pair.getRow() + ", colon = " + pair.getColon() + ", value = " + matrix.get(key(pair.getRow(), pair.getColon())));
            if (count != coordinatesOfSetValues.size() - 1) {
                str.append("\n");
            }
            count++;
        }
        return str.toString();
    }
}