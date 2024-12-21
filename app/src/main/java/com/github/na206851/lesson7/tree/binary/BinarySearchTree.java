package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.Iterator;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class BinarySearchTree<E> extends AbstractBinaryTree<E> {
    Node<E> root;
    int size = 0;
    // nonpublic utility

    /**
     * Validates the node is an instance of supported {@link NodeImpl} type and casts to it
     *
     * @param n node
     * @return casted {@link NodeImpl} node
     * @throws IllegalArgumentException
     */
    protected NodeImpl<E> validate(Node<E> n) throws IllegalArgumentException {
        return (NodeImpl<E>) n;
    }

    // update methods supported by this class

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (root == null) {
            root = new NodeImpl<>(e);
        } else {
            throw new IllegalStateException();
        }
        size++;
        return validate(root);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> test = new BinarySearchTree<>();
        BinarySearchTree.NodeImpl<Integer> node1 = new NodeImpl<>(8);

        test.root = node1;
        test.add(test.root(), 3);
        test.add(test.root(), 10);
        test.add(test.root(), 1);
        test.add(test.root(), 6);   //узел потерялся
        test.add(test.root(), 14);
        test.add(test.root(), 4);   //узел потерялся
        test.add(test.root(), 7);   //узел потерялся

        System.out.println(test.root.getElement() + " this root");
        test.printTree((NodeImpl) test.root, 0);
        System.out.println(test.inOrder(node1, new ArrayList<>()));
    }

    public List<Integer> inOrder(Node<E> root, List<Integer> list) {
        if (root == null) {
            return list;
        } else {
            inOrder(validate(root).left, list);
            list.add((int) validate(root).value);
            inOrder(validate(root).right, list);
        }
        return list;
    }

    private void printTree(BinarySearchTree.NodeImpl node, int space) {
        if (node == null) {
            return;
        }
        space += 10;
        printTree((NodeImpl) validate(node).right, space);
        System.out.println();
        for (int i = 10; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.value + "\n");
        printTree((NodeImpl) validate(node).left, space);
    }


    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        size++;
        return validate(n).left = new NodeImpl<>(e);
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        size++;
        return validate(n).right = new NodeImpl<>(e);
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


    protected static class NodeImpl<E> implements Node<E> {

        @Override
        public E getElement() {
            return null;
        }
    }

}

