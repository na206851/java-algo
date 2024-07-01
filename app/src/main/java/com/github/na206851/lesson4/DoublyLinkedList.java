package com.github.na206851.lesson4;

import java.util.*;

public class DoublyLinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>, Deque<E> {
    Node<E> head;
    Node<E> tail;
    private int size = 0;


    private class Node<E> {
        Node<E> next;
        E item;
        Node<E> prev;
        int modcount;

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
        Node currentNode = head;
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
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        Node node = new Node(null, e, null);
        if (tail == null) {
            this.head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
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
        Node removeNode = head;
        head = head.next;
        Object removeElement = removeNode.item;
        size -= 1;
        return (E) removeElement;
    }

    @Override
    public E removeLast() {
        Node removeNode = tail;
        Object removeElement = removeNode.item;
        if (size == 1) {
            clear();
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return (E) removeElement;
    }

    @Override
    public E pollFirst() {
        return size == 0 ? null : removeFirst();
    }

    @Override
    public E pollLast() {
        return size == 0 ? null : removeLast();
    }

    @Override
    public E getFirst() {
        return (E) head.item;
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
        Node currentNode = head;
        int count = 0;
        while (count < size) {
            if (currentNode.item.equals(o)) {
                remove(o);
                return true;
            }
            count++;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        Node currentNode = tail;
        int count = 0;
        while (count < size) {
            if (currentNode.item.equals(o)) {
                remove(size - count - 1);
                return true;
            }
            count++;
            currentNode = currentNode.prev;
        }
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
        return removeFirst();
    }

    @Override
    public E poll() {
        return size == 0 ? null : removeFirst();
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
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return removeFirst();
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
        Node node = head;
        Node nodeTail = tail;
        int count = 0;
        while (count < size) {
            if (node.item.equals(o)) {
                return true;
            } else if (nodeTail.item.equals(o)) {
                return true;
            } else {
                count++;
                node = node.next;
                nodeTail = nodeTail.prev;
            }
        }
        return false;
    }

    public Node<E> searchNode(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = headd;
        int count = 0;
        while (count != index) {
            node = node.next;
            count++;
        }
        return node;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class MyListIterator implements java.util.ListIterator<E> {
        private Node<E> currentNode;
        private Node<E> nextNode;
        private int nextIndex;
        int expectedModCount;

        public MyListIterator(int index) {

            nextNode = (nextIndex == size) ? null : searchNode(index);// посмотреть как настроить корректное переключение на следующий узел
            nextIndex = index;
        }

        void checkForModification() {
            if (expectedModCount != DoublyLinkedList.this.modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            currentNode = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return currentNode.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        @Override
        public E previous() {
            if (nextIndex < 0) {
                throw new NoSuchElementException();
            }
            nextNode = nextNode.prev;
            currentNode = nextNode;
            nextIndex--;
            return currentNode.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            int prevIndex = nextIndex - 1;
            return prevIndex;
        }

        @Override
        public void remove() {
            checkForModification();

            DoublyLinkedList.this.remove(nextIndex ); //todo посмотреть как правильно переиспользовать методы , возможно добавить
            // новые переменные для отслеживания текущего индекса узла
        }

        @Override
        public void set(E e) {
            checkForModification();
            DoublyLinkedList.this.set(nextIndex, e);
        }

        @Override
        public void add(E e) {
            checkForModification();

            DoublyLinkedList.this.add(nextIndex, e);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node currentNode = headd;
        int count = 0;
        while (count < size) {
            result[count] = currentNode.item;
            count++;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().componentType(), size);
        }
        for (int i = 0; i < size; i++) {
            a[i] = (T) DoublyLinkedList.this.get(i);
        }
        return a;
    }

    @Override
    public boolean add(E e) {
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
        Node removeNode = headd;
        int count = 0;
        while (count < size) {
            if (removeNode.item.equals(o)) {
                remove(count);
                return true;
            }
            count++;
            removeNode = removeNode.next;
        }
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
        int i = 0;
        int count = 0;
        Object[] in = c.toArray();
        if (in.length > size) {
            return false;
        }

        while (i != in.length - 1) {
            DoublyLinkedList.this.contains(in[i]);
            i++;
            count++;
        }
        return count == in.length - 1;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int count = 0;
        Object[] in = c.toArray();
        while (count < in.length) {
            DoublyLinkedList.this.add((E) in[count]);
            count++;
        }
        return count == in.length - 1;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int count = 0;
        Object[] in = c.toArray();
        if (index > size || index < 0) {
            return false;
        }
        while (count < in.length) {
            DoublyLinkedList.this.add(index, (E) in[count]);
            index++;
            count++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (size == 0) return false;

        Object[] in = c.toArray();
        int i = 0;
        int count = 0;
        while (i < in.length) {
            if (DoublyLinkedList.this.contains(in[i])) {
                DoublyLinkedList.this.remove(in[i]);
                count++;
            } else {
                i++;
            }
        }
        return count != 0;
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
        Node node = head;
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
            Node currentNode = head;
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
        Object o = null;
        Node currentNode = head;
        int count = 0;
        while (count < size) {
            if (index == 0) {
                o = removeFirst();
                break;
            }
            if (index == size - 1) {
                o = removeLast();
                break;
            }
            if (count == index) {
                o = currentNode.item;
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }
                size--;
                break;
            }
            currentNode = currentNode.next;
            count++;
        }
        return (E) o;
    }

    @Override
    public int indexOf(Object o) {
        int count = 0;
        Node node = head;
        Node nodeTail = tail;
        while (count < size) {
            if (node.item.equals(o)) {
                return count;
            } else if (nodeTail.item.equals(o)) {
                return size - count - 1;
            }
            node = node.next;
            nodeTail = nodeTail.prev;
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (DoublyLinkedList.this.contains(o)) {
            return DoublyLinkedList.this.indexOf(o);
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
