package com.study.datastructure.pojo;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/18.
 *
 * @author guijiamin.
 */
public class BinaryNode<T extends Comparable> {
    private T data;

    private BinaryNode<T> left;

    private BinaryNode<T> right;

    public BinaryNode() {}

    public BinaryNode(T data) {
        this(data, null, null);
    }

    public BinaryNode(T data, BinaryNode left,  BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }
}
