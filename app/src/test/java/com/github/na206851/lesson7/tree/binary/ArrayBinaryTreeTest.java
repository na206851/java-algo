package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;
import com.github.na206851.lesson7.tree.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ArrayBinaryTreeTest {
    ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<>();

    @Test
    void indexNode() {
        Node<Integer> node8 = tree.add(tree.root, 8);
        Node<Integer> node10 = tree.add(tree.root, 10);
        Node<Integer> node5 = tree.add(tree.root, 5);
        Node<Integer> node12 = tree.add(tree.root, 12);
        Node<Integer> node3 = tree.add(tree.root, 3);
        Node<Integer> node7 = tree.add(tree.root, 7);
        Node<Integer> node9 = tree.add(tree.root, 9);
        Node<Integer> node11 = tree.add(tree.root, 11);
        assertEquals(1, tree.indexNode(tree.validate(node5)));
        assertEquals(2, tree.indexNode(tree.validate(node10)));
        assertEquals(3, tree.indexNode(tree.validate(node3)));
        assertEquals(4, tree.indexNode(tree.validate(node7)));
        assertEquals(0, tree.indexNode(tree.validate(node8)));
        //попробовать провести тесты через set
    }

    @Test
    void left() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node10 = tree.add(tree.root(), 10);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node3 = tree.add(tree.root(), 3);
            Node<Integer> node9 = tree.add(tree.root(), 9);

            assertEquals(node9.getElement(), tree.left(node10).getElement());
            assertEquals(tree.parent(node9), node10);
            assertEquals(node3, tree.left(node5));
            assertEquals(node5.getElement(), tree.left(node8).getElement());

        };
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
    }

    @Test
    void right() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node10 = tree.add(tree.root(), 10);
            Node<Integer> node5 = tree.add(tree.root(), 5);

            assertEquals(node10, tree.right(node8));
        };
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
    }

    @Test
    void root() {
        Consumer<Tree<Integer>> treeConsumer = (Tree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node10 = tree.add(tree.root(), 10);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node7 = tree.add(tree.root(), 7);
            Node<Integer> node4 = tree.add(tree.root(), 4);
            Node<Integer> node1 = tree.add(tree.root(), 1);

            assertEquals(node8, tree.root());
        };
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
    }

    @Test
    void parent() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node10 = tree.add(tree.root(), 10);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node12 = tree.add(tree.root(), 12);
            Node<Integer> node3 = tree.add(tree.root(), 3);
            Node<Integer> node7 = tree.add(tree.root(), 7);
            Node<Integer> node9 = tree.add(tree.root(), 9);
            Node<Integer> node11 = tree.add(tree.root(), 11);

            assertEquals(node8, tree.parent(node10));
            assertEquals(node8, tree.parent(node5));
            assertEquals(node10, tree.parent(node12));
            assertEquals(node5, tree.parent(node3));
            assertEquals(node5, tree.parent(node7));
            assertEquals(node10, tree.parent(node9));
            assertEquals(node12, tree.parent(node11));
        };
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new BinarySearchTree<>());
    }

    @Test
    void addRoot() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            tree.add(tree.root(), 8);
            int expected = 8;
            assertEquals(expected, tree.root().getElement());

            tree.add(tree.root(), 10);
            assertEquals(expected, tree.root().getElement());
        };
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new BinarySearchTree<>());
    }

    @Test
    void addLeft() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.addLeft(tree.root(), 8);
            Node<Integer> node7 = tree.addLeft(tree.root(), 7);
            Node<Integer> node5 = tree.addLeft(tree.root(), 5);

            List<Node<Integer>> expected = List.of(node5, node7, node8);
            assertThat(expected).hasSameElementsAs(tree.nodes());
        };
        treeConsumer.accept(new ArrayBinaryTree<>());
    }

    @Test
    void addLeftArrayBinaryTree() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.addLeft(tree.root(), 8);
            Node<Integer> node7 = tree.addLeft(tree.root(), 7);
            Node<Integer> node5 = tree.addLeft(tree.root(), 5);
            List<Node<Integer>> expected = List.of(node8, node7, node5);
            assertThat(expected).hasSameElementsAs(tree.nodes());

        };
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
//        treeConsumer.accept(new BinarySearchTree<>());

    }

    @Test
    void set() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node10 = tree.add(tree.root(), 10);
            Node<Integer> node5 = tree.add(tree.root(), 5);

            tree.set(node8, -1);
            assertEquals(-1, node8.getElement());
            tree.set(node5, 0);
            assertEquals(0, node5.getElement());
        };
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
    }

    @Test
    void removeLeftLeaf() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node3 = tree.add(tree.root(), 3);
            Node<Integer> node10 = tree.add(tree.root(), 10);

            tree.remove(node3);
            List<Node<Integer>> expected = List.of(node8, node10);
            Assertions.assertIterableEquals(expected, tree.nodes());
        };
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
    }

    @Test
    void removeRightLeaf() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node3 = tree.add(tree.root(), 3);
            Node<Integer> node10 = tree.add(tree.root(), 10);

            tree.remove(node10);
            List<Node<Integer>> expected = List.of(node3, node8);
            assertThat(expected).hasSameElementsAs(tree.nodes());
        };
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
    }

    @Test
    void removeNodeHasOneChildLeft() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node3 = tree.add(tree.root(), 3);

            tree.remove(node5);
            List<Node<Integer>> expected = List.of(node3, node8);
            assertThat(expected).hasSameElementsAs(tree.nodes());
        };
        treeConsumer.accept(new ArrayBinaryTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new BinarySearchTree<>());
    }

    @Test
    void removeNodeHasOneChildRight() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node6 = tree.add(tree.root(), 6);

            tree.remove(node5);
            List<Node<Integer>> expected = List.of(node8, node6);
            Set<Node<Integer>> exp = new HashSet<>(expected);

            assertIterableEquals(exp, new HashSet<>((Collection<Node<Integer>>) tree.nodes()));
        };
        treeConsumer.accept(new BinarySearchTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
    }

    @Test
    void removeNodeHasTwoChild() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node6 = tree.add(tree.root(), 6);
            Node<Integer> node3 = tree.add(tree.root(), 3);

            tree.remove(node5);

            List<Node<Integer>> actual = (List<Node<Integer>>) tree.nodes();
            Set<Node<Integer>> act = new HashSet<>(actual);
            assertIterableEquals(act, new HashSet<>((Collection<Node<Integer>>) tree.nodes()));

        };
        treeConsumer.accept(new BinarySearchTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
    }

    @Test
    void removeNodeHasTwoChildRightBranch() {
        Consumer<BinaryTree<Integer>> treeConsumer = (BinaryTree<Integer> tree) -> {
            Node<Integer> node8 = tree.add(tree.root(), 8);
            Node<Integer> node5 = tree.add(tree.root(), 5);
            Node<Integer> node10 = tree.add(tree.root(), 10);
            Node<Integer> node12 = tree.add(tree.root(), 12);
            Node<Integer> node9 = tree.add(tree.root(), 9);

            tree.remove(node10);
            List<Node<Integer>> expected = List.of(node5, node8, node9, node12);
            assertThat(expected).hasSameElementsAs(tree.nodes());
        };
        treeConsumer.accept(new BinarySearchTree<>());
        treeConsumer.accept(new LinkedBinaryTree<>());
        treeConsumer.accept(new ArrayBinaryTree<>());
    }
}