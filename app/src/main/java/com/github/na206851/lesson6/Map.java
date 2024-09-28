package com.github.na206851.lesson6;

public interface Map<K, V> {
    void add(K key, V Value);

    Node remove(K key);

    V search(K key);
}
