package com.study.datastructure.pojo;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/13.
 *
 * @author guijiamin.
 */
public class SLNode<T> extends Node<T> {
    private SLNode<T> next;

    public SLNode() {}

    public SLNode(T data) {
        super(data);
    }

    public SLNode(T data, SLNode<T> next) {
        super(data);
        this.next = next;
    }

    public SLNode<T> getNext() {
        return next;
    }

    public void setNext(SLNode<T> next) {
        this.next = next;
    }
}
