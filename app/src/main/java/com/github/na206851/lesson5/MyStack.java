package com.github.na206851.lesson5;

import com.github.na206851.lesson4.DoublyLinkedList;

import java.util.LinkedList;

class MyStack<E> {
    private DoublyLinkedList<E> list;

    void push(E element) {
        list.add(element);
    }

    E pop() {
        return (E) list.remove();
    }
}
