package com.github.na206851.lesson6;

public class HashTable {
    Node[] backet;
    int size = 8;

    public static void main(String[] args) {
        HashTable test = new HashTable();
        test.add(0, 0);
        test.remove(0);

        test.add(1, 1);
        test.add(2, 2);
        test.add(3, 3);
        test.add(4, 4);
        test.add(5, 5);
        test.add(6, 6);
        System.out.println(test.toStringAllElementHashTable());
    }

    public HashTable() {
        backet = new Node[size];
    }


    public Node get(int index) {
        return backet[index];
    }

    public void add(Integer key, Integer value) {
        size++;
        int index = hash(key);
        Node newNode = new Node(key, value);
        newNode.next = backet[index];
        backet[index] = newNode;
    }

    public void remove(int index) {
        backet[index] = null;
    }

    public int hash(Integer key) {
        return key.hashCode() % size;
    }

    @Override
    public int hashCode() {
        return backet.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        return o.hashCode() == this.hashCode();
        //слабое место потенциально может быть два обьекта но обьявленных с помощью new , метод вернет false , хотя это неверно
        //нужно учитывать коллизию при одинаковом хеш коде

    }

    private int numberBucket() {
        int currentHashCode = hashCode();
        return currentHashCode % size;
    }

    public String toStringAllElementHashTable() {
        int count = 0;
        StringBuilder tmp = new StringBuilder();
        for (Object o : backet) {
            tmp.append(o + ", ");
        }
        return tmp.toString();
    }


}

class Node {
    Integer key;
    Integer val;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public String toString() {
        return key + " " + val;
    }
}