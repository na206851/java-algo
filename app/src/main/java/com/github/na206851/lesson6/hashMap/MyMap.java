package com.github.na206851.lesson6;

public class MyMap<K, V> implements Map<K, V> {
    private Node[] map;
    private int defaultSize = 8;

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
        int threshold = (int) (map.length * 0.75);
        if (index(key) > threshold) {
            increaseSize();
            index = index(key);
        }

        Node newNode = new Node(key, value);
        if (map[index] == null) {
            map[index] = newNode;
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
                }
                tmp = tmp.next;
            }
        }
    }

    @Override
    public V remove(K key) {
        int index = index(key);
        Node tmp = map[index];
        if (tmp.next == null) {
            map[index] = null;
        } else {
            while (tmp != null) {
                if (tmp.key.equals(index(key))) {
                    tmp.val = null;
                    tmp.key = null;
                }
                tmp = tmp.next;
            }
        }
        return (V) tmp.val;
    }

    @Override
    public V get(K key) {
        int index = index(key);
        Node<K, V> tmp = map[index];

        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return tmp.val;
            }
            tmp = tmp.next;
        }
        return null;
    }

    private int index(K key) {
        return (key.hashCode()) % map.length;
    }

    private void increaseSize() {
        Node[] tmpMap = map;
        map = new Node[tmpMap.length * 2];
        for (Node node : tmpMap) {
            while (node != null) {
                put((K) node.key, (V) node.val);
                node = node.next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("{");
        for (Object o : map) {
            if (o != null) {
                tmp.append(o + ", ");
            }
        }
        tmp.delete(tmp.length() - 2, tmp.length());
        tmp.append("}");
        return tmp.toString();
    }

    public int size() {
        int size = 0;
        for (Node node : map) {
            while (node != null) {
                size++;
                node = node.next;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        MyMap<Integer, Integer> map = new MyMap<>();
        HashMap<Integer, Integer> jdk = new HashMap<>(1);
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
            jdk.put(i, i);
        }

        map.put(0, 1);
        map.put(3, -1);
        jdk.put(0, 1);
        jdk.put(3, -1);

        System.out.println(map + " mymap");

        System.out.println(map.size());
        System.out.println(jdk);
        System.out.println(jdk.size());
    }
}