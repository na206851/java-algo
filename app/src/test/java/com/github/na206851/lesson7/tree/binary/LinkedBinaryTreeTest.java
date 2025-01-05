package com.github.na206851.lesson7.tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedBinaryTreeTest {

    @Test
    void root() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        int expected = 1;
        int a = 0;
        int b = 0;
        assertEquals(expected, tree.root().getElement());
    }

    @Test
    void addLeftChildToRoot() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        tree.addLeft(tree.root(), 2);
        assertEquals(2, tree.left(tree.root()).getElement());
        System.out.println(tree.left(tree.root()).getElement().toString());

    }

    @Test
    void addRightChildToRoot() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        tree.addRight(tree.root(), 2);
        assertEquals(2, tree.right(tree.root()).getElement());
    }

    @Test
    void setTest() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        tree.addLeft(tree.root(), 3);
        tree.set(tree.root(), -1);
        assertEquals(-1, tree.set(tree.root(), -1));
    }

    @Test
    void setRoot() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        tree.set(tree.root(), 0);
        assertEquals(0, tree.set(tree.root(), 0));
    }

    @Test
    void getParent() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        tree.addLeft(tree.root(), 2);
        tree.addRight(tree.root(), 3);
        assertEquals(1, tree.parent(tree.left(tree.root())).getElement());
    }

    @Test
    void addMethodItTreeEmpty() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.add(new LinkedBinaryTree.NodeImpl<>(), -1);
        assertEquals(-1, tree.root().getElement());
    }

    @Test
    void addMethodInRightNode() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.add(tree.root, -1);
        tree.add(tree.root, 0);
        assertEquals(0, tree.right(tree.root()).getElement());
    }

    @Test
    void addMethodInRightNodeIfLeftNotEmpty() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(0);
        tree.add(tree.root, 1);
        List<Integer> expected = List.of(0, 1);
        Assertions.assertIterableEquals(expected, tree.inOrder(tree.validate(tree.root), new ArrayList<>()));
    }

    @Test
    void validNode() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.validate(new LinkedBinaryTree.NodeImpl<>(2));
    }

    @Test
    void searchParentOfArbitraryTree() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
        tree.root = new LinkedBinaryTree.NodeImpl<>(1);
        ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).left = new LinkedBinaryTree.NodeImpl<>(2);
        ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).right = new LinkedBinaryTree.NodeImpl<>(3);
        ((LinkedBinaryTree.NodeImpl<Integer>) ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).left).left = new LinkedBinaryTree.NodeImpl<>(4);
        ((LinkedBinaryTree.NodeImpl<Integer>) ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).left).right = new LinkedBinaryTree.NodeImpl<>(5);
        ((LinkedBinaryTree.NodeImpl<Integer>) ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).right).left = new LinkedBinaryTree.NodeImpl<>(6);
        ((LinkedBinaryTree.NodeImpl<Integer>) ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).right).right = new LinkedBinaryTree.NodeImpl<>(7);

        assertEquals(1, tree.parent(((LinkedBinaryTree.NodeImpl<Integer>) tree.root).right).getElement());
        assertEquals(1, tree.parent(((LinkedBinaryTree.NodeImpl<Integer>) tree.root).left).getElement());
        assertEquals(2, tree.parent(((LinkedBinaryTree.NodeImpl<Integer>) ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).left).right).getElement());
        assertEquals(2, tree.parent(((LinkedBinaryTree.NodeImpl<Integer>) ((LinkedBinaryTree.NodeImpl<Integer>) tree.root).left).right).getElement());
    }

    @Test
    void createArbitratyTree() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
