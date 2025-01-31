package com.github.na206851.lesson7.tree.binary;

import com.github.na206851.lesson7.tree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> test = new BinarySearchTree<>();

    @Test
    void addRoot() {
        test.addRoot(1);
        assertEquals(1, test.root.getElement());
        assertEquals(1, test.size());
    }

    @Test
    void randomInsertGivesSortedResult() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addRoot(8);
        tree.add(tree.root, 5);
        tree.add(tree.root, 6);
        List<Integer> expected = List.of(5, 6, 8);
        System.out.println(tree.inOrder(tree.root, new ArrayList<>()));
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void addNodeIfRootIsNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        List<Integer> expected = List.of(8);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void addRootAndOneLeftChild() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 5);
        List<Integer> expected = List.of(5, 8);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void addRootAndOneRightChild() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 10);
        List<Integer> expected = List.of(8, 10);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void addTestYoutube() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 3);
        tree.add(tree.root, 10);
        tree.add(tree.root, 1);
        tree.add(tree.root, 6);
        tree.add(tree.root, 14);
        tree.add(tree.root, 4);
        tree.add(tree.root, 7);
        tree.add(tree.root, 13);
        tree.add(tree.root, 20);
        tree.add(tree.root, 0);

        List<Integer> expected = List.of(0, 1, 3, 4, 6, 7, 8, 10, 13, 14, 20);
        List<Integer> actual = tree.inOrder(tree.root, new ArrayList<>());
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void addLeft() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addRoot(6);
        tree.add(tree.root, 5);
        tree.add(tree.root, 4);

        List<Integer> expected = List.of(4, 5, 6);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));

        tree.addLeft(tree.root, 1);
        List<Integer> expectedAfterUseAddLeftMethod = List.of(1, 4, 5, 6);
        Assertions.assertIterableEquals(expectedAfterUseAddLeftMethod, tree.inOrder(tree.root, new ArrayList<>()));

    }

    @Test
    void addRight() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addRoot(6);
        tree.add(tree.root, 7);
        tree.add(tree.root, 8);

        List<Integer> expected = List.of(6, 7, 8);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));

        tree.addRight(tree.root, 10);
        List<Integer> expectedAfterUseAddLeftMethod = List.of(6, 7, 8, 10);
        Assertions.assertIterableEquals(expectedAfterUseAddLeftMethod, tree.inOrder(tree.root, new ArrayList<>()));

    }

    @Test
    void set() {
        test.addRoot(1);
        test.set(test.root, 0);
        assertEquals(0, test.root.getElement());

        test.set(test.root, -1);
        assertEquals(-1, test.root.getElement());
    }

    @Test
    void left() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.root = new BinarySearchTree.NodeImpl<>(5);
        tree.add(tree.root, 4);
        tree.add(tree.root, 3);

        assertEquals(4, (int) tree.left(tree.root).getElement());
        assertEquals(3, (int) tree.left(test.validate(tree.root).left).getElement());
    }

    @Test
    void right() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.root = new BinarySearchTree.NodeImpl<>(5);
        tree.add(tree.root, 6);
        tree.add(tree.root, 7);

        assertEquals(6, (int) tree.right(tree.root).getElement());
        assertEquals(7, (int) tree.right(test.validate(tree.root).right).getElement());
    }

    @Test
    void parent() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 5);
        tree.add(tree.root, 6);
        tree.add(tree.root, 4);
        assertEquals(5, (int) tree.parent(test.validate(tree.root).left).getElement());
        assertEquals(5, (int) tree.parent(test.validate(tree.root).right).getElement());
    }

    @Test
    void testRemoveRoot() {
        /*
                8
              5   10
         */
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 5);
        tree.add(tree.root, 10);
        tree.remove(tree.root);

        List<Integer> expected = List.of(5, 10);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void testRemoveLeftLeaf() {
        /*
                8
              5   10
         */
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        Node<Integer> node5 = tree.add(tree.root, 5);
        tree.add(tree.root, 10);

        tree.remove(node5);

        List<Integer> expected = List.of(8, 10);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void testRemoveRightLeaf() {
        /*
                8
              5   10
         */
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 5);
        Node node10 = tree.add(tree.root, 10);

        tree.remove(node10);

        List<Integer> expected = List.of(5, 8);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void RemoveParentOneChildLeft() {
        /*
                8
             5     10
          3            11
         */

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        Node<Integer> node5 = tree.add(tree.root, 5);
        tree.add(tree.root, 10);
        tree.add(tree.root, 3);
        tree.add(tree.root, 11);

        tree.remove(node5);
        List<Integer> expected = List.of(3, 8, 10, 11);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void RemoveParentOneChildRight() {
        /*
                8
             5     10
          3            11
         */

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 5);
        Node<Integer> node10 = tree.add(tree.root, 10);
        tree.add(tree.root, 3);
        tree.add(tree.root, 11);

        tree.remove(node10);
        List<Integer> expected = List.of(3, 5, 8, 11);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void RemoveLeftParentHasTwoChild() {
        /*
                8
             5     10
          4   6   9   11
        */
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Node<Integer> treeRoot = tree.add(tree.root, 8);
        Node<Integer> node5 = tree.add(tree.root, 5);
        Node<Integer> node10 = tree.add(tree.root, 10);
        Node<Integer> node4 = tree.add(tree.root, 4);
        Node<Integer> node6 = tree.add(tree.root, 6);
        Node<Integer> node9 = tree.add(tree.root, 9);
        Node<Integer> node11 = tree.add(tree.root, 11);

        tree.remove(node10);
        List<Integer> expectedBeforeRemoveNode10 = List.of(4, 5, 6, 8, 9, 11);
        Assertions.assertIterableEquals(expectedBeforeRemoveNode10, tree.inOrder(tree.root, new ArrayList<>()));

        tree.remove(node5);
        List<Integer> expectedBeforeRemoveNode5 = List.of(4, 6, 8, 9, 11);
        Assertions.assertIterableEquals(expectedBeforeRemoveNode5, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    void searchParentIsRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(tree.root, 8);
        tree.add(tree.root, 10);
        tree.add(tree.root, 5);

        int expected = 8;
        assertEquals(expected, tree.parent(tree.validate(tree.root).left).getElement());
        assertEquals(expected, tree.parent(tree.validate(tree.root).right).getElement());
    }

    @Test
    void searchParentThreeLevel() {
        /*
                   8
              5        10
          4     6    9     12
       3
         */
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Node<Integer> node8 = tree.add(tree.root, 8);
        Node<Integer> node10 = tree.add(tree.root, 10);
        Node<Integer> node9 = tree.add(tree.root, 9);
        Node<Integer> node12 = tree.add(tree.root, 12);

        Node<Integer> node5 = tree.add(tree.root, 5);
        Node<Integer> node6 = tree.add(tree.root, 6);
        Node<Integer> node4 = tree.add(tree.root, 4);
        Node<Integer> node3 = tree.add(tree.root, 3);

        assertEquals(node4, tree.parent(node3));
        assertEquals(node5, tree.parent(node6));
        assertEquals(node5, tree.parent(node4));
        assertEquals(node8, tree.parent(node5));
        assertEquals(node10, tree.parent(node12));
        assertEquals(node10, tree.parent(node9));
        assertEquals(node8, tree.parent(node10));
    }

    @Test
    void testIterable() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Node<Integer> node8 = tree.add(tree.root, 8);
        Node<Integer> node10 = tree.add(tree.root, 10);
        Node<Integer> node9 = tree.add(tree.root, 9);

        List<Node<Integer>> expected = List.of(node8, node9, node10);

        Assertions.assertIterableEquals(expected, tree.nodes());
    }

    @Test
    void testIterableRootIsNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        List<Node<Integer>> expected = List.of();

        Assertions.assertIterableEquals(expected, tree.nodes());
    }

    @Test
    void testIterableOnlyRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Node<Integer> node10 = tree.add(tree.root, 10);
        List<Node<Integer>> expected = List.of(node10);

        Assertions.assertIterableEquals(expected, tree.nodes());
    }

    @Test
    void testIterableThreeLevelTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Node<Integer> node8 = tree.add(tree.root, 8);
        Node<Integer> node10 = tree.add(tree.root, 10);
        Node<Integer> node9 = tree.add(tree.root, 9);
        Node<Integer> node7 = tree.add(tree.root, 7);
        Node<Integer> node6 = tree.add(tree.root, 6);
        Node<Integer> node11 = tree.add(tree.root, 11);
        Node<Integer> node13 = tree.add(tree.root, 13);

        List<Node<Integer>> expected = List.of(node6, node7, node8, node9, node10, node11, node13);

        Assertions.assertIterableEquals(expected, tree.nodes());
    }
}