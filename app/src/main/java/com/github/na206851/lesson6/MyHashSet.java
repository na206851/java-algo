package com.github.na206851.lesson6;

import com.github.na206851.lesson6.hashMap.MyMap;

import java.util.*;

public class MyHashSet<K> extends AbstractSet implements Set {
    MyMap<K, Object> set = new MyMap<>();
    Object defaultValue = new Object();

    public MyHashSet() {
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return set.containsKey((K) o);
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[set.size()];
        for (int i = 0; i < set.size(); i++) {
            result[i++] = set.toString();
        }
        return result;

    }

    @Override
    public boolean add(Object o) {
        if (set.get((K) o) == null) {
            set.put((K) o, defaultValue);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (set.containsKey((K) o)) {
            set.remove((K) o);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        int tmp = set.size();
        for (Object o : c) {
            set.put((K) o, defaultValue);
        }
        return tmp != size();
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean containsCollection = false;
        for (Object o : c) {
            if (set.containsKey((K) o)) {
                set.remove((K) o);
                containsCollection = true;
            }
        }
        return containsCollection;
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean containsAll(Collection c) {
        int count = 0;
        for (Object o : c) {
            if (set.containsKey((K) o)) {
                count++;
            }
        }
        return count == c.size();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(set);
        return str.toString();
    }
}