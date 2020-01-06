package com.study.datastructure.stack;

import com.study.datastructure.pojo.SLNode;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/16.
 *
 * @author guijiamin.
 */
public class LinkedStack<T> implements Stack<T> {

    private SLNode<T> top;

    private int length;

    public LinkedStack() {
        this.top = new SLNode<T>();
    }

    public boolean isEmpty() {
        return this.top == null || this.top.getData() == null;
    }

    public int length() {
        return this.length;
    }

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (this.top == null) {
            this.top = new SLNode<T>(data);
        } else if (this.top.getData() == null) {
            this.top.setData(data);
        } else {
            SLNode<T> slNode = new SLNode<T>(data);
            SLNode<T> currentTop = this.top;
            slNode.setNext(currentTop);

            this.top = slNode;
        }
        this.length++;
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        SLNode<T> currentTop = this.top;
        this.top = currentTop.getNext();
        this.length--;
        return currentTop.getData();
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.top.getData();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        SLNode<T> currentTop = this.top;
        while (currentTop != null) {
            sb.append(currentTop.getData());
            if (currentTop.getNext() != null) {
                sb.append("->");
            }
            currentTop = currentTop.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<Integer>();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        System.out.println(linkedStack);
        System.out.println(linkedStack.top.getData());
        System.out.println(linkedStack.length());
        System.out.println(linkedStack.peek());
        System.out.println(linkedStack);
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack);
    }
}
