package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
        return (BinarySearchTree.NodeImpl<E>) n;
    }

    // update methods supported by this class

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (root == null) {
            root = new NodeImpl<>(e);
        } else {
            throw new IllegalStateException("root != null");
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

    public void printTree(Node<E> node, int space) {
        if (node == null) {
            return;
        }
        space += 10;
        printTree(validate(node).right, space);
        System.out.println();
        for (int i = 10; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(validate(node).value + "\n");
        printTree(validate(node).left, space);
    }


    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> newNode = new NodeImpl<>(e);
        if (e == null) {
            throw new IllegalArgumentException("error e = null");
        }
        if (root == null) {
            return addRoot(e);
        }

        if (n == null) {
            size++;
            return newNode;
        } else if ((Integer) e > (Integer) validate(n).value) {
            if (validate(n).right == null) {
                return validate(n).right = new NodeImpl<>(e);
            } else {
                n = validate(n).right;
                add(validate(n), e);
            }
        } else if ((Integer) e < (Integer) validate(n).value) {
            if (validate(n).left == null) {
                return validate(n).left = new NodeImpl<>(e);
            } else {
                add(validate(n).left, e);
            }
        }
        return newNode;
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        size++;
        return validate(root).left = new NodeImpl<>(e);
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        size++;
        return validate(root).right = new NodeImpl<>(e);
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
        return validate(n).value = e;
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
        BinarySearchTree.NodeImpl<E> removeNodeParent = (BinarySearchTree.NodeImpl<E>) parent(n);

        if (left(n) == null && right(n) == null) {
            if (removeNodeParent.left == n) {
                removeNodeParent.left = null;
            } else if (removeNodeParent.right == n) {
                removeNodeParent.right = null;
            }
            size--;
        } else if (left(n) == null || right(n) == null) {
            Node<E> child;
            if (left(n) == null) {
                child = validate(right(n));
            } else {
                child = validate(left(n));
            }
            if (removeNodeParent.left == n) {
                removeNodeParent.left = child;
            } else {
                removeNodeParent.right = child;
            }
            size--;
        } else if (left(n) != null && right(n) != null) {
            Node<E> nodeWithIn = getMinValueInRightSubtree(right(n));
            set(n, validate(nodeWithIn).value);
            remove(nodeWithIn);
        }
        return validate(n).value;
    }

    public Node<E> getMinValueInRightSubtree(Node<E> n) {
        while (validate(n).left != null) {
            n = validate(n).left;
        }
        return n;
    }

    // {@link Tree} and {@link BinaryTree} implementations

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        return validate(p).left;
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        return validate(p).right;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        return searchParent(root, n);
    }

    private Node<E> searchParent(Node<E> parent, Node<E> child) {
        if (child == null) {
            return null;
        } else {
            if (validate(root).left == child || validate(root).right == child) {
                return root;
            } else {
                Node<E> leftResult = searchParent(validate(parent).left, child);
                Node<E> rightResult = searchParent(validate(parent).left, child);
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
        return new iteratorTree((NodeImpl<Integer>) validate(root));
    }

    @Override
    public Iterable<Node<E>> nodes() {
        return null;
    }

    public static class iteratorTree implements Iterator {
        Stack<NodeImpl<Integer>> stackIterator = new Stack<>();
        BinarySearchTree.NodeImpl<Integer> currentNode;

        private iteratorTree(BinarySearchTree.NodeImpl<Integer> root) {
            currentNode = root;
            pushLeft(currentNode);
        }

        private void pushLeft(BinarySearchTree.NodeImpl<Integer> node) {
            while (node != null) {
                stackIterator.push(node);
                node = (BinarySearchTree.NodeImpl<Integer>) node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stackIterator.isEmpty();
        }

        @Override
        public Object next() {
            NodeImpl<Integer> node = stackIterator.pop();
            pushLeft((NodeImpl<Integer>) node.right);
            return node.value;
        }
    }

    protected static class NodeImpl<E> implements Node<E> {
        private E value;
        public Node<E> left;
        public Node<E> right;

        public NodeImpl(E value) {
            this.value = value;
            left = right = null;
        }

        public NodeImpl() {
        }

        @Override
        public E getElement() {
            return value;
        }
    }
}

