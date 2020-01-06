package com.study.datastructure.queue;

import com.study.datastructure.pojo.SLNode;

import java.util.NoSuchElementException;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/17.
 *
 * @author guijiamin.
 */
public class LinkedQueue<T> implements Queue<T> {

    /**
     * 指向头尾
     * 预定当front==null&&rear==null时，队列为空
     */
    public SLNode<T> front,rear;

    public int size;

    public static final int MAX_SIZE = 2;

    public LinkedQueue() {
        this.front = this.rear = null;
    }

    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    public int size() {
        return this.size;
    }

    /**
     * 插入队尾
     * @param data
     * @return
     */
    public boolean add(T data) {
        if (data == null) {
            throw  new IllegalArgumentException("data cannot be null");
        }

        SLNode<T> slNode = new SLNode<T>(data, null);
        if (this.front == null) {
            this.front = slNode;
        } else {
            this.rear.setNext(slNode);
        }
        this.rear = slNode;
        this.size++;
        return true;
    }

    /**
     * 插入队尾，队满插入失败
     * @param data
     * @return
     */
    public boolean offer(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        if (this.size > MAX_SIZE) {
            throw new IllegalArgumentException("queue is full");
        }

        return this.add(data);
    }

    /**
     * 出队不删除，队列为空返回null
     * @return
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.front.getData();
    }

    /**
     * 出队不删除，队列为空抛异常
     * @return
     */
    public T element() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return this.peek();
    }

    /**
     * 出队并删除，队列为空返回null
     * @return
     */
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        T data = this.front.getData();
        this.front = this.front.getNext();
        this.size--;
        return data;
    }

    /**
     * 出队并删除，队列为空抛异常
     * @return
     */
    public T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return this.poll();
    }

    /**
     * 清空队列
     */
    public void clear() {
        if (this.isEmpty()) return;
        this.front = this.rear = null;
        this.size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        SLNode<T> head = this.front;
        while (head != null) {
            sb.append(head.getData());
            sb.append(",");
            head = head.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<Integer>();
        System.out.println(linkedQueue.offer(0));
        System.out.println(linkedQueue.offer(1));
        System.out.println(linkedQueue.offer(2));
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size + "---" + linkedQueue.front.getData() + "---" + linkedQueue.rear.getData());
        System.out.println(linkedQueue.add(3));
        System.out.println(linkedQueue.add(4));
        System.out.println(linkedQueue.add(5));
        System.out.println(linkedQueue.add(6));
        System.out.println(linkedQueue.peek());
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size + "---" + linkedQueue.front.getData() + "---" + linkedQueue.rear.getData());
        System.out.println(linkedQueue.poll());
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size + "---" + linkedQueue.front.getData() + "---" + linkedQueue.rear.getData());

        linkedQueue.clear();
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size + "---" + linkedQueue.front + "---" + linkedQueue.rear);
    }
}
