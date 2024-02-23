package com.github.na206851.lesson3;

import java.util.*;


public class DynamicArray<E> implements List<E> {

    private int defaultSize = 10;
    private Object[] ArrList = new Object[defaultSize];
    private int point = 0;


    @Override
    public int size() {
        return this.point;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < ArrList.length; i++) {
            if (ArrList[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(ArrList, 0, size());
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {           //здесь посмотреть чек лист , если нужно добавить исключение
        try {
            if (point > 0 && size() == ArrList.length) {
                increaseInSize();
            }
        } catch (RuntimeException E) {
            throw new IndexOutOfBoundsException();
        } finally {
            ArrList[point++] = e;
        }
        return true;
    }

    private void increaseInSize() {
        int newCapacity = (int) (ArrList.length * 1.5);
        ArrList = Arrays.copyOf(ArrList, newCapacity);
    }


    @Override
    public boolean remove(Object o) {

        return false;
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
        ArrList = new Object[defaultSize];
        point = 0;
    }

    @Override
    public E get(int index) {
        if (index >= point || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return (E) ArrList[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) { //сделать так чтобы все работало через исключения блоки try catch
        try {
            if (point == ArrList.length) {
                increaseInSize();
            }
        } catch (RuntimeException E) {
            throw new RuntimeException("runtime exception");
        } finally {
            ArrList[index] = element;
        }
    }

    @Override
    public E remove(int index) {
        for (int i = index; i < size() - 1; i++) {
            ArrList[i] = ArrList[i + 1];
        }
        ArrList[index - 1] = null;
        return (E) ArrList;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < ArrList.length; i++) {
            if (ArrList[i] == o) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 1;
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
