package com.github.na206851.lesson8.binary.balanced;

import com.github.na206851.lesson7.tree.Node;
import com.github.na206851.lesson7.tree.binary.LinkedBinaryTree;

import java.util.List;
import java.util.Objects;

public class version2<E extends Comparable<E>> extends LinkedBinaryTree<E> {
    public NodeRBT<E> root;

    public NodeRBT<E> findUncle(NodeRBT<E> node) {
        NodeRBT<E> parent = (NodeRBT<E>) node.parent;
        if (parent == null) {
            return null;
        }
        NodeRBT<E> grandFather = (NodeRBT<E>) parent.parent;
        if (grandFather == null) {
            return null;    //родитель корень , дяди нет
        }
        if (parent == grandFather.left) {
            return (NodeRBT<E>) grandFather.right;
        } else if (parent == grandFather.right) {
            return (NodeRBT<E>) grandFather.left;
        } else {
            return null;
        }
    }

    public void fixInside(NodeRBT<E> node) {
        while (node != root && ((NodeRBT<E>) node.parent).isRed) {
            NodeRBT<E> parent = (NodeRBT<E>) node.parent;
            NodeRBT<E> grandFather = (NodeRBT<E>) parent.parent;
            NodeRBT<E> uncle = findUncle(node);

            if (parent == grandFather.left) {
                if (uncle != null && uncle.isRed) {
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandFather.isRed = true;
                    node = grandFather;
                } else {
                    if (node == parent.right) {
                        node = parent;
                        leftTurn(node);
                    }
                    parent.isRed = false;
                    grandFather.isRed = true;
                    rightTurn(grandFather);
                }
            } else {
                if (uncle != null && uncle.isRed) {
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandFather.isRed = true;
                    node = grandFather;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rightTurn(node);
                    }
                    parent.isRed = false;
                    grandFather.isRed = true;
                    leftTurn(grandFather);
                }
            }
        }
        root.isRed = false;
    }

    public NodeRBT<E> search(E e) {
        NodeRBT<E> currentNode = root;
        while (currentNode != null) {
            if (currentNode.value.compareTo(e) > 0) {
                currentNode = (NodeRBT<E>) currentNode.left;
            } else if (currentNode.value.compareTo(e) < 0) {
                currentNode = (NodeRBT<E>) currentNode.right;
            } else {
                return currentNode;
            }
        }
        return null;
    }

    public NodeRBT<E> add(Node<E> node, E e) {
        NodeRBT<E> newNode = new NodeRBT<>(e, true);
        if (root == null) {
            newNode.isRed = false;
            root = newNode;
            return newNode;
        }

        NodeRBT<E> parent = (NodeRBT<E>) node;
        if (e.compareTo(parent.getElement()) < 0) {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
            } else {
                return add(parent.left, e);
            }
        } else {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
            } else {
                return add(parent.right, e);
            }
        }
        fixInside(newNode);
        return newNode;
    }

    public static void main(String[] args) {
        version2<Integer> tree = new version2<>();

        NodeRBT node10 = tree.add(tree.root, 10);
        NodeRBT node8 = tree.add(tree.root, 8);
        NodeRBT node5 = tree.add(tree.root, 5);
        NodeRBT node1 = tree.add(tree.root, 1);

        tree.printAscii(tree.root, 0);
        System.out.println("___________________");
        tree.remove(node8);
        tree.printAscii(tree.root, 0);
    }


