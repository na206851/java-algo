package com.github.na206851.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LinkedHashSet_Map {
    @Test
    void add() {
        // в тесте мы видим что linkedHashMap and linkedHashSet хранят только уникальные значения
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        List<String> expectedLinkedHashMap = new ArrayList<>();
        List<Integer> expectedLinkedHashSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i);
            linkedHashSet.add(i);
            expectedLinkedHashMap.add(i + "=" + i);
            expectedLinkedHashSet.add(i);
        }

        //       assertEquals(expectedLinkedHashMap.toString(), linkedHashMap.toString());
        Assertions.assertArrayEquals(expectedLinkedHashSet.toArray(), linkedHashSet.toArray());
        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i);
            linkedHashSet.add(i);
        }
        assertEquals("{0=0, 1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9}", linkedHashMap.toString());
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", linkedHashSet.toString());
    }

    @Test
    void addMathRandom() {
        // в тесте мы видим что при добавлении элементов в мар попадают все элементы из цикла так как у элементов разные ключи
        // в set падают не все элементы так как в таком маленьком диапазоне значения ключей повторяются
        // так как в set под капотом реализация map (которая не хранит одинаковые ключи ) элементы с одним ключем не будут добавлены в set
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int m = (int) (Math.random() * 10);
            list.add(i);
            linkedHashMap.put(i, m);
            linkedHashSet.add(m);
        }
        System.out.println(linkedHashMap + " map, " + linkedHashMap.size() + " size");
        System.out.println(linkedHashSet + " set, " + linkedHashSet.size() + " size");
        System.out.println(list + " list");
        assertNotEquals(linkedHashSet, linkedHashMap.values());
        //самому интересно стало сначала удивился почему так а потом как понял ∆
    }

    @Test
    void addLinkedHashMap() {
        //в тесты мы видим что при добавлении дубликата в с одинаковыми ключами но разными значениями старое значение ключа
        //будет обновляться на новое
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "1");
        linkedHashMap.put(2, "2");
        linkedHashMap.put(3, "3");
        linkedHashMap.put(3, "4");
        List<String> list = List.of("1", "2", "4");
        int i = 1;
        for (String str : list) {
            assertEquals(str, linkedHashMap.get(i++));
        }
    }

    @Test
    void addLinkedHashSet() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("1");
        linkedHashSet.add("2");
        linkedHashSet.add("3");
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Assertions.assertArrayEquals(list.toArray(), linkedHashSet.toArray());
    }

    @Test
    void addLinkedHashSetDuplicate() {
        // в тесты мы наглядно видим что одинаковые элементы не попадают в linked hash set
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("1");
        linkedHashSet.add("2");
        linkedHashSet.add("1");

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        Assertions.assertArrayEquals(list.toArray(), linkedHashSet.toArray());
    }
}