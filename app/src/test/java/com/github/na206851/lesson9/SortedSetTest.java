package com.github.na206851.lesson9;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class SortedSetTest {
    @Test
    public void testAdd() {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(5);
        set.add(8);

        assertEquals(3, set.size());
        assertTrue(set.contains(10));
        assertTrue(set.contains(5));
        assertTrue(set.contains(8));
        assertFalse(set.contains(0));
    }

    @Test
    public void setContainsNaturalNumber() {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(8);
        set.add(5);

        Integer[] expected = {10, 8, 5};
        assertArrayEquals(expected, set.toArray());
    }

    @Test
    public void getFirstElement() {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(8);
        set.add(12);

        assertEquals(8, set.first());
    }


    @Test
    public void getLastElement() {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(8);
        set.add(12);

        assertEquals(12, set.last());
    }

    @Test
    public void remove() {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(12);
        set.add(8);

        Integer[] expectedFirst = {8, 10, 12};
        assertArrayEquals(expectedFirst, set.toArray());

        set.remove(12);
        Integer[] expectedSecond = {8, 10};
        assertArrayEquals(expectedSecond, set.toArray());

        set.remove(8);
        Integer[] expectedThree = {10};
        assertArrayEquals(expectedThree, set.toArray());
    }

    @Test
    public void testSubSetTail() {
        SortedSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(i);
        }
        Integer[] expected = {0, 1, 2, 3, 4};
        assertArrayEquals(expected, set.toArray());

        set = set.tailSet(3);
        Integer[] expectedTailSet3 = {3, 4};
        assertArrayEquals(expectedTailSet3, set.toArray());

    }

    @Test
    public void testSubSetHead() {
        SortedSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(i);
        }
        List<Integer> expected = List.of(0, 1, 2);
        assertArrayEquals(expected.toArray(), set.headSet(3).toArray());

        assertEquals(5, set.size());
        assertEquals(3, set.headSet(3).size());
    }



}