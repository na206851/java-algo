package com.github.na206851.lesson6.MyMapTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdkHashMapTest {
    HashMap<Integer, Integer> jdk = new HashMap<>();

    @Test
    public void testPut() {
        assertTrue(jdk.isEmpty());
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
            expected.add(i);
        }
        assertFalse(jdk.isEmpty());
        assertEquals(10, jdk.size());
        Assertions.assertArrayEquals(expected.toArray(), jdk.values().toArray());
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
        }
        assertEquals(0, jdk.get(0));
        assertEquals(9, jdk.get(9));
        assertNull(jdk.get(10));
        assertNull(jdk.get(-1));
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
        }

        assertEquals(10, jdk.size());
        jdk.remove(0);

        assertNull(jdk.remove(10));
        assertEquals(9, jdk.size());
    }

    @Test
    public void clearTest() {
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
        }
        assertEquals(10, jdk.size());
        jdk.clear();
        assertEquals(0, jdk.size());
    }

    @Test
    public void containsKeyTest() {
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
        }
        assertTrue(jdk.containsKey(0));
        assertFalse(jdk.containsKey(-1));
    }

    @Test
    public void containsValueTest() {
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
        }
        assertTrue(jdk.containsValue(1));
        assertFalse(jdk.containsValue(100));
    }

    @Test
    public void keySetTest() {
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
            expected.add(i);
        }
        Assertions.assertArrayEquals(expected.toArray(), jdk.keySet().toArray());
        jdk.clear();
        expected.clear();
        Assertions.assertArrayEquals(expected.toArray(), jdk.keySet().toArray());
    }

    @Test
    public void valueSetTest() {
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
            expected.add(i);
        }
        Assertions.assertArrayEquals(expected.toArray(), jdk.values().toArray());
    }

    @Test
    public void entrySetTest() {
        List<String> expected = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            jdk.put(i, i);
            expected.add(i + "=" + i);
        }
        assertEquals(expected.toString(), jdk.entrySet().toString());

        jdk.clear();
        expected.clear();
        Assertions.assertArrayEquals(expected.toArray(), jdk.entrySet().toArray());
    }
}