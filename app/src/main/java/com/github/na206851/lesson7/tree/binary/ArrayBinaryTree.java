package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;

import java.util.Iterator;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    private Node<E> root;
    public static Node[] data = new Node[8];
    int size = 0;


    private static int calculateHeight(int size) {
        return (int) Math.ceil(Math.log(size + 1) / Math.log(2));
    }

    public void printAsciiTree(Node[] data) {
        if (data == null || data.length == 0) {
            System.out.println("Дерево пустое.");
            return;
        }
        int height = calculateHeight(data.length); // Вычисляем высоту дерева
        List<String> result = new ArrayList<>();
        for (int level = 0; level < height; level++) {
            StringBuilder line = new StringBuilder();
            int startIdx = (1 << level) - 1; // Индекс начала уровня
            int endIdx = Math.min((1 << (level + 1)) - 1, data.length); // Индекс конца уровня
            int spacing = (1 << (height - level)) - 1; // Расстояние между элементами
            for (int i = startIdx; i < endIdx; i++) {
                if (i < size) {
                    line.append(" ".repeat(spacing)).append(validate(data[i]).value).append(" ".repeat(spacing));
                }
            }
            result.add(line.toString());
        }
        result.forEach(System.out::println);
    }

    private static Node[] increaseSize(Node[] data) {
        data = Arrays.copyOf(data, data.length * 2);
        return data;
    }

    public int indexNode(NodeImpl searchNode) {
        int index = 0;
        for (Node tmp : data) {
            if (tmp == searchNode) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }

    public ArrayBinaryTree.NodeImpl<E> validate(Node<E> n) throws IllegalArgumentException {
        return (ArrayBinaryTree.NodeImpl<E>) n;
    }

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        int indexLeft = 2 * indexNode(validate(p)) + 1;
        return data[indexLeft] == null ? null : data[indexLeft];
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        int indexRight = 2 * indexNode(validate(p)) + 2;
        return data[indexRight] == null ? null : data[indexRight];

    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        if (e == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return addRoot(e);
        } else {
            Node<E> newNode = new NodeImpl<>(e);
            int indexAddLeft = indexNode(validate(n)) * 2 + 1;
            if (validate(n).left == null) {
                if (indexAddLeft >= data.length) {
                    data = increaseSize(data);
                }
                validate(n).left = newNode;
                data[indexAddLeft] = validate(n).left;
                return newNode;
            } else {
                return addLeft(validate(n).left, e);
            }
        }
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        validate(root).right = new ArrayBinaryTree.NodeImpl<>(e);
        data[2] = validate(root).right;
        size++;
        return data[2];
    }

    @Override
    public Node<E> root() {
        return root == null ? null : data[0];
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        int i = indexNode(validate(n));
        return data[(i - 1) / 2] == null ? null : data[(i - 1) / 2];
    }

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (root != null) {
            throw new IllegalStateException("корень уже установлен");
        } else {
            root = new NodeImpl<>(e);
            data[0] = root;
            size++;
        }
        return root;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> newNode = new NodeImpl<>(e);
        int currentIndex = indexNode(validate(n));
        int indexLeftChild = 2 * currentIndex + 1;
        int indexRightChild = 2 * currentIndex + 2;

        if (indexRightChild >= data.length) {
            data = increaseSize(data);
        }
        if (root == null) {
            return addRoot(e);
        } else {
            if ((Integer) e > (Integer) validate(n).value) {
                if (data[indexRightChild] == null) {
                    data[indexRightChild] = newNode;
                    validate(n).right = newNode;
                    size++;
                    return newNode;
                } else {
                    return add(validate(n).right, e);
                }
            } else if ((Integer) e < (Integer) validate(n).value) {
                if (data[indexLeftChild] == null) {
                    validate(n).left = newNode;
                    data[indexLeftChild] = newNode;
                    size++;
                    return newNode;
                } else {
                    return add(validate(n).left, e);
                }
            }
        }
        return newNode;
    }

    @Override
    public E set(Node<E> n, E e) throws IllegalArgumentException {
        E oldValue = validate(n).getElement();
        validate(n).value = (E) e;
        return oldValue;
    }

    @Override
    public E remove(Node<E> n) throws IllegalArgumentException {
        if (n == null) {
            throw new IllegalArgumentException();
        }
        Node<E> parentRemoveNode = parent(n);
        int currentIndex = indexNode(validate(n));

        if (currentIndex * 2 + 2 >= data.length) {
            data = increaseSize(data);
        }
        if (left(n) == null && right(n) == null) {
            if (validate(parentRemoveNode).left == n) {
                validate(parentRemoveNode).left = null;
            } else if (validate(parentRemoveNode).right == n) {
                validate(parentRemoveNode).right = null;
            }
            data[currentIndex] = null;
            size--;
        } else if (left(n) == null || right(n) == null) {
            Node<E> child;
            if (left(n) == null) {
                child = validate(right(n));
            } else {
                child = validate(left(n));
            }
            if (validate(parentRemoveNode).left == n) {
                validate(parentRemoveNode).left = child;
            } else {
                validate(parentRemoveNode).right = child;
            }
            int childIndex = indexNode(validate(child));
            data[indexNode((NodeImpl<E>) n)] = child;
            data[childIndex] = null;
            size--;
        } else if (left(n) != null && right(n) != null) {
            Node<E> nodeWithIn = getMinValueInRightSubtree(right(n));
            set(n, validate(nodeWithIn).value);
            remove(nodeWithIn);
        }
        return validate(n).value;
    }

    public Node<E> getMinValueInRightSubtree(Node<E> node) {
        if (validate(node).left == null) {
            return node;
        }
        return getMinValueInRightSubtree(validate(node).left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayBinaryTree.iteratorTree((NodeImpl<Integer>) validate(root));
    }

    @Override
    public Iterable<Node<E>> nodes() {
        List<Node<E>> listNode = new LinkedList<>();
        iterableList(0, listNode);
        return listNode;
    }

    private void iterableList(int currentIndex, List<Node<E>> listNode) {
        while (currentIndex < data.length) {
            if (data[currentIndex] != null) {
                listNode.add(data[currentIndex]);
            }
            currentIndex++;
        }
    }

    public static class iteratorTree implements Iterator {
        Stack<ArrayBinaryTree.NodeImpl<Integer>> stackIterator = new Stack<>();
        NodeImpl<Integer> currentNode;

        iteratorTree(ArrayBinaryTree.NodeImpl<Integer> root) {
            currentNode = root;
            pushStack(currentNode);
        }

        public int size() {
            int count = 0;
            for (Node<Integer> tmp : this.stackIterator) {
                if (tmp != null) {
                    count++;
                }
            }
            return count;
        }

        private void pushStack(NodeImpl<Integer> node) {
            int currentIndex = 0;
            while (currentIndex != size()) {
                stackIterator.push(this.stackIterator.get(currentIndex++));
            }
        }

        @Override
        public boolean hasNext() {
            return !stackIterator.isEmpty();
        }

        @Override
        public Object next() {
            NodeImpl<Integer> node = stackIterator.pop();
            return node.getElement();
        }
    }

    protected static class NodeImpl<E> implements Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;

        public NodeImpl(E value) {
            this.value = value;
            left = right = null;
        }

        @Override
        public E getElement() {
            return value;
        }

        @Override
        public String toString() {
            return value.toString();
        }


        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(Object value) {
            if (this.value == value) {
                return true;
            }
            return this.hashCode() == value.hashCode();
        }

    }
}