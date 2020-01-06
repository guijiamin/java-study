package com.study.datastructure.stack;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/15.
 *
 * @author guijiamin.
 */
public class SequenceStack<T> implements Stack<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int top = -1;

    private T[] data;

    private int length;

    public SequenceStack() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public SequenceStack(int capacity) {
        this.data = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int length() {
        return this.length;
    }

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (this.data.length == this.length) {
            //需要扩容
            T[] oldData = this.data;
            T[] newData = (T[]) new Object[this.length * 2];
            for (int i = 0; i < oldData.length; i++) {
                newData[i] = oldData[i];
            }
            this.data = newData;
        }
        this.length++;
        this.data[++this.top] = data;
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        this.length--;
        return this.data[this.top--];
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.data[this.top];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for(int i = 0; i < this.length; i++) {
            sb.append(this.data[i]);
            if (i != this.length-1) {
                sb.append("->");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        SequenceStack<Integer> sequenceStack = new SequenceStack<Integer>(3);
        sequenceStack.push(1);
        sequenceStack.push(2);
        sequenceStack.push(3);
        sequenceStack.push(4);
        System.out.println(sequenceStack);
        System.out.println(sequenceStack.top);
        System.out.println(sequenceStack.peek());
        System.out.println(sequenceStack.pop());
        System.out.println(sequenceStack.top+"--"+sequenceStack.length());
        System.out.println(sequenceStack);
    }
}
