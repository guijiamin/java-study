package com.study.datastructure.pojo;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/14.
 *
 * @author guijiamin.
 */
public class DLNode<T> extends Node<T> {
    private DLNode<T> prev;
    private DLNode<T> next;

    public DLNode() {
    }

    public DLNode(T data) {
        super(data);
    }

    public DLNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DLNode<T> prev) {
        this.prev = prev;
    }

    public DLNode<T> getNext() {
        return next;
    }

    public void setNext(DLNode<T> next) {
        this.next = next;
    }

    public String toString() {
        String p = "null";
        String d = "null";
        String n = "null";
        if (this.getPrev() != null && this.getPrev().getData() != null) {
            p = this.getPrev().getData().toString();
        }
        if (this.getData() != null) {
            d = this.getData().toString();
        }
        if (this.getNext() != null && this.getNext().getData() != null) {
            n = this.getNext().getData().toString();
        }
        return "[" + p + "," + d + "," + n + "]";
    }
}
