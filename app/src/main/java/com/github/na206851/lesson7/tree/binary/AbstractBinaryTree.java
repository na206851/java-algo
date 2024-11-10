package com.github.na206851.lesson7.tree.binary;


import com.github.na206851.lesson7.tree.AbstractTree;
import com.github.na206851.lesson7.tree.Node;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    public abstract Node<E> left(Node<E> p) throws IllegalArgumentException;

    public abstract Node<E> right(Node<E> p) throws IllegalArgumentException;

    public abstract Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException;

    public abstract Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException;

    @Override
    public Node<E> sibling(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Node<E>> children(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int childrenNumber(Node<E> n) throws IllegalArgumentException {
        return 0;
    }

    /**
     * @return an iterable collection of nodes of the tree in inorder
     */
    public Iterable<Node<E>> inOrder() {
        return null;
    }
}

