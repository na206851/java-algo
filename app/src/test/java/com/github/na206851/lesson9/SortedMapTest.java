package com.github.na206851.lesson9;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SortedMapTest {
    SortedMap<Integer, String> map = new TreeMap<>();

    @Test
    public void testFirstKey() {
        //если разные ключи , то возможно хранение одинаковых элементов
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        assertEquals(1, map.firstKey());
        assertEquals("A", map.get(map.firstKey()));
    }

    @Test
    public void testLastKey() {
        //если разные ключи , то возможно хранение одинаковых элементов
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        assertEquals(3, map.lastKey());
        assertEquals("C", map.get(map.lastKey()));
    }

    @Test
    public void testAdd() {
        //происходит сортировка по значению ключа, недопустимы одинаковые ключи,  происходит затирание элемента последним добавленным
        map.put(5, "E");
        map.put(1, "A");
        map.put(3, "C");
        map.put(4, "D");
        map.put(2, "B");

        List<String> expected = List.of("A", "B", "C", "D", "E");
        assertIterableEquals(expected, map.values());
    }

    @Test
    public void testHeadMap() {
        //метод возращает мапу от первого ключа до переданного , граница не входит в диапазон
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        List<String> expected = List.of("A", "B");
        assertIterableEquals(expected, map.headMap(3).values());

        List<String> expectedLimitTop = List.of("A", "B", "C", "D", "E");
        assertIterableEquals(expectedLimitTop, map.headMap(100).values());
    }

    @Test
    public void testTailMap() {
        //метод возвращает мапу от переданного ключа включительно, задает начало матрицы, нижняя (переданная) граница входит в диапазон
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        List<String> expected = List.of("C", "D", "E");
        assertIterableEquals(expected, map.tailMap(3).values());

        List<String> expectedEmptyMap = List.of();
        assertIterableEquals(expectedEmptyMap, map.tailMap(10).values());
    }

    @Test
    public void testSubMap() {
        //возвращает подмапу в границах переданных значений , нижняя граница входит в диапазон, нижняя граница не входит в диапазон
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        List<String> expected = List.of("A", "B", "C");
        assertIterableEquals(expected, map.subMap(1, 4).values());
    }
}
