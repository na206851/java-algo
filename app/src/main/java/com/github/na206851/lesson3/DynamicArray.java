package com.github.na206851.lesson3;

import java.util.*;


public class DynamicArray<E> implements List<E> {

    private final static int defaultSize = 10;

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
        for (int i = 0; i < ArrList.length; i++) {
            if (ArrList[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] o = c.toArray();
        int newLeng = o.length;
        if (newLeng + size() > point) {
            increaseInSize();
        }
        System.arraycopy(o, 0, ArrList, point, newLeng);
        point += newLeng;
        return true;
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
        if (index < ArrList.length && index >= 0) {
            ArrList[index] = element;
        }
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
        Object[] tmp = new Object[ArrList.length];
        int i = 0;
        int j = index + 1;
        int count = 0;
        while (i < index) {
            tmp[count++] = ArrList[i];
            i++;
        }
        while (j < ArrList.length - 1) {
            tmp[count++] = ArrList[j];
            j++;
        }
        ArrList[size() - 1] = null;
        System.arraycopy(tmp, 0, ArrList, 0, point - 1);
        point = point - 1;
        return (E) ArrList[index];

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
