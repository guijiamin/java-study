package com.study.datastructure.queue;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/16.
 *
 * @author guijiamin.
 */
public interface Queue<T> {
    boolean isEmpty();

    int size();

    boolean add(T data);

    boolean offer(T data);

    T peek();

    T element();

    T poll();

    T remove();

    void clear();
}
