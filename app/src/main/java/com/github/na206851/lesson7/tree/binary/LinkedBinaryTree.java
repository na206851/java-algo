package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.*;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {
    public Node<E> root;
    private int size = 0;

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
            throw new IllegalStateException("корень уже установлен");
        }
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        if (e == null) {
            throw new IllegalArgumentException("Ошибка: e = null");
        }

        NodeImpl<E> newNode = new NodeImpl<>(e);

        if (root == null) {
            root = newNode;
            size++;
            return root;
        }

        if (n == null) {
            throw new IllegalArgumentException("Указан null в качестве родителя");
        }

        if (e.compareTo(validate(n).value) > 0) {
            if (validate(n).right == null) {
                validate(n).right = newNode;
                newNode.parent = n; // Устанавливаем родителя
                size++;
                return newNode;
            } else {
                return add(validate(n).right, e);
            }
        } else if (e.compareTo(validate(n).value) < 0) {
            if (validate(n).left == null) {
                validate(n).left = newNode;
                newNode.parent = n; // Устанавливаем родителя
                size++;
                return newNode;
            } else {
                return add(validate(n).left, e);
            }
        }

        return n;
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
        if (n == null) {
            throw new IllegalArgumentException("Удаляемый узел не может быть null");
        }

        NodeImpl<E> node = validate(n);
        NodeImpl<E> parent = (NodeImpl<E>) parent(n);

        // Удаление листа
        if (node.left == null && node.right == null) {
            if (parent == null) {
                root = null; // Удаление корня
            } else if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Узел с одним потомком
        else if (node.left == null || node.right == null) {
            NodeImpl<E> child = (NodeImpl<E>) (node.left != null ? node.left : node.right);
            if (parent == null) {
                root = child; // Удаление корня
            } else if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            child.parent = parent;
        }
        // Узел с двумя потомками
        else {
            Node<E> successor = getMinValueInRightSubtree(node.right);
            node.value = successor.getElement();
            remove(successor);
        }

        size--;
        return node.value;
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
        if (root == null) {
            System.out.println("Дерево пустое");
            return;
        }
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
        public E value;
        public Node<E> parent;
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

        public String toString() {
            return this.value.toString();
        }

        public int hashCode() {
            return (value != null) ? value.hashCode() : 0;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            NodeImpl<E> other = (NodeImpl<E>) object;
            return Objects.equals(this.value, other.value);
        }
    }
}