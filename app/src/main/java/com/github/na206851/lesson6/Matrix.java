package com.github.na206851.lesson6;

public interface Matrix<V> {
    V get(int i, int j);

    void set(int i, int j, V value);
}