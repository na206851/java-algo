package com.github.na206851.lesson6.hashMap;

public interface Map<K, V> {
    void put(K key, V Value);

    V remove(K key);

    V get(K key);
}
