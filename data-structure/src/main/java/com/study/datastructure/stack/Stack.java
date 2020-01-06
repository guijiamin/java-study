package com.study.datastructure.stack;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/15.
 *
 * @author guijiamin.
 */
public interface Stack<T> {
    boolean isEmpty();

    int length();

    void push(T data);

    T pop();

    T peek();
}
