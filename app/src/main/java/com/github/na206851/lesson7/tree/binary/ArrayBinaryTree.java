package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.Iterator;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {


    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        return null;
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
        return null;
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        return null;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public E set(Node<E> n, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public E remove(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int size() {
        return 0;
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
        private E node;

        public NodeImpl(E node) {
            this.node = node;
        }

        @Override
        public E getElement() {
            return null;
        }
    }
}