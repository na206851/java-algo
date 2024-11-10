package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.Iterator;

import com.github.na206851.lesson7.tree.Node;
import com.github.na206851.lesson7.tree.Tree;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    private Node<E> root;
    // nonpublic utility

    /**
     * Validates the node is an instance of supported {@link NodeImpl} type and casts to it
     *
     * @param n node
     * @return casted {@link NodeImpl} node
     * @throws IllegalArgumentException
     */
    protected NodeImpl<E> validate(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    // update methods supported by this class

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        root = new LinkedBinaryTree.NodeImpl<>(e);
        return root;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
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

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @param e element
     * @return replace element
     * @throws IllegalArgumentException
     */
    @Override
    public E set(Node<E> n, E e) throws IllegalArgumentException {
        return null;
    }

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @return replace element
     * @throws IllegalArgumentException
     */
    @Override
    public E remove(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    // {@link Tree} and {@link BinaryTree} implementations

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
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

    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    protected static class NodeImpl<E> implements Node<E> {

        @Override
        public E getElement() {
            return null;
        }
    }

}
