package com.github.na206851.lesson6;

import java.util.HashMap;

public class MyMap<K, V> implements Map<K, V> {
    public Node[] map;
    int defaultSize = 8;
    int size = defaultSize;

    public MyMap() {
        map = new Node[defaultSize];
    }

    private class Node<S, T> {
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

    public static void main(String[] args) {
        MyMap<String, Integer> test = new MyMap<>();
        HashMap<String, Integer> jdk = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            test.put(String.valueOf(i), i);
            jdk.put(String.valueOf(i), i);
        }

        System.out.println(test);
        System.out.println(jdk);
    }

    @Override
    public void put(K key, V value) {
        int index = index(key);

        if (map[index] != null) {
            increaseSize();
            index = index(key);
        }

        Node newNode = new Node(key, value);
        if (map[index] == null) {
            map[index] = newNode;
        } else if (map[index] != null) {
            Node tmp = map[index];
            if (index((K) tmp.key) != (index((K) newNode.key)) && tmp.val != newNode.val) {
                while (tmp != null) {
                    tmp = tmp.next;
                }
                tmp.next = newNode;
            } else if (index((K) tmp.key) != index((K) newNode.key) && tmp.val == newNode.val) {
                map[index] = newNode;
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
        Node tmp = map[index(key)];
        if (tmp.next == null) {
            return (V) map[index(key)].val;
        } else {
            Node nodeInOnesIndex = map[index(key)];
            while (nodeInOnesIndex != null) {
                if (nodeInOnesIndex.key == key && nodeInOnesIndex.equals(key)) {
                    return (V) nodeInOnesIndex.val;
                }
                nodeInOnesIndex = nodeInOnesIndex.next;
            }
        }
        return null;
    }

    public int index(K key) {
        return Math.abs(key.hashCode()) % map.length;
    }

    private void increaseSize() {
        Node[] tmpMap = map;
        map = new Node[tmpMap.length * 2];
        size = size * 2;
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
        tmp.append("}");
        return tmp.toString();
    }
}
