package com.github.na206851.lesson7.tree;

import java.util.Iterator;

/**
 * An interface for a tree where nodes can have an arbitrary number of children
 *
 * @param <E> element of a tree
 */
public interface Tree<E> extends Iterable<E> {

    // accessor methods

    /**
     * @return the node of the root of the tree (or null if empty)
     */
    Node<E> root();

    /**
     * @param n node
     * @return the node of the parent of the tree (or null if empty)
     * @throws IllegalArgumentException
     */
    Node<E> parent(Node<E> n) throws IllegalArgumentException;

    /**
     * @param n node
     * @return an iterable collection containing the children of node <i>node</i> (if any)
     * @throws IllegalArgumentException
     */
    Iterable<Node<E>> children(Node<E> n) throws IllegalArgumentException;

    /**
     * @param n node
     * @return the number of children of node <i>n</i>
     * @throws IllegalArgumentException if <i>n</i> instance is not of supported type
     */
    int childrenNumber(Node<E> n) throws IllegalArgumentException;

    // update methods supported by this class

    /**
     * Places element <i>e</i> at the root of an empty tree and returns its new {@link Node}
     *
     * @param e element
     * @return created root
     * @throws IllegalStateException if tree is not empty
     */
    Node<E> addRoot(E e) throws IllegalStateException;

    /**
     * Creates a new child of {@link Node} <i>p</i> storing element <i>e</i>
     *
     * @param p parent node
     * @param e element
     * @return created node
     * @throws IllegalArgumentException
     */
    Node<E> add(Node<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @param e element
     * @return replace element
     * @throws IllegalArgumentException
     */
    E set(Node<E> n, E e) throws IllegalArgumentException;

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @return replace element
     * @throws IllegalArgumentException
     */
    E remove(Node<E> n) throws IllegalArgumentException;

    // query methods

    /**
     * @param n node
     * @return true if node <i>n</i> has at least one child
     * @throws IllegalArgumentException if <i>n</i> instance is not of supported type
     */
    boolean isInternal(Node<E> n) throws IllegalArgumentException;

    /**
     * @param n node
     * @return true if node <i>n</i> does not have any children
     * @throws IllegalArgumentException if <i>n</i> instance is not of supported type
     */
    boolean isExternal(Node<E> n) throws IllegalArgumentException;

    /**
     * @param n node
     * @return true if node <i>n</i> is the root of the tree
     * @throws IllegalArgumentException if <i>n</i> instance is not of supported type
     */
    boolean isRoot(Node<E> n) throws IllegalArgumentException;

    // general methods

    /**
     * @return the number of nodes (and hence elements) that are contained in the tree
     */
    int size();

    /**
     * @return true if the tree does not contain any nodes (and thus no elements)
     */
    boolean isEmpty();

    /**
     * @return an iterator for all elements in the tree (so that the tree itself is {@link Iterable})
     */
    @Override
    Iterator<E> iterator();

    /**
     * @return an iterable collection of all nodes of the tree
     */
    Iterable<Node<E>> nodes();

}