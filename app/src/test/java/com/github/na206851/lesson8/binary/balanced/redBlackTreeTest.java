package com.github.na206851.lesson8.binary.balanced;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class redBlackTreeTest {
    RedBlackTree<Integer> tree = new RedBlackTree<>();

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            tree.add(tree.root, i);
        }
        tree.printAscii(tree.root, 0);
        tree.remove(tree.root);
    }

    @Test
    public void testRightAddition() {
        RedBlackTree.NodeRBT<Integer> node5 = tree.root = new RedBlackTree.NodeRBT<>(false, 5);
        RedBlackTree.NodeRBT<Integer> node7 = (RedBlackTree.NodeRBT<Integer>) (tree.root.right = new RedBlackTree.NodeRBT<>(true, 7));
        RedBlackTree.NodeRBT<Integer> node8 = (RedBlackTree.NodeRBT<Integer>) (tree.validate(tree.root.right).right = new RedBlackTree.NodeRBT<>(false, 8));

        List<Integer> expected = new ArrayList<>(List.of(5, 7, 8));
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    public void testLeftAddition() {
        RedBlackTree.NodeRBT<Integer> node5 = tree.root = new RedBlackTree.NodeRBT<>(false, 5);
        RedBlackTree.NodeRBT<Integer> node7 = (RedBlackTree.NodeRBT<Integer>) (tree.root.left = new RedBlackTree.NodeRBT<>(true, 7));
        RedBlackTree.NodeRBT<Integer> node8 = (RedBlackTree.NodeRBT<Integer>) (tree.validate(tree.root.left).left = new RedBlackTree.NodeRBT<>(false, 8));

        List<Integer> expected = new ArrayList<>(List.of(8, 7, 5));
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    public void testLeftTurn() {
        RedBlackTree.NodeRBT<Integer> node5 = tree.root = new RedBlackTree.NodeRBT<>(false, 5);
        RedBlackTree.NodeRBT<Integer> node7 = (RedBlackTree.NodeRBT<Integer>) (tree.root.right = new RedBlackTree.NodeRBT<>(true, 7));
        RedBlackTree.NodeRBT<Integer> node8 = (RedBlackTree.NodeRBT<Integer>) (tree.validate(tree.root.right).right = new RedBlackTree.NodeRBT<>(false, 8));

        tree.leftTurn(node5);
        List<Integer> expected = new ArrayList<>(List.of(5, 7, 8));
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    public void testRightTurn() {
        RedBlackTree.NodeRBT<Integer> node5 = tree.root = new RedBlackTree.NodeRBT<>(false, 5);
        RedBlackTree.NodeRBT<Integer> node7 = (RedBlackTree.NodeRBT<Integer>) (tree.root.left = new RedBlackTree.NodeRBT<>(true, 7));
        RedBlackTree.NodeRBT<Integer> node8 = (RedBlackTree.NodeRBT<Integer>) (tree.validate(tree.root.left).left = new RedBlackTree.NodeRBT<>(false, 8));

        tree.rightTurn(node5);
        tree.printAscii(tree.root, 0);
        System.out.println(tree.inOrder(tree.root, new ArrayList<>()));
        List<Integer> expected = new ArrayList<>(List.of(8, 7, 5));
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    public void testAddInEmptyTree() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);

        assertFalse(node10.isRed);
        assertEquals(10, tree.root.getElement());
    }

    @Test
    public void testAdd() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);

        tree.printAscii(tree.root, 0);
        assertFalse(node10.isRed);
        assertTrue(node8.isRed);
        assertTrue(node12.isRed);
    }

    @Test
    public void testColorGrandfather() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);
        RedBlackTree.NodeRBT<Integer> node15 = tree.add(tree.root, 15);

        assertTrue(node15.isRed);
        assertFalse(node12.isRed);
    }

    @Test
    public void testAdditionTenElementSequentially() {
        int count = 10;
        List<Integer> expected = new ArrayList<>();
        while (count > 0) {
            tree.add(tree.root, count);
            expected.add(count);
            count--;
        }
        expected.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return -1;
                } else if (o1 > o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
        assertFalse(tree.search(10).isRed);
        assertTrue(tree.search(1).isRed);
        assertFalse(tree.search(5).isRed);
    }

    @Test
    public void testAddColorUncleBlack() {
        int count = 10;
        List<Integer> expected = new ArrayList<>();
        while (count > 0) {
            tree.add(tree.root, count);
            expected.add(count);
            count--;
        }
        expected.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return -1;
                } else if (o1 > o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        assertTrue(tree.search(1).isRed);
        tree.add(tree.root, 0);
        assertFalse(tree.search(4).isRed);
    }

    @Test
    public void testAddColorUncleRed() {
        int count = 10;
        List<Integer> expected = new ArrayList<>();
        while (count > 0) {
            tree.add(tree.root, count);
            expected.add(count);
            count--;
        }
        expected.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return -1;
                } else if (o1 > o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        assertTrue(tree.search(1).isRed);
        tree.printAscii(tree.root, 0);
    }

    @Test
    public void removeEmptyTree() {
        assertThrows(NullPointerException.class, () -> tree.remove(tree.root));

    }

    @Test
    public void removeLeaf() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);

        assertEquals(12, tree.remove(node12).getElement());

        List<Integer> expected = List.of(8, 10);
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));

        tree.remove(node8);
        assertIterableEquals(List.of(10), tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    public void removeNodeWithOneChild() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 5);
        RedBlackTree.NodeRBT<Integer> node15 = tree.add(tree.root, 15);
        RedBlackTree.NodeRBT<Integer> node11 = tree.add(tree.root, 11);

        tree.remove(node15);
        assertTrue(tree.validate(tree.root.right).isRed);
        assertFalse(tree.validate(tree.root).isRed);
        assertFalse(tree.root.isRed);
        assertFalse(node10.isRed);
    }

    @Test
    public void removeNodeWithTwoChild() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 5);
        RedBlackTree.NodeRBT<Integer> node15 = tree.add(tree.root, 15);
        RedBlackTree.NodeRBT<Integer> node20 = tree.add(tree.root, 20);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);

        assertFalse(node15.isRed);
        tree.remove(node15);

        assertFalse(node20.isRed);
        assertFalse(node15.isRed);
    }

    @Test
    public void removeRoot() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);

        assertFalse(node10.isRed);
        tree.remove(node10);

        assertFalse(node12.isRed);
        tree.remove(node12);
    }

    @Test
    public void searchInEmptyTree() {
        assertNull(tree.search(0));
    }

    @Test
    public void searchNodeNotEmptyTree() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);

        assertEquals(node12, tree.search(12));
        assertEquals(node8, tree.search(8));
        assertEquals(node10, tree.search(10));
    }

    @Test
    public void addDoubleNode() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);
        RedBlackTree.NodeRBT<Integer> doubleNode = tree.add(tree.root, 10);

        List<Integer> expected = List.of(8, 10, 12);
        assertIterableEquals(expected, tree.inOrder(tree.root, new ArrayList<>()));
    }

    @Test
    public void rootAlwaysBlack() {
        RedBlackTree.NodeRBT<Integer> node10 = tree.add(tree.root, 10);
        assertFalse(node10.isRed);

        RedBlackTree.NodeRBT<Integer> node8 = tree.add(tree.root, 8);
        assertFalse(node10.isRed);

        RedBlackTree.NodeRBT<Integer> node12 = tree.add(tree.root, 12);
        assertFalse(node10.isRed);
    }

}