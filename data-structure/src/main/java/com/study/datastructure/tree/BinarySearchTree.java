package com.study.datastructure.tree;

import com.study.datastructure.pojo.BinaryNode;
import com.study.datastructure.queue.LinkedQueue;
import com.study.datastructure.queue.Queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Decription
 * <p>
 *     二叉查找树
 * </p>
 * DATE 18/12/18.
 *
 * @author guijiamin.
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(BinaryNode<T> root) {
        this.root = root;
    }

    public BinarySearchTree(T[] pList, T[] inList, boolean isPreOrder) {
        if (pList == null || inList == null) {
            throw new IllegalArgumentException("pList or inList cannot be null");
        }

        if (isPreOrder) {
            this.root = createBinarySearchTreeByPreIn(pList, inList, 0, pList.length - 1, 0, inList.length - 1);
        } else {
            this.root = createBinarySearchTreeByPostIn(pList, inList, 0, pList.length - 1, 0, inList.length - 1);
        }
    }

    private BinaryNode<T> createBinarySearchTreeByPreIn(T[] preList, T[] inList, int preStart, int preEnd, int inStart, int inEnd) {
        BinaryNode<T> p = new BinaryNode<T>(preList[preStart]);
        if (preStart == preEnd && inStart == inEnd) {
            return p;
        }
        int root = 0;
        for (root = inStart; root < inEnd; root++) {
            if (preList[preStart].compareTo(inList[root]) == 0) {
                break;
            }
        }
        int leftLength = root - inStart;
        int rightLength = inEnd - root;

        if (leftLength > 0) {
            p.setLeft(createBinarySearchTreeByPreIn(preList, inList, preStart + 1, preStart + leftLength, inStart, root - 1));
        }

        if (rightLength > 0) {
            p.setRight(createBinarySearchTreeByPreIn(preList, inList, preStart + leftLength + 1, preEnd, root + 1, inEnd));
        }
        return p;
    }

    private BinaryNode<T> createBinarySearchTreeByPostIn(T[] postList, T[] inList, int postStart, int postEnd, int inStart, int inEnd) {
        BinaryNode<T> p = new BinaryNode<T>(postList[postEnd]);
        if (postStart == postEnd && inStart == inEnd) {
            return p;
        }

        int root = 0;
        for (root = inStart; root < inEnd; root++) {
            if (postList[postEnd].compareTo(inList[root]) == 0) {
                break;
            }
        }

        int leftLength = root - inStart;
        int rightLength = inEnd - root;

        if (leftLength > 0) {
            p.setLeft(createBinarySearchTreeByPostIn(postList, inList, postStart, postStart + leftLength - 1, inStart, root - 1));
        }

        if (rightLength > 0) {
            p.setRight(createBinarySearchTreeByPostIn(postList, inList, postStart + leftLength, postEnd - 1, root + 1, inEnd));
        }
        return p;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return size(this.root);
    }

    private int size(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        } else {
            return size(p.getLeft()) + 1 + size(p.getRight());
        }
    }

    public int height() {
        return height(this.root);
    }

    private int height(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        } else {
            int l = height(p.getLeft());
            int r = height(p.getRight());
            return (l > r) ? l + 1 : r + 1;
        }
    }

    public void insert(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        this.root = insert(data, this.root);
    }

    private BinaryNode<T> insert(T data, BinaryNode<T> tree) {
        if (tree == null) {
            //终结条件是要插入的树为null
            return new BinaryNode<T>(data, null, null);
        }
        int compareResult = data.compareTo(tree.getData());
        if (compareResult < 0) {
            //插入左树
            tree.setLeft(insert(data, tree.getLeft()));
        } else if (compareResult > 0) {
            //插入右树
            tree.setRight(insert(data, tree.getRight()));
        } else {
            //相等不用插入
        }
        return tree;
    }

    public void remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        this.root = this.remove(data, this.root);
    }

    private BinaryNode<T> remove(T data, BinaryNode<T> tree) {
        if (tree == null) {
            return tree;
        }

        int compareResult = data.compareTo(tree);
        if (compareResult < 0) {
            tree.setLeft(remove(data, tree.getLeft()));
        } else if (compareResult > 0) {
            tree.setRight(remove(data, tree.getRight()));
        } else if (tree.getLeft() != null && tree.getRight() != null) {
            tree.setData(findMin(tree.getRight()).getData());
            tree.setRight(remove(tree.getData(), tree.getRight()));
        } else {
            tree = tree.getLeft() == null ? tree.getRight() : tree.getLeft();
        }
        return tree;
    }
    public boolean contains(T data) {
        return contains(data, this.root);
    }

    private boolean contains(T data, BinaryNode<T> tree) {
        if (data == null || tree == null) {
            return false;
        }

        int compareResult = data.compareTo(tree.getData());
        if (compareResult < 0) {
            return contains(data, tree.getLeft());
        } else if (compareResult > 0) {
            return contains(data, tree.getRight());
        } else {
            return true;
        }
    }

    public void clear() {
        this.root = null;
    }

    public T findMin() {
        return findMin(this.root).getData();
    }

    private BinaryNode<T> findMin(BinaryNode<T> tree) {
        if (tree == null) {
            return null;
        } else if (tree.getLeft() == null) {
            return tree;
        }
        return findMin(tree.getLeft());
    }

    public T findMax() {
        return findMax(this.root).getData();
    }

    private BinaryNode<T> findMax(BinaryNode<T> tree) {
        if (tree == null) {
            return null;
        } else if (tree.getRight() == null) {
            return tree;
        }
        return findMax(tree.getRight());
    }

    public String preOrder() {
        return preOrder(this.root);
    }

    private String preOrder(BinaryNode<T> p) {
        StringBuilder sb = new StringBuilder();
        if (p != null) {
            sb.append(p.getData());
            sb.append(preOrder(p.getLeft()));
            sb.append(preOrder(p.getRight()));
        }
        return sb.toString();
    }

    public String inOrder() {
        return inOrder(this.root);
    }

    private String inOrder(BinaryNode<T> p) {
        StringBuilder sb = new StringBuilder();
        if (p != null) {
            sb.append(inOrder(p.getLeft()));
            sb.append(p.getData());
            sb.append(inOrder(p.getRight()));
        }
        return sb.toString();
    }

    public String postOrder() {
        return postOrder(this.root);
    }

    private String postOrder(BinaryNode<T> p) {
        StringBuilder sb = new StringBuilder();
        if (p != null) {
            sb.append(postOrder(p.getLeft()));
            sb.append(postOrder(p.getRight()));
            sb.append(p.getData());
        }
        return sb.toString();
    }

    public String levelOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedQueue<BinaryNode<T>> linkedQueue = new LinkedQueue<BinaryNode<T>>();
        BinaryNode<T> p = this.root;

        while (p != null) {
            sb.append(p.getData());

            if (p.getLeft() != null) {
                linkedQueue.add(p.getLeft());
            }

            if (p.getRight() != null) {
                linkedQueue.add(p.getRight());
            }
            p = linkedQueue.poll();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] preList = {"A", "B", "D", "G", "C", "E", "F", "H"};
        String[] inList = {"D", "G", "B", "A", "E", "C", "H", "F"};
        String[] postList = {"G", "D", "B", "E", "H", "F", "C", "A"};
        BinarySearchTree<String> tree = new BinarySearchTree<String>(postList, inList, false);
        System.out.println(tree.postOrder());
    }
}
