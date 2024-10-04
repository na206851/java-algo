package com.github.na206851.lesson6.MyMapTest;

import com.github.na206851.lesson6.hashMap.MyMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMapTest {
    @Test
    void resizeTest() {
        MyMap<Integer, Integer> myMap = new MyMap<>();
        HashMap<Integer, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            myMap.put(i, i);
            jdkMap.put(i, i);
        }
        assertEquals(jdkMap.toString(), myMap.toString());
        assertEquals(jdkMap.size(), myMap.size());
    }

    @Test
    void put() {
        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            myMap.put(String.valueOf(i), i);
            jdkMap.put(String.valueOf(i), i);
        }
        assertEquals(jdkMap.toString(), myMap.toString());
        assertEquals(jdkMap.get("3"), myMap.get("3"));
    }

    @Test
    void remove() {
        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            myMap.put(String.valueOf(i), i);
            jdkMap.put(String.valueOf(i), i);
        }
        assertEquals(1, myMap.remove("1"));
        assertEquals(1, jdkMap.remove("1"));
        assertEquals(jdkMap.toString(), myMap.toString());
        assertEquals(2, myMap.remove("2"));
        assertEquals(2, jdkMap.remove("2"));
    }

    @Test
    void get() {
        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            myMap.put(String.valueOf(i), i);
            jdkMap.put(String.valueOf(i), i);
        }
        assertEquals(jdkMap.get("7"), myMap.get("7"));
        assertEquals(jdkMap.get("9999"), myMap.get("9999"));
        assertEquals(jdkMap.get("-1"), myMap.get("-1"));
        assertEquals(null, myMap.get("-12"));
    }
}