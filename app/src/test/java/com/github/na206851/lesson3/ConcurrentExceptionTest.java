package com.github.na206851.lesson3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
public class ConcurrentExceptionTest {
    @Test
    void ConcurrentModInAddMethod_FirstExample() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);
            ListIterator<Integer> listIterator = list.listIterator();
            list.add(23);
            assertThrows(ConcurrentModificationException.class, () -> {
                while (listIterator.hasNext()) {
                    listIterator.next();
                }
            });
        };
        listConsumer.accept(new ArrayList<Integer>());
        listConsumer.accept(new DynamicArray<Integer>());
    }

    @Test
    void ConcurrentModInAddMethod_SecondExample() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);
            ListIterator<Integer> listIterator = list.listIterator();

            Assertions.assertThrows(ConcurrentModificationException.class, () -> {
                while (listIterator.hasNext()) {
                    listIterator.next();
                    list.add(-1);
                }
            });
        };
        listConsumer.accept(new ArrayList<Integer>());
        listConsumer.accept(new DynamicArray<Integer>());
    }

    @Test
    void ConcurrentModInRemoveMethod_FirstExample() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> listIterator = list.listIterator();

            list.remove(1);

            Assertions.assertThrows(ConcurrentModificationException.class, () -> {
                while (listIterator.hasNext()) {
                    listIterator.next();
                }
            });
        };
        listConsumer.accept(new ArrayList<Integer>());
        listConsumer.accept(new DynamicArray<Integer>());
    }

    @Test
    void ConcurrentModInRemoveMethod_SecondExample() {
        Consumer<List<Integer>> listConsumer = (List<Integer> list) -> {
            list.add(1);
            list.add(2);
            list.add(3);

            ListIterator<Integer> listIterator = list.listIterator();

            Assertions.assertThrows(ConcurrentModificationException.class, () -> {
                while (listIterator.hasNext()) {
                    listIterator.next();
                    list.remove(0);
                }
            });
        };
        listConsumer.accept(new ArrayList<Integer>());
        listConsumer.accept(new DynamicArray<Integer>());
    }
}
