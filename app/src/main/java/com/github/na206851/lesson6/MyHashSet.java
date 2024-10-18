package com.github.na206851.lesson6;

import com.github.na206851.lesson6.hashMap.MyMap;

import java.util.*;

public class MyHashSet<K> extends AbstractSet implements Set {
    MyMap<K, Object> map = new MyMap<>();
    int size = 0;
    Object defaultValue = new Object(); // то что лежит по умолчанию в качестве значения

    public MyHashSet() {
        //дописать свой set
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
        return map.containsKey((K) o);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[map.size()];
        for (int i = 0; i < map.size(); i++) {
            result[i++] = map.toString();
        }
        return result;
    }

    private void getKey() {
        List<K> list = new ArrayList<>();
    }


    @Override
    public boolean add(Object o) {
        if (map.get((K) o) == null) {
            map.put((K) o, defaultValue);
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
//        return map.remove(o) == defaultValue;
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Object[] arr = new MyMap[]{map};
        return str.toString();
    }

    public static void main(String[] args) {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(0);
        System.out.println(Arrays.toString(set.toArray()));
    }


}
