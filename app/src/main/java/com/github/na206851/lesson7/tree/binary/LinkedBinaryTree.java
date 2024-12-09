package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import javax.swing.*;
import java.util.*;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    public Node<E> root;
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
        ((NodeImpl<E>) n).value = e;
        return ((NodeImpl<E>) n).value;
    }

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @return replace element
     * @throws IllegalArgumentException
     */
    public E getLeftChild(Node<E> n) {
        return (E) ((NodeImpl<E>) n).left;
    }

    public E getRightChild(Node<E> n) {
        return (E) ((NodeImpl<E>) n).right;
    }

    @Override
    public E remove(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> removeNodeParent = (NodeImpl<E>) parent(n);
        if (((NodeImpl) n).left == null && ((NodeImpl) n).right == null) {
            if (removeNodeParent.left == n) {
                removeNodeParent.left = null;
            } else if (removeNodeParent.right == n) {
                removeNodeParent.right = null;
            }
        } else if (((NodeImpl<E>) n).left == null || ((NodeImpl<E>) n).right == null) {
            if (((NodeImpl<E>) n).left == null) {
                removeNodeParent.left = (Node<E>) getRightChild(n);
            } else {
                removeNodeParent.right = (Node<E>) getLeftChild(n);
            }
        } else if (((NodeImpl<E>) n).left != null && ((NodeImpl<E>) n).right != null) {
            //здесь должна быть реализация удаления узла с двумя потомками
        }
        size--;
        return (E) null;
    }

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
        return new iteratorTree((NodeImpl) root);
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return null;
    }

    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    //нужен тест на обход дерева
    public List<E> inOrder(Node<E> node, List<E> list) {
        NodeImpl<E> nodeImpl = (NodeImpl<E>) node;
        if (node != null) {
            inOrder(nodeImpl.left, list);
            list.add(node.getElement());
            inOrder(nodeImpl.right, list);
        }
        return list;
    }

    public static void main(String[] args) {
        //создает отдельно узлы и отдельно линкуем их в дерево  ,
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        NodeImpl<Integer> node1 = new NodeImpl<>(1);
        NodeImpl<Integer> node2 = new NodeImpl<>(2);
        NodeImpl<Integer> node3 = new NodeImpl<>(3);
        NodeImpl<Integer> node4 = new NodeImpl<>(4);
        NodeImpl<Integer> node5 = new NodeImpl<>(5);
        NodeImpl<Integer> node6 = new NodeImpl<>(6);
        NodeImpl<Integer> node7 = new NodeImpl<>(7);
        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        System.out.println(tree.inOrder(tree.root, new ArrayList<>()));
        iteratorTree it = new iteratorTree(node1);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        System.out.println(it.hasNext());
    }

    public static class iteratorTree implements Iterator {
        Stack<NodeImpl> stackIterator = new Stack<>();
        NodeImpl currentNode;

        iteratorTree(NodeImpl root) {
            currentNode = root;
            pushLeft(currentNode);
        }

        private void pushLeft(NodeImpl node) {
            while (node != null) {
                stackIterator.push(node);
                node = (NodeImpl) node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stackIterator.isEmpty();
        }

        @Override
        public Object next() {
            NodeImpl node = stackIterator.pop();
            pushLeft((NodeImpl) node.right);
            return node.value;
        }
    }

    protected static class NodeImpl<E> implements Node<E> {

        @Override
        public E getElement() {
            return null;
        }
    }

}