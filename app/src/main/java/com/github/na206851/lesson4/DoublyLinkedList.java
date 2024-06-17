package com.github.na206851.lesson4;

import java.util.*;

public class DoublyLinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>, Deque<E> {
    Node headd;//должно быть tail
    Node<E> tail;
    private int size = 0;


    private class Node<E> {
        Node<E> next;
        E item;
        Node<E> prev;

        Node(Node<E> next, E element, Node<E> prev) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        Node currentNode = headd;
        while (currentNode != null) {
            result.append(currentNode.item);
            currentNode = currentNode.next;
            if (currentNode != null) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public void addFirst(E e) {
        Node newNode = new Node(null, e, null);
        if (headd == null) {
            headd = newNode;
            tail = headd;
        } else {
            newNode.next = headd;
            headd.prev = newNode;

        }
        headd = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        Node node = new Node(null, e, null);
        if (tail == null) {
            this.headd = node;                                        //зацикливаем список
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;                               //предыдущий узел равен старому хвосту
            tail = node;                                    //новый хвост равен новой ноду
        }
        size++;                                             //увеличиваем размер списка
    }


    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return (E) headd.item;
    }

    @Override
    public E getLast() {
        return (E) tail.item;
    }

    @Override
    public E peekFirst() {
        return getFirst();
    }

    @Override
    public E peekLast() {
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        if (size != 0) {
            addLast(e);
            return true;
        }
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        if (size != 0) {
            return getFirst();
        }
        return null;
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node node = headd;
        int count = 0;
        while (count < size) {
            if (node.item.equals(o)) {
                return true;
            }
            count++;
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        Node node;
        if (size == 0) {
            addFirst(e);
            return true;
        } else if (size > 0) {
            addLast(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    public int searchItem(E item) {
        Object o = (Object) item;
        for (int i = 0; i < size(); i++) {
            if (DoublyLinkedList.this.get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        headd = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    private int numberNode(int index) {
        if (index < (size - 1)) {

            for (int i = 0; i < index; i++) {

            }
        }
        return 1;//посмотреть метод , нужно чтобы он возвращал порядковый номер ноды
    }

    @Override
    public E get(int index) {
        Node node = this.headd;
        int count = 0;
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        while (count <= index || node.next != null) {
            if (count == index) {
                return (E) node.item;
            }
            count++;
            node = node.next;
        }
        return (E) null;
    }

    @Override
    public E set(int index, E element) {
        Node node = this.headd;
        int count = 0;
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (count <= index) {
            if (count == index) {
                break;
            }
            count++;
            node = node.next;
        }
        Object oldValue = node.item;
        node.item = element;
        return (E) oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);

        } else if (index == size) {
            addLast(element);
        } else if (index < size) {
            Node inNode = new Node(null, element, null);
            Node currentNode = headd;
            int count = 0;
            while (count < size) {
                if (count == index) {
                    inNode.next = currentNode;
                    inNode.prev = currentNode.prev;
                    currentNode.prev.next = inNode;
                    currentNode.prev = inNode;
                    break;
                }
                count++;
                currentNode = currentNode.next;
            }
            size++;
        }
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int count = 0;
        Node node = headd;
        while (count < size) {
            if (node.item.equals(o)) {
                return count;
            }
            node = node.next;
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node currentNode = headd;
        int count = 0;

        if (tail.item.equals(o)) {
            return 0;
        }

        while (currentNode != null) {
            if (currentNode.item.equals(o)) {
                return size - count - 1;
            }
            count++;
            currentNode = currentNode.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

}
