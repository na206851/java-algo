package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.*;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    public Node<E> root;
    private int size = 0;
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

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (root == null) {
            root = new NodeImpl<>(e);
            size++;
            return root;
        } else {
            throw new IllegalStateException("корень уже есть");
        }
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> newNode = new LinkedBinaryTree.NodeImpl<>(e);
        if (e == null) {
            throw new IllegalArgumentException("error e = null");
        }
        if (root == null) {
            return addRoot(e);
        }

        if (n == null) {
            return null;
        } else if ((Integer) e > (Integer) validate(n).value) {
            if (validate(n).right == null) {
                return validate(n).right = newNode;
            } else {
                n = validate(n).right;
                return add(validate(n), e);
            }
        } else if ((Integer) e < (Integer) validate(n).value) {
            if (validate(n).left == null) {
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
        NodeImpl<E> removeNodeParent = (NodeImpl<E>) parent(n);
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
        if (child == null || parent == null) {
            return null;
        } else {
            if (validate(parent).left == (child) || validate(parent).right == (child)) {
                return parent;
            } else {
                if ((validate(parent).left == null) || (validate(parent).right == null)) {
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
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new iteratorTree(validate(root));
    }

    @Override
    public Iterable<Node<E>> nodes() {
        List<Node<E>> list = new LinkedList<>();
        inOrderNodes(validate(root), list);
        return list;
    }

    private void inOrderNodes(NodeImpl<E> root, List<Node<E>> listNode) {
        if (root != null) {
            inOrderNodes((NodeImpl<E>) validate(root).left, listNode);
            listNode.add(root);
            inOrderNodes((NodeImpl<E>) root.right, listNode);
        }
    }

    public List<E> inOrder(NodeImpl<E> node, List<E> list) {
        if (node != null) {
            inOrder((NodeImpl<E>) node.left, list);
            list.add(node.getElement());
            inOrder((NodeImpl<E>) node.right, list);
        }
        return list;
    }

    /**
     * метод для печати дерева в стиле ascii
     */
    public void printAscii(NodeImpl<E> node, int space) {
        if (node == null) return;
        space += 10;
        printAscii((NodeImpl<E>) node.right, space);
        System.out.print("\n");
        for (int i = 10; i < space; i++) System.out.print(" ");
        System.out.print(node.value + "\n");
        printAscii((NodeImpl<E>) node.left, space);
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

    public static class NodeImpl<E> implements Node<E> {
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