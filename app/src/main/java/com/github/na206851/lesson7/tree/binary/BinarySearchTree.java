package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.*;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {
    Node<E> root;
    private int size = 0;

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

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (root == null) {
            size++;
            root = new NodeImpl<>(e);
        } else {
            throw new IllegalStateException("root != null");
        }
        return validate(root);
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
        } else if (n == null) {
            return null;
        } else if (e.compareTo(validate(n).value) > 0) {
            if (validate(n).right == null) {
                size++;
                return validate(n).right = newNode;
            } else {
                return add(validate(n).right, e);
            }
        } else if (e.compareTo(validate(n).value) < 0) {
            if (validate(n).left == null) {
                size++;
                return validate(n).left = newNode;
            } else {
                return add(validate(n).left, e);
            }
        }
        return newNode;
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return addRoot(e);
        } else {
            if (validate(n).left == null) {
                return validate(n).left = new NodeImpl<>(e);
            } else {
                return addLeft(validate(n).left, e);
            }
        }
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return addRoot(e);
        } else {
            if (validate(n).right == null) {
                return validate(n).right = new NodeImpl<>(e);
            } else {
                return addRight(validate(n).right, e);
            }
        }
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
        E oldValue = n.getElement();
        validate(n).value = e;
        return oldValue;
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
        Node<E> child;
        if (left(n) == null && right(n) == null) {
            if (removeNodeParent != null && removeNodeParent.left == n) {
                removeNodeParent.left = null;
            } else if (removeNodeParent != null && removeNodeParent.right == n) {
                removeNodeParent.right = null;
            }
            size--;
        } else if (left(n) == null || right(n) == null) {
            if (left(n) == null) {
                child = validate(right(n));
            } else {
                child = validate(left(n));
            }
            if (removeNodeParent.left == (n)) {
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
        return n.getElement();

    }

    private Node<E> getMinValueInRightSubtree(Node<E> n) {
        if (validate(n).left == null) {
            return n;
        }
        return getMinValueInRightSubtree(validate(n).left);
    }

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
        if (parent == null || child == null) {
            return null;
        }
        if (validate(parent).left == child || validate(parent).right == child) {
            return parent;
        }

        Node<E> leftResult = searchParent(validate(parent).left, child);
        Node<E> rightResult = searchParent(validate(parent).right, child);

        if (leftResult != null) {
            return leftResult;
        } else {
            return rightResult;
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
        List<Node<E>> listNode = new LinkedList<>();
        inOrderNodes(validate(root), listNode);
        return listNode;
    }

    private void inOrderNodes(NodeImpl<E> root, List<Node<E>> listNode) {
        if (root != null) {
            inOrderNodes((NodeImpl<E>) root.left, listNode);
            listNode.add(root);
            inOrderNodes((NodeImpl<E>) root.right, listNode);
        }
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

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            BinarySearchTree.NodeImpl<E> other = (BinarySearchTree.NodeImpl<E>) object;
            return Objects.equals(this.value, other.value);
        }
    }
}