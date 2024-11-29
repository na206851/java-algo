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
    private int size;
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
        root = new NodeImpl<>(e);
        size++;
        return root;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        if (root == null) {
            addRoot(e);
        } else {
            rec(root, e);
        }
        return n;
    }

    public void rec(Node<E> currentNode, E e) {
        if (((NodeImpl) currentNode).left == null) {
//            addLeft(currentNode, e);
            ((NodeImpl<E>) currentNode).left = new NodeImpl<>(e);
//            rec(((NodeImpl) currentNode).left, e);
        } else if (((NodeImpl<E>) currentNode).right == null) {
//            addRight(root, e);
            ((NodeImpl<E>) currentNode).right = new NodeImpl<>(e);
//            rec(((NodeImpl) currentNode).right, e);
        } else {
            rec(((NodeImpl<E>) currentNode).left, e);
        }
    }


    public static void visualMyTree() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.root = new NodeImpl<>(1);
        ((NodeImpl<Integer>) tree.root).left = new NodeImpl<>(2);
        ((NodeImpl<Integer>) tree.root).right = new NodeImpl<>(3);
        ((NodeImpl<Integer>) ((NodeImpl<Integer>) tree.root).left).left = new NodeImpl<>(4);
        ((NodeImpl<Integer>) ((NodeImpl<Integer>) tree.root).left).right = new NodeImpl<>(5);
        ((NodeImpl<Integer>) ((NodeImpl<Integer>) tree.root).right).left = new NodeImpl<>(6);
        ((NodeImpl<Integer>) ((NodeImpl<Integer>) tree.root).right).right = new NodeImpl<>(7);
        ((NodeImpl<Integer>) ((NodeImpl<Integer>) ((NodeImpl<Integer>) tree.root).right).left).left = new NodeImpl<>(8);
        ((NodeImpl<Integer>) ((NodeImpl<Integer>) ((NodeImpl<Integer>) tree.root).right).left).right = new NodeImpl<>(9);
        inOrder((NodeImpl) tree.root);
    }

    public E getLeft(Node<E> current) {
        return (E) ((NodeImpl) current).left.getElement();
    }

    public E getRight(Node<E> current) {
        return (E) ((NodeImpl) current).right.getElement();
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> left = new NodeImpl<>(e);
        ((NodeImpl) n).left = left;
        size++;
        return left;
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> right = new NodeImpl<>(e);
        ((NodeImpl) n).right = right;
        size++;
        return right;
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
        ((NodeImpl) n).value = e;
        return ((NodeImpl<E>) n).value;
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
        return root;
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        return searchParent(root, n);
    }

    private Node<E> searchParent(Node<E> root, Node<E> child) {
        if (((NodeImpl<E>) root).left == null && ((NodeImpl<E>) root).right == null) {
            return null;
        }
        if (root == child) {
            throw new IllegalArgumentException("был передан корень");
        } else {
            if (((NodeImpl<E>) root).left == child || ((NodeImpl<E>) root).right == child) {
                return root;
            } else {
                Node<E> leftResult = searchParent(((NodeImpl<E>) root).left, child);
                Node<E> rightResult = searchParent(((NodeImpl<E>) root).right, child);

                if (leftResult != null) {
                    return leftResult;
                } else {
                    return rightResult;
                }
            }
        }
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

    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    public static void inOrder(NodeImpl node) {
        if (node == null) {
            return;
        }
        inOrder((NodeImpl) node.left);
        System.out.print(node.getElement() + " ");
        inOrder((NodeImpl) node.right);
    }

    protected static class NodeImpl<E> implements Node<E> {

        @Override
        public E getElement() {
            return null;
        }
    }

}