//      /  tree.
//       / tree.add(new LinkedBinaryTree.NodeImpl<>(), 1);
    }

    public static LinkedBinaryTree.NodeImpl defaultTree() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);
        LinkedBinaryTree.NodeImpl<Integer> node4 = new LinkedBinaryTree.NodeImpl<>(4);
        LinkedBinaryTree.NodeImpl<Integer> node5 = new LinkedBinaryTree.NodeImpl<>(5);
        LinkedBinaryTree.NodeImpl<Integer> node6 = new LinkedBinaryTree.NodeImpl<>(6);
        LinkedBinaryTree.NodeImpl<Integer> node7 = new LinkedBinaryTree.NodeImpl<>(7);
        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        return node1;
    }

    @Test
    void testIterator() {
        List<Integer> expected = new ArrayList<>(List.of(4, 2, 5, 1, 6, 3, 7));
        LinkedBinaryTree.iteratorTree iterator = new LinkedBinaryTree.iteratorTree(defaultTree());
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add((Integer) iterator.next());
        }
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void testIteratorHasNext() {
        LinkedBinaryTree.iteratorTree iterator = new LinkedBinaryTree.iteratorTree(defaultTree());
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorHasNextNegativeCase() {
        /**tree.printAscii(node1, 0);
         *            1
         *         2     3
         *      4   5  6   7
         */
        LinkedBinaryTree.iteratorTree iterator = new LinkedBinaryTree.iteratorTree(defaultTree());
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void testInOrder() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);

        tree.root = node1;
        node1.left = node2;
        node1.right = node3;

        List<Integer> expected = Arrays.asList(2, 1, 3);
        List<Integer> actual = tree.inOrder(node1, new ArrayList<>());
        assertEquals(expected, actual, "list not equals");
    }

    @Test
    void testRemoveLeaf() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);

        tree.root = node1;
        node1.left = node2;
        node1.right = node3;

        tree.remove(node2);

        List<Integer> expected = Arrays.asList(1, 3);
        List<Integer> actual = tree.inOrder(node1, new ArrayList<>());
        assertEquals(expected, actual, "list not equals");

        tree.remove(node3);
        List<Integer> expected2 = Arrays.asList(1);
        List<Integer> actual2 = tree.inOrder(node1, new ArrayList<>());
        assertEquals(expected2, actual2, "list not equals");
    }

    @Test
    void testRemoveNodeHaveOneChild() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);
        LinkedBinaryTree.NodeImpl<Integer> node4 = new LinkedBinaryTree.NodeImpl<>(4);
        LinkedBinaryTree.NodeImpl<Integer> node5 = new LinkedBinaryTree.NodeImpl<>(5);

        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        System.out.println(tree.inOrder(node1, new ArrayList<>()) + " before manipulation ");
        tree.remove(node2);
        System.out.println(tree.inOrder(node1, new ArrayList<>()) + " after manipulation");
    }

    @Test
    void findMaxValueInBinaryTree() {
        //создать дефолтное дерево
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);
        LinkedBinaryTree.NodeImpl<Integer> node4 = new LinkedBinaryTree.NodeImpl<>(4);
        LinkedBinaryTree.NodeImpl<Integer> node5 = new LinkedBinaryTree.NodeImpl<>(5);
        LinkedBinaryTree.NodeImpl<Integer> node6 = new LinkedBinaryTree.NodeImpl<>(6);
        LinkedBinaryTree.NodeImpl<Integer> node7 = new LinkedBinaryTree.NodeImpl<>(7);
        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        assertEquals(node7.getElement(), tree.inOrder(tree.root, new ArrayList<>()).stream().max(Integer::compareTo).get());

    }

    @Test
    void removeNodeHasOneLeftChild() {
        /**
         *        1
         *     2     3
         *   4     5
         */
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);
        LinkedBinaryTree.NodeImpl<Integer> node4 = new LinkedBinaryTree.NodeImpl<>(4);
        LinkedBinaryTree.NodeImpl<Integer> node5 = new LinkedBinaryTree.NodeImpl<>(5);
        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;

        tree.remove(node2);
        List<Integer> removeNode2Expected = new ArrayList<>(List.of(4, 1, 5, 3));
        Assertions.assertEquals(removeNode2Expected, tree.inOrder(node1, new ArrayList<>()));

        tree.remove(node3);
        List<Integer> removeNode3Expected = new ArrayList<>(List.of(4, 1, 5));
        Assertions.assertEquals(removeNode3Expected, tree.inOrder(node1, new ArrayList<>()));
//      tree.printAscii((LinkedBinaryTree.NodeImpl<Integer>) tree.root, 0);
        /**
         * view binary tree
         *      1
         *    4   5
         */
    }

    @Test
    void removeNodeHasOneRightChild() {
        /**
         *        1
         *     2     3
         *       4     5
         */
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.NodeImpl<Integer> node1 = new LinkedBinaryTree.NodeImpl<>(1);
        LinkedBinaryTree.NodeImpl<Integer> node2 = new LinkedBinaryTree.NodeImpl<>(2);
        LinkedBinaryTree.NodeImpl<Integer> node3 = new LinkedBinaryTree.NodeImpl<>(3);
        LinkedBinaryTree.NodeImpl<Integer> node4 = new LinkedBinaryTree.NodeImpl<>(4);
        LinkedBinaryTree.NodeImpl<Integer> node5 = new LinkedBinaryTree.NodeImpl<>(5);
        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;

        tree.remove(node2);
        List<Integer> removeNode2Expected = new ArrayList<>(List.of(4, 1, 3, 5));
        Assertions.assertEquals(removeNode2Expected, tree.inOrder(node1, new ArrayList<>()));

        tree.remove(node3);
        List<Integer> removeNode3Expected = new ArrayList<>(List.of(4, 1, 5));
        Assertions.assertEquals(removeNode3Expected, tree.inOrder(node1, new ArrayList<>()));
        tree.printAscii((LinkedBinaryTree.NodeImpl<Integer>) tree.root, 0);
        /**
         * view binary tree
         *      1
         *    4   5
         */
    }
}
