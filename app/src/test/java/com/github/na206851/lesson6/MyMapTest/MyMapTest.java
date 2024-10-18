package com.github.na206851.lesson6.MyMapTest;

import com.github.na206851.lesson6.hashMap.MyMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyMapTest {
    @Test
    void hashcodeTest() {
        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> jdk = new HashMap<>();
        String o = "polygenelubricants";
        String b = "GydZG_";
        String k = "DESIGNING WORKHOUSES";
        myMap.put(o, 1);
        myMap.put(b, 1);
        myMap.put(k, 1);

        jdk.put(o, 1);
        jdk.put(b, 1);
        jdk.put(k, 1);

        assertEquals(jdk.toString(), myMap.toString());
    }

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
        MyMap<Integer, Integer> myMap = new MyMap<>();
        HashMap<Integer, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            myMap.put(i, i);
            jdkMap.put(i, i);
        }
        assertEquals(jdkMap.get(7), myMap.get(7));
        assertEquals(jdkMap.get(9999), myMap.get(9999));
        assertEquals(jdkMap.get(-1), myMap.get(-1));
        assertNull(myMap.get(-12));
    }

    @Test
    void containsKey() {
        MyMap<Integer, Integer> myMap = new MyMap<>();
        HashMap<Integer, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            myMap.put(i, i);
            jdkMap.put(i, i);
        }
        assertEquals(jdkMap.containsKey(5), myMap.containsKey(5));
        assertEquals(jdkMap.containsKey(-1), myMap.containsKey(-1));
    }

    @Test
    void isEmpty() {
        MyMap<Integer, Integer> myMap = new MyMap<>();
        HashMap<Integer, Integer> jdkMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            myMap.put(i, i);
            jdkMap.put(i, i);
        }
        assertEquals(jdkMap.isEmpty(), myMap.isEmpty());
        jdkMap.clear();
        myMap.clear();
        assertEquals(jdkMap.isEmpty(), myMap.isEmpty());
    }
}