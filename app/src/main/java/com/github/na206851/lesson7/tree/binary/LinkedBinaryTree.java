package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
        return (NodeImpl<E>) n;
    }

    // update methods supported by this class

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
        if (n == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            addRoot(e);
        }

        if (validate(n).right != null && validate(n).left == null) {
            if (validate(n).left == null) {
                addLeft(validate(n), e);
            } else {
                add(validate(n).left, e);
            }
        } else {
            if (size % 2 == 1) {
                addRight(validate(n), e);
            } else {
                add(validate(n).right, e);
            }
        }
        return n;
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> left = new NodeImpl<>(e);
        validate(n).left = left;
        size++;
        return left;
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        Node<E> right = new NodeImpl<>(e);
        validate(n).right = right;
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
        validate(n).value = e;
        return validate(n).value;
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
        } else if (left(n) != null && right(n) != null) {
            //здесь должна быть реализация удаления узла с двумя потомками
        }
        size--;
        return (E) null;
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

    private Node<E> searchParent(Node<E> root, Node<E> child) {
        if (validate(root).left == null && validate(root).right == null) {
            return null;
        }
        if (root == child) {
            throw new IllegalArgumentException("был передан корень");
        } else {
            if (validate(root).left == child || validate(root).right == child) {
                return root;
            } else {
                Node<E> leftResult = searchParent(validate(root).left, child);
                Node<E> rightResult = searchParent(validate(root).right, child);

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
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();

        NodeImpl<Integer> node1 = new NodeImpl<>(1);
        NodeImpl<Integer> node2 = new NodeImpl<>(2);
        NodeImpl<Integer> node3 = new NodeImpl<>(3);
        NodeImpl<Integer> node4 = new NodeImpl<>(4);
        NodeImpl<Integer> node5 = new NodeImpl<>(5);
        NodeImpl<Integer> node6 = new NodeImpl<>(6);
        NodeImpl<Integer> node7 = new NodeImpl<>(7);
        NodeImpl<Integer> node8 = new NodeImpl<>(8);
        NodeImpl<Integer> node9 = new NodeImpl<>(9);
        NodeImpl<Integer> node10 = new NodeImpl<>(10);
        NodeImpl<Integer> node11 = new NodeImpl<>(11);
        NodeImpl<Integer> node12 = new NodeImpl<>(12);
        NodeImpl<Integer> node13 = new NodeImpl<>(13);
        NodeImpl<Integer> node14 = new NodeImpl<>(14);
        NodeImpl<Integer> node15 = new NodeImpl<>(15);
        NodeImpl<Integer> node16 = new NodeImpl<>(16);
        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;
        node6.right = node13;
        node7.left = node14;
        node7.right = node15;
        node8.right = node16;

        tree.printAscii((NodeImpl) tree.root, 0);
//        node7.left = node8;
//        tree.printAscii(node1, 0);

//        System.out.println(tree.findMaxNode(node1).getElement());
//        System.out.println(tree.findMaxNode(tree.root).getElement());
    }

    public Node<E> findMaxNode(Node<E> node) {
        if (node == null) {
            return parent(node);
        }
        Node<E> maxNode = node;
        Node<E> maxLeft = findMaxNode(left(node));
        Node<E> maxRight = findMaxNode(right(node));

        if (maxLeft != null && (Integer) maxLeft.getElement() > (Integer) (maxNode.getElement())) {
            maxNode = maxLeft;
        }

        if (maxRight != null && (Integer) maxRight.getElement() > (Integer) (maxNode.getElement())) {
            maxNode = maxRight;
        }
        return maxNode;
    }

    /**
     * метод для печати дерева в стиле ascii
     */
    public void printAscii(NodeImpl node, int space) {
        if (node == null) return;
        space += 10;
        printAscii((NodeImpl) node.right, space);
        System.out.print("\n");
        for (int i = 10; i < space; i++) System.out.print(" ");
        System.out.print(node.value + "\n");
        printAscii((NodeImpl) node.left, space);
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