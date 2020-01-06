package com.study.datastructure.queue;

import java.util.NoSuchElementException;

/**
 * Decription
 * <p>
 *     基于数组实现循环队列，队列总会有一个为空
 * </p>
 * DATE 18/12/16.
 *
 * @author guijiamin.
 */
public class SequeceQueue<T> implements Queue<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] elementData;

    private int front, rear;

    private int size;

    public SequeceQueue() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
        this.front = this.rear = 0;
    }

    public SequeceQueue(int capacity) {
        this.elementData = (T[]) new Object[capacity];
        this.front = this.rear = 0;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public int size() {
        return this.size;
    }

    /**
     * 添加成功返回true，可扩容
     *
     * @param data
     * @return
     */
    public boolean add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        //当头尾指针碰撞，说明容量满了
        //默认扩容到现在的两倍
        if (this.front == (this.rear + 1) % this.elementData.length) {
            T[] oldElementData = this.elementData;
            T[] newElementData = (T[]) new Object[this.elementData.length * 2 + 1];//多+1是给尾指针
            int j = 0;
            for (int i = this.front; i != this.rear; i = (i + 1) % this.elementData.length) {
                newElementData[j++] = oldElementData[i];
            }
            this.elementData = newElementData;
            this.front = 0;
            this.rear = j;
        }
        this.elementData[this.rear] = data;
        this.rear = (this.rear + 1) % this.elementData.length;
        this.size++;
        return true;
    }

    /**
     * 添加成功返回true，不可扩容，队满插入错误
     *
     * @param data
     * @return
     */
    public boolean offer(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (this.front == (this.rear + 1) % this.elementData.length) {
            return false;
        }

        this.elementData[this.rear] = data;
        this.rear = (this.rear + 1) % this.elementData.length;
        this.size++;

        return true;
    }

    /**
     * 返回队头元素，不执行删除操作，若队列为空，返回null
     *
     * @return
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }

        return this.elementData[this.front];
    }

    /**
     * 返回队头元素，不执行删除操作，若队列为空，抛出异常NoSuchElementException
     *
     * @return
     */
    public T element() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("data is null");
        }

        return this.elementData[this.front];
    }

    /**
     * 出队，执行删除操作，返回队头元素，若队列为空，返回null
     *
     * @return
     */
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        T data = this.elementData[this.front];
//        this.elementData[this.front] = null;
        this.front = (this.front + 1) % this.elementData.length;
        this.size--;
        return data;
    }

    /**
     * 出队，执行删除操作，若队列为空，抛出异常NoSuchElementException
     *
     * @return
     */
    public T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("data is null");
        }
        return poll();
    }

    public void clear() {
        if (this.isEmpty()) return;
        for (int i = this.front; i != this.rear; i = (i + 1) % this.elementData.length) {
            this.elementData[i] = null;
        }
        this.front = this.rear = 0;
        this.size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = this.front; i != this.rear; i = (i + 1) % this.elementData.length) {
            sb.append(this.elementData[i]);
            sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        SequeceQueue<Integer> sequeceQueue = new SequeceQueue<Integer>(5);
        System.out.println(sequeceQueue.offer(0));
        System.out.println(sequeceQueue.offer(1));
        System.out.println(sequeceQueue.offer(2));
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        System.out.println(sequeceQueue.add(3));
        System.out.println(sequeceQueue.add(4));
        System.out.println(sequeceQueue.add(5));
        System.out.println(sequeceQueue.add(6));
        System.out.println(sequeceQueue.peek());
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        System.out.println(sequeceQueue.poll());
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        System.out.println(sequeceQueue.add(7));
        System.out.println(sequeceQueue.add(8));
        System.out.println(sequeceQueue.add(9));
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        System.out.println(sequeceQueue.add(10));
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        System.out.println(sequeceQueue.poll());
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        System.out.println(sequeceQueue.add(11));
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
        sequeceQueue.clear();
        System.out.println(sequeceQueue);
        System.out.println(sequeceQueue.size + "---" + sequeceQueue.front + "---" + sequeceQueue.rear);
    }
}
