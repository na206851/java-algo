package com.github.na206851.lesson6;

public class MyMap<K, V> implements Map<K, V> {
    public Node[] map;
    int defaultSize = 8;
    int size = defaultSize;

    public MyMap() {
        map = new Node[defaultSize];
    }

    @Override
    public void add(K key, V value) {
        int index = hash(key);

        if (index >= map.length) {
            increaseSize();
        }
        Node newNode = new Node(key, value);

        if (map[index] == null) {
            map[index] = newNode;
        } else if (map[index] != null) {
            Node<K, V> tmp = map[index];
            if (hash(tmp.key) != (hash((K) newNode.key)) && tmp.val != newNode.val) {
                while (tmp != null) {
                    tmp = tmp.next;
                }
                tmp.next = newNode;
            } else if (hash(tmp.key) != hash((K) newNode.key) && tmp.val == newNode.val) {
                map[index] = newNode;
            }
        }
    }

    @Override
    public Node remove(K key) {
        int index = hash(key);
        Node<K, V> tmp = map[index];
        if (tmp.next == null) {
            map[index] = null;
        } else {
            while (tmp != null) {
                if (tmp.key.equals(hash(key))) {
                    tmp.val = null;
                    tmp.key = null;
                }
                tmp = tmp.next;
            }
        }
        return tmp;
    }

    @Override
    public V search(K key) {
        Node tmp = map[hash(key)];
        if (tmp.next == null) {
            return (V) map[hash(key)].val;
        } else {
            Node nodeInOnesIndex = map[hash(key)];
            while (nodeInOnesIndex != null) {
                if (nodeInOnesIndex.key == key && nodeInOnesIndex.equals(key)) {
                    return (V) nodeInOnesIndex.val;
                }
                nodeInOnesIndex = nodeInOnesIndex.next;
            }
        }
        return null;
    }

    int size() {
        for (Node<K, V> o : map) {
            if (o != null) {
                size++;
            }
        }
        return size;
    }

    public int hash(K key) {
        int numberHash = (key.hashCode() % size());
        return numberHash;
    }

    public void increaseSize() {
        Node<K, V>[] tmpMap = map;
        map = new Node[tmpMap.length * 2];
        size = size * 2;
        for (Node<K, V> node : tmpMap) {
            while (node != null) {
                add(node.key, node.val);
                node = node.next;
            }
        }
    }

    public String toStringAllElementHashTable() {
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

class Node<K, V> {
    K key;
    V val;
    Node next;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public String toString() {
        return key + "=" + val;
    }
}
