package com.github.na206851.lesson8.binary;

import com.github.na206851.lesson7.tree.Node;
import com.github.na206851.lesson7.tree.binary.LinkedBinaryTree;

import java.util.Comparator;

public abstract class BinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    /**
     * Method for comparing two values
     *
     * @param val1
     * @param val2
     * @return
     */
    protected int compare(E val1, E val2) {
        return -1;
    }

    /**
     * Returns the node in n's subtree by val
     *
     * @param n
     * @param val
     * @return
     */
    public Node<E> treeSearch(Node<E> n, E val) {
        return null;
    }
}