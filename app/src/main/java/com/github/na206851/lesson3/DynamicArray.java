package com.github.na206851.lesson3;

import java.util.*;


public class DynamicArray<E> implements List<E> {

    private final static int defaultSize = 10;
    private Object[] ArrList = new Object[defaultSize];
    private int pointer = 0;
    protected transient int modCount = 0;

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
    public Object[] toArray() {
        return Arrays.copyOfRange(ArrList, 0, size());
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) Arrays.copyOf(ArrList, size(), a.getClass());
        }
        System.arraycopy(ArrList, 0, a, 0, size());
        if (a.length > size())
            a[size()] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        modCount++;
        if (pointer > 0 && size() == ArrList.length) {
            increaseInSize();
        }
        ArrList[pointer++] = e;
        return true;
    }

    private void increaseInSize() {
        int newCapacity = (int) (ArrList.length * 1.5);
        ArrList = Arrays.copyOf(ArrList, newCapacity);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
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
        modCount++;
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
        modCount++;
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
        modCount++;
        Object[] in = c.toArray();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < in.length; j++) {
                if (ArrList[i].equals(in[j])) {
                    remove(i);
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        modCount++;
        int count = 0;
        Object[] o = c.toArray();
        Object[] src = ArrList;
        for (int i = 0; i < pointer; i++) {
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
        modCount++;
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
        modCount++;
        if (index >= pointer) {
            throw new IndexOutOfBoundsException();
        }
        ArrList[index] = element;
        return (E) ArrList[index];
    }

    @Override
    public void add(int index, E element) {
        modCount++;
        if (index < 0 || index > pointer) {
            throw new IndexOutOfBoundsException();
        }

        if (pointer == size()) {
            increaseInSize();
        }

        for (int i = pointer; i > index; i--) {
            ArrList[i] = ArrList[i - 1];
        }
        ArrList[index] = element;
        pointer++;
    }

    @Override
    public E remove(int index) {
        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }
        modCount++;
        System.arraycopy(ArrList, index + 1, ArrList, index, size() - index - 1);
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
    public Iterator<E> iterator() {
        return new MyIterator(0);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyIterator(index);
    }


    private class MyIterator extends MyItr implements ListIterator<E> {
        MyIterator(int index) {
            super();
            currentIndex = index;

        }

        int expectedModCount = modCount;
        private int index = 0;
        private int currentIndex = -1;
        int lastIndex = -1;

        void checkForMod() {
            if (DynamicArray.this.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            int i = lastIndex;
            return ++i < size();
        }

        @Override
        public E next() {
            checkForMod();
            int i = index;
            if (i >= size())
                throw new NoSuchElementException();
            Object[] ArrList = DynamicArray.this.ArrList;
            if (i >= ArrList.length)
                throw new ConcurrentModificationException();
            currentIndex++;
            return (E) ArrList[++lastIndex];
        }


        @Override
        public boolean hasPrevious() {
            return currentIndex != 0;
        }

        @Override
        public E previous() {
            checkForMod();
            currentIndex -= 1;
            if (index < 0) {
                throw new NoSuchElementException();
            }
            if (index >= ArrList.length)
                throw new ConcurrentModificationException();

            return (E) ArrList[currentIndex];
        }

        @Override
        public int nextIndex() {
            int i = lastIndex;
            return ++i;
        }

        @Override
        public int previousIndex() {
            int i = currentIndex - 1;
            return i;
        }

        @Override
        public void remove() {
            if (lastIndex < 0) {
                throw new IllegalStateException();
            }
            checkForMod();
            try {
                DynamicArray.this.remove(lastIndex);
                currentIndex = lastIndex + 1;
                index--;
                lastIndex -= 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }


        @Override
        public void set(E e) {
            if (lastIndex < 0) {
                throw new IllegalStateException();
            }
            checkForMod();
            try {
                DynamicArray.this.set(lastIndex, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForMod();
            try {
                if (lastIndex == -1) {
                    lastIndex = index;
                    DynamicArray.this.add(currentIndex, e);
                    currentIndex += 1;
                    expectedModCount = modCount;
                } else {
                    lastIndex = index;
                    DynamicArray.this.add(lastIndex, e);
                    currentIndex += 1;
                    expectedModCount = modCount;
                }
                index = currentIndex;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }


    private class MyItr implements Iterator<E> {
        int index;
        int currentIndex = -1;
        int lastIndex = currentIndex;
        int expectedModCount = modCount;

        MyItr() {
        }

        @Override
        public boolean hasNext() {
            int i = lastIndex;
            return i != size();
        }

        @Override
        public E next() {
            checkForMod();
            int i = index;
            if (i >= size())
                throw new NoSuchElementException();
            if (i >= ArrList.length)
                throw new ConcurrentModificationException();
            currentIndex++;
            return (E) ArrList[++lastIndex];
        }

        void checkForMod() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> result = new ArrayList<E>();
        for (; fromIndex < toIndex; fromIndex++) {
            result.add((E) ArrList[fromIndex]);
        }
        return result;
    }

    @Override
    public String toString() {
        ArrList = Arrays.copyOf(ArrList, pointer);
        return Arrays.toString(ArrList);
    }
}
