package com.github.na206851.lesson9;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class NavigableMapTest {
    NavigableMap<Integer, String> map = new TreeMap<>();

    @Test
    public void testCeilingEntry() {
        //возвращает элемент который больше или равен передаваемому ключу
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals("C", map.ceilingEntry(3).getValue());
        assertEquals("A", map.ceilingEntry(0).getValue());
        assertNull(map.ceilingEntry(6));
    }

    @Test
    public void testFloorEntry() {
        //возвращает элемент который меньше или равен передаваему ключу
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals("E", map.floorEntry(10).getValue());
        assertNull(map.floorEntry(0));
        assertEquals("D", map.floorEntry(4).getValue());
    }

    @Test
    public void testHigherEntry() {
        //возвращает пару ключ значение больше передаваемого ключа , в противном случае null
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals("B", map.higherEntry(1).getValue());
        assertEquals("E", map.higherEntry(4).getValue());
        assertNull(map.higherEntry(6));
    }

    @Test
    public void testLowerEntry() {
        //возвращает пару ключ-значение строго меньше передаваемого ключа, в противном случае null
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals("A", map.lowerEntry(2).getValue());
        assertEquals("E", map.lowerEntry(12).getValue());
        assertNull(map.lowerEntry(0));
        assertNull(map.lowerEntry(-1));
        assertNull(map.lowerEntry(-2147483648));
    }

    @Test
    public void testFirstEntry() {
        //возвращает первую пару ключ-значение
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals("A", map.firstEntry().getValue());
        assertEquals(1, map.firstEntry().getKey());
    }

    @Test
    public void testLastEntry() {
        //возвращает последнюю пару ключ-значение
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals("E", map.lastEntry().getValue());
        assertEquals(5, map.lastEntry().getKey());
    }

    @Test
    public void testPollFirstEntry() {
        //удаляет первую пару ключ-значение
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals(5, map.size());
        assertEquals("A", map.pollFirstEntry().getValue());
        assertEquals("B", map.firstEntry().getValue());
        assertEquals(4, map.size());
    }

    @Test
    public void testPollLastEntry() {
        //удаляет последнюю пару ключ-значение
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals(5, map.size());
        assertEquals("E", map.pollLastEntry().getValue());
        assertEquals("D", map.lastEntry().getValue());
        assertEquals(4, map.size());
    }

    @Test
    public void testCeilingKey() {
        //возращает ключ по ключу которое больше или равно переданному ключу
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertEquals(2, map.ceilingKey(2));
        assertEquals(1, map.ceilingKey(0));
        assertNull(map.ceilingKey(10));
    }

    @Test
    public void testFloorKey() {
        //воззвращает ключ который больше или равен передаваемому в качестве параметра, в противном случае null
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertNull(map.floorKey(0));
        assertEquals(2, map.floorKey(2));
        assertEquals(5, map.floorKey(10));
    }

    @Test
    public void testLowerKey() {
        //возвращает ключ который строго меньше передавемого значения, в противном случае null
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertNull(map.lowerKey(-1));
        assertNull(map.lowerKey(0));
        assertEquals(5, map.lowerKey(100));
        assertEquals(3, map.lowerKey(4));
    }

    @Test
    public void testHigherKey() {
        //возвращает ключ который строго больше переданного значение, в противном случае null
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        assertNull(map.higherKey(5));
        assertNull(map.higherKey(10));
        assertEquals(1, map.higherKey(-1));
        assertEquals(5, map.higherKey(4));
    }

    @Test
    public void testDescendingKeySet() {
        //возвращает множество ключей в обратном порядке
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        Integer[] expected = {5, 4, 3, 2, 1};
        assertArrayEquals(expected, map.descendingKeySet().toArray(new Integer[0]));
    }

    @Test
    public void testDescendingMap() {
        //возвращает пары ключ-значение в обратном порядке
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> expected = new HashMap<>();
        expected.put(5, "E");
        expected.put(4, "D");
        expected.put(3, "C");
        expected.put(2, "B");
        expected.put(1, "A");

        assertEquals(expected, map);
        assertEquals("E", map.descendingMap().get(5));
    }

    @Test
    public void testNavigableKeySet() { //найти способ сравнить лист и результат 

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        List<Integer> expected = List.of(5, 4, 3, 2, 1);

//        Assertions.assertIterableEquals(expected.toArray(), map.navigableKeySet().toArray());
    }

    @Test
    public void testHeadMap() {
        //возвращает все пары ключ значение, ключ которых строго меньше передаваемого ключа  в виде treeMap
        //по умолчанию верхняя граница  не включена
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> exp = new HashMap<>();
        exp.put(1, "A");
        assertEquals(exp, map.headMap(2));

    }

    @Test
    public void testHeadMapBooleanArgumentChange() {
        //возвращает мапу начиная от первого ключа и до переданного значения, параметр boolean inclusive отвечает за
        //верхнюю границу - true-включительно, false-строго меньше
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> expBooleanIsTrue = new HashMap<>();
        expBooleanIsTrue.put(1, "A");
        expBooleanIsTrue.put(2, "B");
        assertEquals(expBooleanIsTrue, map.headMap(2, true));

        HashMap<Integer, String> expBooleanIsFalse = new HashMap<>();
        expBooleanIsFalse.put(1, "A");
        expBooleanIsFalse.put(2, "B");
        expBooleanIsFalse.put(3, "C");
        expBooleanIsFalse.put(4, "D");
        assertEquals(expBooleanIsFalse, map.headMap(5, false));
    }

    @Test
    public void testTailMap() {
        //метод принимает значение ключа и возвращает hashMap c первым элементом ключ которого больше или равен передаваемому значению
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> expected = new HashMap<>();
        expected.put(3, "C");
        expected.put(4, "D");
        expected.put(5, "E");

        assertEquals(expected, map.tailMap(3));
        assertEquals(3, map.tailMap(3).size());
    }

    @Test
    public void testTailMapBooleanArgumentChange() {
        //метод возвращает мапу в завимости от значения параметра boolean нижная граница true-включени, false-не включена
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> expBooleanTrue = new HashMap<>();
        expBooleanTrue.put(2, "B");
        expBooleanTrue.put(3, "C");
        expBooleanTrue.put(4, "D");
        expBooleanTrue.put(5, "E");
        assertEquals(expBooleanTrue, map.tailMap(2, true));

        HashMap<Integer, String> expBooleanFalse = new HashMap<>();
        expBooleanFalse.put(3, "C");
        expBooleanFalse.put(4, "D");
        expBooleanFalse.put(5, "E");
        assertEquals(expBooleanFalse, map.tailMap(2, false));
    }

    @Test
    public void testSubMap() {
        //метод возвращает map с границами, по умолчанию нижняя граница включена , верхняя не включена
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> exp = new HashMap<>();
        exp.put(1, "A");
        exp.put(2, "B");
        exp.put(3, "C");

        assertEquals(exp, map.subMap(1, 4));
    }

    @Test
    public void testSubMapChangeBooleanParameter() {
        //метод возвращает map с границами, по умолчанию нижняя граница включена , верхняя не включена
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        HashMap<Integer, String> exp = new HashMap<>();
        exp.put(1, "A");
        exp.put(2, "B");
        exp.put(3, "C");
        assertEquals(exp, map.subMap(1, true, 3, true));

        HashMap<Integer, String> exp1 = new HashMap<>();
        exp1.put(1, "A");
        exp1.put(2, "B");
        assertEquals(exp1, map.subMap(1, true, 3, false));

        HashMap<Integer, String> exp2 = new HashMap<>();
        exp2.put(2, "B");
        assertEquals(exp2, map.subMap(1, false, 3, false));

        HashMap<Integer, String> exp3 = new HashMap<>();
        exp3.put(2, "B");
        exp3.put(3, "C");
        assertEquals(exp3, map.subMap(1, false, 3, true));

    }
}
