package com.study.datastructure.pojo;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/10.
 *
 * @author guijiamin.
 */
public class Node<T> {
    private T data;

    public Node() {}

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
