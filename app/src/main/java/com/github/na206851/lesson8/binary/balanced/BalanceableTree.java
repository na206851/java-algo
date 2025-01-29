package com.github.na206851.lesson8.binary.balanced;

import com.github.na206851.lesson7.tree.binary.LinkedBinaryTree;
import com.github.na206851.lesson8.Node;
import com.github.na206851.lesson8.binary.BinarySearchTree;

public class BalanceableTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /**
     * Relinks a parent with child node
     */
    private void relink(LinkedBinaryTree.NodeImpl<E> parent, LinkedBinaryTree.NodeImpl<E> child, boolean makeLeftChild) {
        // todo
    }

    /**
     * Rotates n with parent.
     */
    public void rotate(Node<E> n) {
        // todo
    }

    /**
     * Performs a left-right/right-left rotations.
     */
    public Node<E> rotateTwice(Node<E> n) {
        // todo
        return null;
    }

}