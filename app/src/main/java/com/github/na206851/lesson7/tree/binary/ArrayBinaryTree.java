package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.Iterator;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    private Node<E> root;
    public static Node[] data = new Node[8];
    int size = 0;

    private static Node[] increaseSize(Node[] data) {
        data = Arrays.copyOf(data, data.length * 2);
        return data;
    }

    public int indexNode(NodeImpl searchNode) {
        int index = 0;
        for (Node tmp : data) {
            if (tmp.equals(searchNode)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private ArrayBinaryTree.NodeImpl<E> validate(Node<E> n) throws IllegalArgumentException {
        return (ArrayBinaryTree.NodeImpl<E>) n;
    }

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        int indexLeft = indexNode(validate(p)) + 1;
        return data[indexLeft] == null ? null : data[indexLeft];
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        int indexRight = indexNode(validate(p)) + 2;
        return data[indexRight] == null ? null : data[indexRight];

    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> root() {
        return root == null ? null : data[0];
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (root != null) {
            throw new IllegalStateException("корень уже установлен");
        } else {
            root = new NodeImpl<>(e);
            data[0] = root;
            size++;
        }
        return root;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> newNode = new NodeImpl<>(e);

        int currentIndex = indexNode(validate(n));
        int indexLeftChild = currentIndex + 1;
        int indexRightChild = currentIndex + 2;

        if (currentIndex + 2 >= data.length) {
            data = increaseSize(data);

        }
        if (root == null) {
            return addRoot(e);
        } else {
            if ((Integer) e > (Integer) validate(n).value) {
                if (data[indexRightChild] == null) {
                    data[indexRightChild] = newNode;
                    validate(n).left = data[indexRightChild];
                    size++;
                } else {
                    return add(data[indexRightChild], e);
                }
            } else if ((Integer) e < (Integer) validate(n).value) {
                if (data[indexLeftChild] == null) {
                    data[indexLeftChild] = newNode;
                    validate(n).right = data[indexLeftChild];
                    size++;
                } else {
                    return add(data[indexLeftChild], e);
                }
            }
        }
        return data[indexNode(validate(newNode))];

    }

    @Override
    public E set(Node<E> n, E e) throws IllegalArgumentException {
        E oldValue = n.getElement();
        validate(n).value = e;
        return oldValue;
    }

    @Override
    public E remove(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return null;
    }

    protected static class NodeImpl<E> implements Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;

        public NodeImpl(E value) {
            this.value = value;
            left = right = null;
        }

        @Override
        public E getElement() {
            return value;
        }
    }
}