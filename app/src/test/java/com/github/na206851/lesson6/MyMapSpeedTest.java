package com.github.na206851.lesson6;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class MyMapSpeedTest {
    @Test
    void putMethodSpeed() {
        HashMap<Integer, Integer> jdk = new HashMap<>();
        MyMap<Integer, Integer> myMap = new MyMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            //jdk.put(i, i);
            myMap.put(i, i);
        }
        long end = System.nanoTime();
        long result = end - start;
        System.out.println(result + " put speed");

    }

    @Test
    void removeMethodSpeed() {
        HashMap<Integer, Integer> jdk = new HashMap<>();
        MyMap<Integer, Integer> myMap = new MyMap<>();
        for (int i = 0; i < 100_000; i++) {
            //  jdk.put(i, i);
            myMap.put(i, i);
        }

        long start = System.nanoTime();
        for (int i = 99_999; i >= 0; i--) {
            // jdk.remove(i);
            myMap.remove(i);
        }
        long end = System.nanoTime();

        long result = end - start;
        System.out.println(result + " remove speed");
    }

    @Test
    void getBegginIndexSpeed() {
        HashMap<Integer, Integer> jdk = new HashMap<>();
        MyMap<Integer, Integer> myMap = new MyMap<>();
        for (int i = 0; i < 100_000; i++) {
            //  jdk.put(i, i);
            myMap.put(i, i);
        }
        long start = System.nanoTime();
        //jdk.get(0);
        myMap.get(0);
        long end = System.nanoTime();

        long result = end - start;
        System.out.println(result + " get beggin index");

    }

    @Test
    void getMiddleIndexSpeed() {
        HashMap<Integer, Integer> jdk = new HashMap<>();
        MyMap<Integer, Integer> myMap = new MyMap<>();
        for (int i = 0; i < 100_000; i++) {
            //    jdk.put(i, i);
            myMap.put(i, i);
        }
        long start = System.nanoTime();
        // jdk.get(50000);
        myMap.get(50000);
        long end = System.nanoTime();

        long result = end - start;
        System.out.println(result + " get middle index");

    }
}