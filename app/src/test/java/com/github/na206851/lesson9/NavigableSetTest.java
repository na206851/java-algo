package com.github.na206851.lesson9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class NavigableSetTest {
    @Test
    public void testDuplicateElement() {    //не поддерживает вставку одинаковых элементов
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(10);

        List<Integer> expected = List.of(10, 20, 30);
        Assertions.assertIterableEquals(expected, set);
    }

    @Test
    public void testLower() {   //возвращает элемент строго меньший переданному в качестве параметра, если такого нет,
        //то метод возвращает null
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        System.out.println(set.lower(10));
        assertEquals(10, set.lower(20));
        assertEquals(20, set.lower(30));
        assertEquals(30, set.lower(40));
        assertNull(set.lower(10));
        assertEquals(30, set.lower(233));
        assertNull(set.lower(-1));
    }

    @Test
    public void testFloor() {   //возвращает элемент больший или равный переданному в качестве параметра в метод
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(10, set.floor(19));
        assertEquals(10, set.floor(10));
        assertNull(set.floor(9));
        assertEquals(30, set.floor(100));
        assertEquals(20, set.floor(29));
    }

    @Test
    public void testCeiling() {     //возвращает элемент больший или равный переданному в качестве параметра
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(10, set.ceiling(9));
        assertEquals(10, set.ceiling(-10));
        assertNull(set.ceiling(31));
        assertEquals(30, set.ceiling(21));
        assertEquals(20, set.ceiling(11));
    }

    @Test
    public void testHigher() {      //возвращает элемент строго больший переданного параметра
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(10, set.higher(0));
        assertEquals(20, set.higher(10));
        assertNull(set.higher(30));
        assertEquals(30, set.higher(20));
    }

    @Test
    public void testFirst() {   //возращает первый элемент из множества
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(10, set.first());
        assertEquals(3, set.size());
        set.remove(10);
        assertEquals(20, set.first());
        assertEquals(2, set.size());
    }

    @Test
    public void testLast() {    //возвращает последний элемент
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(30, set.last());
        set.remove(30);
        assertEquals(20, set.last());
    }

    @Test
    public void testPollFirst() {   //извлекает первый элемент и удаляет его из множества
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(3, set.size());
        assertEquals(10, set.pollFirst());
        assertEquals(2, set.size());

        assertEquals(20, set.pollFirst());
        assertEquals(1, set.size());

        set.pollFirst();
        assertEquals(0, set.size());
        assertNull(set.pollFirst());

    }

    @Test
    public void testPollLast() {    //извлекает последний элемент в удаляет его из множества
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        assertEquals(3, set.size());
        assertEquals(30, set.pollLast());

        assertEquals(2, set.size());
    }

    @Test
    public void testHeadSet() {     //возвращает подмножество, в качестве параметров принимает элемент с которым заканчивается
        // новое подмножество и параметр который указывает на то нужно ли вернуть множество с последним элементом строго
        //больше или равным переданному параметру
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(40);
        set.add(50);

        List<Integer> expected = List.of(10, 20);
        assertIterableEquals(expected, set.headSet(30));

        List<Integer> expectedBooleanTrue = List.of(10, 20, 30);
        assertIterableEquals(expectedBooleanTrue, set.headSet(30, true));

        List<Integer> expectedEmptySet = List.of();
        assertIterableEquals(expectedEmptySet, set.headSet(10));

        List<Integer> expectedSetFull = List.of(10, 20, 30, 40, 50);
        assertIterableEquals(expectedSetFull, set.headSet(100));
    }

    @Test
    public void testTailSet() {
        //возвращает элементы подмножество больше переденного параметра , boolean параметр отвечает за
        //включение границ , по умолчанию (True) границы входят в диапазон
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(40);
        set.add(50);

        List<Integer> expected = List.of(30, 40, 50);
        assertIterableEquals(expected, set.tailSet(30));

        List<Integer> expectedBooleanFalse = List.of(40, 50);
        assertIterableEquals(expectedBooleanFalse, set.tailSet(30, false));

        List<Integer> expectedBooleanTrue = List.of(30, 40, 50);
        assertIterableEquals(expectedBooleanTrue, set.tailSet(24));

        List<Integer> expectedEmptyTailSet = List.of();
        assertIterableEquals(expectedEmptyTailSet, set.tailSet(51, true));
    }

    @Test
    public void testSubSet() {
        //возвращает подмножество с заданными граница с головы и хвоста , boolean параметры отвечает за включение переданных
        //границ в результирующее подмножество
        //по умолчанию нижняя граница включена в границу , верхняя нет
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(40);
        set.add(50);

        List<Integer> expectedBooleanFalse = List.of(20, 30, 40);
        assertIterableEquals(expectedBooleanFalse, set.subSet(10, false, 50, false));

        List<Integer> expectedBooleanTrue = List.of(20, 30);
        assertIterableEquals(expectedBooleanTrue, set.subSet(20, true, 30, true));

        List<Integer> expectedBooleanDefault = List.of(10, 20, 30);
        assertIterableEquals(expectedBooleanDefault, set.subSet(10, 40));
    }

    @Test
    public void testDescendingSet() {
        //метод возвращает множество в обратном порядке
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(40);
        set.add(50);

        Integer[] expected = {50, 40, 30, 20, 10};
        assertArrayEquals(expected, set.descendingSet().toArray());

    }
}
