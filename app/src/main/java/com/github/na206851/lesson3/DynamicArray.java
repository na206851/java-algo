package com.github.na206851.lesson3;

import java.util.*;


public class DynamicArray<E> implements List<E> {

    private final static int defaultSize = 10;

    private Object[] ArrList = new Object[defaultSize];
    private int pointer = 0;


    @Override
    public int size() {
        return this.pointer;
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
    public boolean add(E e) {
        try {
            if (pointer > 0 && size() == ArrList.length) {
                increaseInSize();
            }
        } catch (RuntimeException E) {
            throw new IndexOutOfBoundsException();
        } finally {
            ArrList[pointer++] = e;
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
        if (c.size() > ArrList.length || c.isEmpty()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < pointer; i++) {
            for (Object o : c) {
                if (ArrList[i].equals(o)) {
                    count++;
                    break;
                }
            }
        }
        return count == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] o = c.toArray();
        int newLength = o.length;
        if (newLength + size() > pointer) {
            increaseInSize();
        }
        System.arraycopy(o, 0, ArrList, pointer, newLength);
        pointer += newLength;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (c.toArray().length == 0) {
            return false;
        }
        if (c.toArray().length + size() > pointer) {
            increaseInSize();
        }
        Object[] in = c.toArray();
        Object[] src = Arrays.copyOfRange(ArrList, 0, pointer);
        Object[] result = new Object[pointer + in.length + 1];
        int j = 0;
        int count = 0;
        while (count < in.length + pointer) {
            if (index > 0) {
                for (int i = 0; i < index; i++) {
                    result[count++] = src[i];
                    j++;
                }
                for (int i = 0; i < in.length; i++) {
                    result[count++] = in[i];
                }
                for (int i = index + in.length; i < src.length + in.length; i++) {
                    result[count++] = src[j++];
                }
            } else {
                for (int i = 0; i < in.length; i++) {
                    result[count++] = in[i];
                }
                for (int i = 0; i < src.length; i++) {
                    result[count++] = src[j++];
                }
            }
        }
        pointer = count;
        ArrList = result;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] in = c.toArray();
        for (int i = 0; i < ArrList.length; i++) {
            for (int j = 0; j < in.length; j++) {
                if (ArrList[i].equals(in[j])) {
                    remove(j);
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int count = 0;
        Object[] o = c.toArray();
        Object[] src = ArrList;
        for (int i = 0; i < src.length; i++) {
            for (Object object : o) {
                if (src[i].equals(object)) {
                    src[i] = object;
                    count++;
                    break;
                }
            }
        }
        pointer = count;
        return count == c.size();
    }

    @Override
    public void clear() {
        ArrList = new Object[defaultSize];
        pointer = 0;
    }

    @Override
    public E get(int index) {
        if (index >= pointer || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) ArrList[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= pointer || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index < ArrList.length) {
            ArrList[index] = element;
        }
        return (E) ArrList[index];
    }

    @Override
    public void add(int index, E element) {
        try {
            if (pointer == ArrList.length) {
                increaseInSize();
            }
        } catch (RuntimeException E) {
            throw new RuntimeException();
        } finally {
            ArrList[index] = element;
        }
    }

    @Override
    public E remove(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
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
        System.arraycopy(tmp, 0, ArrList, 0, pointer - 1);
        pointer = pointer - 1;
        return (E) ArrList[index];

    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < ArrList.length; i++) {
            if (ArrList[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = pointer - 1; i >= 0; i--) {
            if (ArrList[i].equals(o)) {
                return i;
            }
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
    public List<E> subList(int fromIndex, int toIndex) { //todo test
        Object[] sublist = Arrays.copyOfRange(ArrList, fromIndex, toIndex);
        List<E> result = new ArrayList();
        for (Object o : sublist) {
            result.add((E) o);
        }
        return result;
    }

    @Override
    public String toString() {
        ArrList = Arrays.copyOf(ArrList, pointer);
        return Arrays.toString(ArrList);
    }
}
