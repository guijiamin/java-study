package com.study.datastructure.list;

import com.study.datastructure.pojo.SLNode;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/10.
 *
 * @author guijiamin.
 */
public class SingleLinkedList<T> implements List<T> {
    private SLNode<T> head;

    public SingleLinkedList() {
    }

    public SingleLinkedList(SLNode<T> head) {
        this.head = head;
    }

    public SingleLinkedList(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("array cannot be null or empty");
        }
        this.head = new SLNode<T>(array[0]);
        SLNode<T> slNode = this.head;
        int length = array.length;
        for (int i = 1; i < length; i++) {
            slNode.setNext(new SLNode<T>(array[i]));
            slNode = slNode.getNext();
        }
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int length() {
        int count = 0;
        SLNode<T> slNode = this.head;
        while (slNode != null) {
            count++;
            slNode = slNode.getNext();
        }
        return count;
    }

    public void clear() {
        this.head = null;
    }

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        SLNode<T> slNode = this.head;//如果没有初始化起始引用会不会异常？不会
        while (slNode != null) {
            if (slNode.getData().equals(data)) {//使用equal是否可以？因为data必须是引用类型，引用类型实现了equals
                return true;
            }
            slNode = slNode.getNext();
        }
        return false;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index cannot be negtive");
        }

        SLNode<T> slNode = this.head;
        int i = 0;
        while (slNode != null) {
            if (i == index) {
                return slNode.getData();
            }
            i++;
            slNode = slNode.getNext();
        }

        return null;
    }

    public T set(int index, T data) {
        if (index < 0 || data == null) {
            throw new IllegalArgumentException("index cannot be negtive or data cannot be null");
        }
        SLNode<T> slNode = this.head;
        int i = 0;
        while (slNode != null) {
            if (i == index) {
                slNode.setData(data);
                return slNode.getData();
            }
            i++;
            slNode = slNode.getNext();
        }
        return null;
    }

    public boolean add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        SLNode<T> previous = this.head;
        SLNode<T> current = this.head.getNext();
        while (current != null) {
            previous = current;
            current = current.getNext();
        }
        previous.setNext(new SLNode<T>(data));
        return true;
    }

    /**
     * insertBefore和insertAfter也类似
     *
     * @param index
     * @param data
     * @return
     */
    public boolean insert(int index, T data) {
        if (index < 0 || data == null) {
            throw new IllegalArgumentException("index cannot be negtive or data cannot be null");
        }
        SLNode<T> previous = this.head;
        SLNode<T> current = this.head.getNext();
        //1.在头部插入；2.在中间插入；3.在尾部插入
        //要注意只有一个节点的情况
        //要注意插入的节点大于链表长度的情况->此时插入到链表尾部
        if (index == 0) {
            SLNode<T> slNode = new SLNode<T>(data);
            slNode.setNext(previous);
            this.head = slNode;
            return true;
        } else {
            int i = 1;
            while (current != null) {
                if (i == index) {
                    SLNode<T> slNode = new SLNode<T>(data);
                    previous.setNext(slNode);
                    slNode.setNext(current);
                    return true;
                }
                i++;
                previous = current;
                current = current.getNext();
            }

            SLNode<T> slNode = new SLNode<T>(data);
            previous.setNext(slNode);
            return true;
        }
    }

    public boolean remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index cannot be negtive");
        }
        if (this.head == null) {
            throw new NullPointerException("head is null");
        }
        SLNode<T> previous = this.head;
        SLNode<T> current = this.head.getNext();
        //1.移除头部；2.移除中间；3.移除尾部
        //注意一个节点情况
        //注意移除下标不存在的情况
        if (index == 0) {
            this.head = current;
        } else {
            int i = 1;
            while (current != null) {
                if (i == index) {
                    previous.setNext(current.getNext());
                    current.setNext(null);
                    return true;
                }
                i++;
                previous = current;
                current = current.getNext();
            }
        }
        return true;
    }

    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (this.head == null) {
            throw new NullPointerException("head is null");
        }
        SLNode<T> previous = this.head;
        SLNode<T> current = this.head.getNext();
        //1.删除的节点是第一个；2.删除中间的；3.删除尾部的
        //注意只有一个节点的情况
        //注意删除的不存在的情况
        if (previous.getData().equals(data)) {
            this.head = current;
            return true;
        } else {
            while (current != null) {
                if (current.getData().equals(data)) {
                    previous.setNext(current.getNext());
                    current.setNext(null);
                    return true;
                }
                previous = current;
                current = current.getNext();
            }
        }

        return false;
    }

    public boolean removeAll(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (this.head == null) {
            throw new NullPointerException("head is null");
        }
        SLNode<T> previous = this.head;
        SLNode<T> current = this.head.getNext();

        //首先解决只有一个节点的情况
        if (previous.getData().equals(data) && current == null) {
            this.head = null;
            return true;
        }

        while (current != null) {
            //删除第一个节点
            if (previous.getData().equals(data)) {
                this.head = current;
            }
            if (current.getData().equals(data)) {
                previous.setNext(current.getNext());
                current = current.getNext();
            }
            previous = current;
            current = current.getNext();
        }

        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        SLNode<T> slNode = this.head;
        while (slNode != null) {
            sb.append(slNode.getData());
            if (slNode.getNext() != null) {
                sb.append("->");
            }
            slNode = slNode.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] array1 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] array2 = new String[]{"a"};
        Integer[] array3 = new Integer[]{1, 2, 3, 4};
        SingleLinkedList<String> singleLinkedList = new SingleLinkedList<String>(array1);
//        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>(array1);
        System.out.println("单链表：" + singleLinkedList);
//        System.out.println(singleLinkedList.length() + "," + singleLinkedList.contains("i"));
//        System.out.println(singleLinkedList.get(2));
//        System.out.println(singleLinkedList.get(10));
//        System.out.println(singleLinkedList.set(3, "dd"));
//        System.out.println("单链表：" + singleLinkedList);
//        System.out.println(singleLinkedList.add("k"));
//        System.out.println("单链表：" + singleLinkedList);
//        System.out.println(singleLinkedList.insert(5, "e1"));
//        System.out.println("单链表：" + singleLinkedList);
//        System.out.println(singleLinkedList.remove(111));
//        System.out.println("单链表：" + singleLinkedList);
//        System.out.println(singleLinkedList.remove("a"));
//        System.out.println("单链表：" + singleLinkedList);
        singleLinkedList.insert(3, "a");
        singleLinkedList.insert(7, "a");
        System.out.println("单链表：" + singleLinkedList);
        singleLinkedList.removeAll("a");
        System.out.println("单链表：" + singleLinkedList);
    }
}
