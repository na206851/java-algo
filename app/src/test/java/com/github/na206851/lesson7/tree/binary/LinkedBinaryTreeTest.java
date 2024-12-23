package com.github.na206851.lesson7.tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void getParent() {      //добавить метод добавления родителя не знаю
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(1);
        tree.addLeft(tree.root(), 2);
        tree.addRight(tree.root(), 3);
        assertEquals(1, tree.parent(tree.left(tree.root())));
    }

    @Test
    void addMethodItTreeEmpty() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.add(new LinkedBinaryTree.NodeImpl<>(), -1);
        assertEquals(-1, tree.root().getElement());
    }

    @Test
    void addMethodInLeftNode() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.add(new LinkedBinaryTree.NodeImpl<>(), -1);
        tree.add(new LinkedBinaryTree.NodeImpl<>(), 0);
        assertEquals(0, tree.left(tree.root()).getElement());
        System.out.println(tree.left(tree.root()).getElement());
    }

    @Test
    void addMethodInRightNodeIfLeftNotEmpty() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        tree.addRoot(0);
        tree.add(new LinkedBinaryTree.NodeImpl<>(), 1);
        tree.add(new LinkedBinaryTree.NodeImpl<>(2), 2);

        System.out.println();
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

    @Test
    void testIterator() {
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

        List<Integer> expected = new ArrayList<>(List.of(4, 2, 5, 1, 6, 3, 7));
        LinkedBinaryTree.iteratorTree iterator = new LinkedBinaryTree.iteratorTree(node1);
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add((Integer) iterator.next());
        }
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void testIteratorHasNext() {
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

        LinkedBinaryTree.iteratorTree iterator = new LinkedBinaryTree.iteratorTree(node1);
        assertTrue(iterator.hasNext());

    }

    @Test
    void testIteratorHasNextNegativeCase() {
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

        LinkedBinaryTree.iteratorTree iterator = new LinkedBinaryTree.iteratorTree(node7);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        assertFalse(iterator.hasNext());
    }
}
