package com.github.na206851.lesson6.hashMap;

public class MyMap<K, V> implements Map<K, V> {
    private Node[] map;
    private int defaultSize = 8;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size = 0;

    public MyMap() {
        map = new Node[defaultSize];
    }

    private static class Node<S, T> {
        S key;
        T val;
        Node next;

        public Node(S key, T val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return key + "=" + val;
        }
    }

    @Override
    public void put(K key, V value) {
        int index = index(key);
        int threshold = (int) (map.length * DEFAULT_LOAD_FACTOR);
        if (size() > threshold) {
            increaseSize();
            index = index(key);
        }

        Node newNode = new Node(key, value);
        if (map[index] == null) {
            map[index] = newNode;
            size++;
        } else {
            Node tmp = map[index];
            while (tmp != null) {
                if (tmp.key.equals(newNode.key)) {
                    tmp.val = value;
                    return;
                }
                if (tmp.next == null) {
                    tmp.next = newNode;
                    tmp = tmp.next;
                    size++;
                }
                tmp = tmp.next;
            }
        }
    }

    public V remove(K key) {        //new method
        int index = index(key);
        Node tmp = map[index];
        if (map[index].key.equals(key)) {
            map[index] = null;
            size--;
            return (V) tmp.val;
        } else if (tmp.next != null) {
            while (tmp != null) {
                Node removeElement = tmp;
                if (tmp.key.equals(key)) {
                    tmp.key = null;
                    tmp.val = null;
                    return (V) removeElement.val;
                }
                tmp = tmp.next;
            }
            size--;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = index(key);
        if (index > size || index < 0) {
            return null;
        }
        Node<K, V> tmp = map[index];
        if (tmp == null) {
            return null;
        }
        if (map[index].key.equals(key)) {
            return tmp.val;
        } else if (tmp.next != null) {
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    return (V) tmp.val;
                }
                tmp = tmp.next;
            }
        }
        return null;
    }

    private int index(K key) {
        return key.hashCode() % map.length;
    }

    private void increaseSize() {       //new method
        Node[] tmpMap = map;
        map = new Node[map.length * 2];
        System.arraycopy(tmpMap, 0, map, 0, size);
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        map = new Node[defaultSize];
        size = 0;
    }

    @Override
    public String toString() {  //переписать метод чтобы он печатал в глубину
        StringBuilder tmp = new StringBuilder();
        tmp.append("{");
        for (Node currentNode : map) {
            while (currentNode != null) {
                tmp.append(currentNode + ", ");
                currentNode = currentNode.next;
            }

        }
        if (size > 2) {
            tmp.delete(tmp.length() - 2, tmp.length());
        }
        tmp.append("}");
        return tmp.toString();
    }

    public int size() {
        return size;
    }
}