//    public void remove(E e) {
//        NodeRBT<E> node = search(e);
//        if (node == null) return; // Узел не найден
//
//        // Если у узла два потомка, ищем замену (наименьший в правом поддереве)
//        if (node.left != null && node.right != null) {
//            NodeRBT<E> successor = (NodeRBT<E>) getMinValueInRightSubtree(node.right);
//            node.value = successor.value; // Заменяем значение
//            node = successor; // Теперь нужно удалить successor
//        }
//
//        // Теперь node имеет не более одного потомка
//        NodeRBT<E> child = (NodeRBT<E>) ((node.left != null) ? node.left : node.right);
//
//        if (!node.isRed) {
//            if (child != null && child.isRed) {
//                child.isRed = false;
//            } else {
//                fixInside((NodeRBT<E>) node.parent);
//            }
//        }
//
//        replaceNode(node, child);
//
//        if (node.parent == null && child != null) {
//            root = child;
//        }
//    }

    public void remove(NodeRBT<E> node) {
        if (node == null) {
            return;
        }

        NodeRBT<E> originalNode = node;
        boolean originalColor = node.isRed;
        NodeRBT<E> replacement;

        if (node.left == null) {
            replacement = (NodeRBT<E>) node.right;
            replaceNode(node, replacement);
        } else if (node.right == null) {
            replacement = (NodeRBT<E>) node.left;
            replaceNode(node, replacement);
        } else {
            NodeRBT<E> successor = findMinNode((NodeRBT<E>) node.right);
            originalColor = successor.isRed;
            replacement = (NodeRBT<E>) successor.right;

            if (successor.parent != node) {
                replaceNode(successor, replacement);
                successor.right = node.right;
                if (successor.right != null) {
                    validate(successor.right).parent = successor;
                }
            }
            replaceNode(node, successor);
            successor.left = node.left;
            if (successor.left != null) {
                validate(successor.left).parent = successor;
            }
            successor.isRed = node.isRed;

            if (!originalColor) {
                fixAfterRemove(replacement, (NodeRBT<E>) node.parent);
            }
        }
    }

    private void fixAfterRemove(NodeRBT<E> node, NodeRBT<E> parent) {
        // Проверяем, что родитель не null (иначе не сможем работать с его полями)
        while ((node != root) && (node == null || !node.isRed)) {
            if (parent == null) break;  // Если родитель null, выходим из цикла

            if (node == parent.left) {  // Если узел - левый потомок
                NodeRBT<E> sibling = (NodeRBT<E>) parent.right;

                if (sibling != null && sibling.isRed) {  // Проверка на null для sibling
                    sibling.isRed = false;
                    parent.isRed = true;
                    leftTurn(parent);
                    sibling = (NodeRBT<E>) parent.right;
                }

                if (sibling == null ||
                        ((sibling.left == null || !((NodeRBT<E>) sibling.left).isRed) &&
                                (sibling.right == null || !((NodeRBT<E>) sibling.right).isRed))) {
                    if (sibling != null) sibling.isRed = true;  // Проверка на null
                    node = parent;
                    parent = (NodeRBT<E>) node.parent;
                } else {
                    if (sibling.right == null || !((NodeRBT<E>) sibling.right).isRed) {
                        if (sibling.left != null)  // Проверка на null для sibling.left
                            ((NodeRBT<E>) sibling.left).isRed = false;
                        sibling.isRed = true;
                        rightTurn(sibling);
                        sibling = (NodeRBT<E>) parent.right;
                    }

                    if (sibling != null) {  // Проверка на null для sibling
                        sibling.isRed = parent.isRed;
                        if (sibling.right != null)  // Проверка на null для sibling.right
                            ((NodeRBT<E>) sibling.right).isRed = false;
                    }
                    parent.isRed = false;
                    leftTurn(parent);
                    node = root;
                }
            } else {  // Если узел - правый потомок (симметричная обработка)
                NodeRBT<E> sibling = (NodeRBT<E>) parent.left;

                if (sibling != null && sibling.isRed) {  // Проверка на null для sibling
                    sibling.isRed = false;
                    parent.isRed = true;
                    rightTurn(parent);
                    sibling = (NodeRBT<E>) parent.left;
                }

                if (sibling == null ||
                        ((sibling.left == null || !((NodeRBT<E>) sibling.left).isRed) &&
                                (sibling.right == null || !((NodeRBT<E>) sibling.right).isRed))) {
                    if (sibling != null) sibling.isRed = true;  // Проверка на null
                    node = parent;
                    parent = (NodeRBT<E>) node.parent;
                } else {
                    if (sibling.left == null || !((NodeRBT<E>) sibling.left).isRed) {
                        if (sibling.right != null)  // Проверка на null для sibling.right
                            ((NodeRBT<E>) sibling.right).isRed = false;
                        sibling.isRed = true;
                        leftTurn(sibling);
                        sibling = (NodeRBT<E>) parent.left;
                    }

                    if (sibling != null) {  // Проверка на null для sibling
                        sibling.isRed = parent.isRed;
                        if (sibling.left != null)  // Проверка на null для sibling.left
                            ((NodeRBT<E>) sibling.left).isRed = false;
                    }
                    parent.isRed = false;
                    rightTurn(parent);
                    node = root;
                }
            }
        }

        if (node != null) node.isRed = false;  // Проверка на null для node
    }

    public NodeRBT<E> findMinNode(NodeRBT<E> node) {
        if (node == null) return null;
        while (node.left != null) {
            node = (NodeRBT<E>) node.left;
        }
        return node;
    }

    private void replaceNode(NodeRBT<E> node, NodeRBT<E> child) {
        if (node.parent == null) {
            root = child;
        } else if (node == ((NodeRBT<E>) node.parent).left) {
            ((NodeRBT<E>) node.parent).left = child;
        } else {
            ((NodeRBT<E>) node.parent).right = child;
        }
        if (child != null) {
            child.parent = node.parent;
        }
    }


    //перевязывает родителя и ребенка
    public void relink(NodeRBT<E> parent, NodeRBT<E> child, boolean makeLeftChild) {
        if (makeLeftChild) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if (child != null) {
            child.parent = parent;
        }
    }

    public void leftTurn(NodeRBT<E> node) {
        if (node == null || node.right == null) return;

        NodeRBT<E> rightChild = (NodeRBT<E>) node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) ((NodeRBT<E>) rightChild.left).parent = node;
        rightChild.parent = node.parent;
        if (node.parent == null) root = rightChild;
        else if (node == ((NodeRBT<E>) node.parent).left)
            ((NodeRBT<E>) node.parent).left = rightChild;
        else ((NodeRBT<E>) node.parent).right = rightChild;
        rightChild.left = node;
        node.parent = rightChild;
    }

    public void rightTurn(NodeRBT<E> node) {
        if (node == null || node.left == null) return;
        NodeRBT<E> leftChild = (NodeRBT<E>) node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) ((NodeRBT<E>) leftChild.right).parent = node;
        leftChild.parent = node.parent;
        if (node.parent == null) root = leftChild;
        else if (node == ((NodeRBT<E>) node.parent).left)
            ((NodeRBT<E>) node.parent).left = leftChild;
        else ((NodeRBT<E>) node.parent).right = leftChild;
        leftChild.right = node;
        node.parent = leftChild;
    }

    public void swapColor(Node<E> node) {
        NodeRBT<E> nodeSwap = (NodeRBT<E>) node;
        //меняем цвет переданного узла
        if (node == root) {
            ((NodeRBT<E>) node).isRed = false;
        } else {
            if (nodeSwap.isRed) {
                nodeSwap.isRed = false;
            }
        }
    }

    public static class NodeRBT<E> extends LinkedBinaryTree.NodeImpl<E> {
        public E value;
        public Node<E> parent;
        public Node<E> left;
        public Node<E> right;
        boolean isRed;

        public NodeRBT(E value, boolean isRed) {
            this.value = value;
            this.isRed = isRed;
            parent = left = right = null;
        }

        public NodeRBT(boolean isRed) {
            this.isRed = isRed;
        }

        public NodeRBT() {
        }

        @Override
        public E getElement() {
            return this.value;
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

            NodeRBT<E> other = (NodeRBT<E>) object;
            return Objects.equals(this.value, other.value);
        }
    }
}
