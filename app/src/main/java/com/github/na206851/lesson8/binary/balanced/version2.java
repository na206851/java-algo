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

    public NodeRBT<E> search(E e) throws NullPointerException {
        NodeRBT<E> currentNode = root;
        while (currentNode != null) {
            if (currentNode.value.compareTo(e) > 0) {
                currentNode = (NodeRBT<E>) currentNode.left;
            } else if (currentNode.value.compareTo(e) < 0) {
                currentNode = (NodeRBT<E>) currentNode.right;
            } else if (currentNode.value.compareTo(e) == 0) {
                return currentNode;
            } else {
                throw new NullPointerException();
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
                relink(parent, newNode, true);
            } else {
                return add(parent.left, e);
            }
        } else if (e.compareTo(parent.getElement()) > 0) {
            if (parent.right == null) {
                relink(parent, newNode, false);
            } else {
                return add(parent.right, e);
            }
        } else {
            return null;
        }
        fixInside(newNode);
        return newNode;
    }


    public NodeRBT<E> remove(NodeRBT<E> node) {
        if (node == null) {
            NullPointerException exception;
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
        return originalNode;
    }

    private void fixAfterRemove(NodeRBT<E> node, NodeRBT<E> parent) {
        // Проверяем, что родитель не null
        while ((node != root) && (node == null || !node.isRed)) {
            if (parent == null) break;  // Если родитель null, выходим из цикла

            if (node == parent.left) {  // Если узел - левый потомок
                NodeRBT<E> sibling = (NodeRBT<E>) parent.right;

                if (sibling != null && sibling.isRed) {
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
                        if (sibling.left != null)
                            ((NodeRBT<E>) sibling.left).isRed = false;
                        sibling.isRed = true;
                        rightTurn(sibling);
                        sibling = (NodeRBT<E>) parent.right;
                    }

                    if (sibling != null) {
                        sibling.isRed = parent.isRed;
                        if (sibling.right != null)
                            ((NodeRBT<E>) sibling.right).isRed = false;
                    }
                    parent.isRed = false;
                    leftTurn(parent);
                    node = root;
                }
            } else {  // Если узел - правый потомок
                NodeRBT<E> sibling = (NodeRBT<E>) parent.left;

                if (sibling != null && sibling.isRed) {
                    sibling.isRed = false;
                    parent.isRed = true;
                    rightTurn(parent);
                    sibling = (NodeRBT<E>) parent.left;
                }

                if (sibling == null ||
                        ((sibling.left == null || !((NodeRBT<E>) sibling.left).isRed) &&
                                (sibling.right == null || !((NodeRBT<E>) sibling.right).isRed))) {
                    if (sibling != null) sibling.isRed = true;
                    node = parent;
                    parent = (NodeRBT<E>) node.parent;
                } else {
                    if (sibling.left == null || !((NodeRBT<E>) sibling.left).isRed) {
                        if (sibling.right != null)
                            ((NodeRBT<E>) sibling.right).isRed = false;
                        sibling.isRed = true;
                        leftTurn(sibling);
                        sibling = (NodeRBT<E>) parent.left;
                    }

                    if (sibling != null) {
                        sibling.isRed = parent.isRed;
                        if (sibling.left != null)
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
            relink((NodeRBT<E>) node.parent, child, true);
        } else {
            relink((NodeRBT<E>) node.parent, child, false);
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
        if (rightChild.left != null) {
            validate(rightChild.left).parent = node;
        }

        rightChild.parent = node.parent;
        if (node.parent == null) root = rightChild;
        else if (node == ((NodeRBT<E>) node.parent).left)
            ((NodeRBT<E>) node.parent).left = rightChild;
        else ((NodeRBT<E>) node.parent).right = rightChild;
        rightChild.left = node;
        node.parent = rightChild;
    }
        if (node.parent == null) {
            root = rightChild;
        } else if (node == ((NodeRBT<E>) node.parent).left) {
            relink((NodeRBT<E>) node.parent, rightChild, true);
        } else {
            relink((NodeRBT<E>) node.parent, rightChild, false);
        }

        relink(rightChild, node, true);
    }


    public void rightTurn(NodeRBT<E> node) {
        if (node == null || node.left == null) return;

        NodeRBT<E> leftChild = (NodeRBT<E>) node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            validate(leftChild.right).parent = node;
        }

        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == ((NodeRBT<E>) node.parent).left) {
            relink((NodeRBT<E>) node.parent, leftChild, true);
        } else {
            if (nodeSwap.isRed) {
                nodeSwap.isRed = false;
            }
            relink((NodeRBT<E>) node.parent, leftChild, false);
        }

        relink(leftChild, node, false);
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
