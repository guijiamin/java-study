package com.study.datastructure.list;

import com.study.datastructure.pojo.DLNode;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/14.
 *
 * @author guijiamin.
 */
public class DoubleLinkedList<T> implements List<T> {
    private DLNode<T> head;

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    public DoubleLinkedList() {
        this.head = new DLNode<T>();
    }

    public DoubleLinkedList(T[] array) {
        if (array == null || array.length <= 0) {
            throw new IllegalArgumentException("array cannot be null");
        }
        this.head = new DLNode<T>(array[0]);
        DLNode<T> dlNode = this.head;
        int length = array.length;
        for (int i = 1; i < length; i++) {
            DLNode<T> currentDLNode = new DLNode<T>(array[i]);
            currentDLNode.setPrev(dlNode);
            dlNode.setNext(currentDLNode);
            dlNode = dlNode.getNext();
        }
    }

    @Override
    public int length() {
        int count = 0;
        DLNode<T> dlNode = this.head;
        while (dlNode != null) {
            count++;
            dlNode = dlNode.getNext();
        }
        return count;
    }

    @Override
    public void clear() {
        this.head = null;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        DLNode<T> dlNode = this.head;
        while (dlNode != null) {
            if (dlNode.getData().equals(data)) {
                return true;
            }
            dlNode = dlNode.getNext();
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index cannot be negtive");
        }
        DLNode<T> dlNode = this.head;
        int i = 0;
        while (dlNode != null) {
            if (i == index) {
                return dlNode.getData();
            }
            i++;
            dlNode = dlNode.getNext();
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || data == null) {
            throw new IllegalArgumentException("index cannot be negtive or data cannot be null");
        }
        DLNode<T> dlNode = this.head;
        int i = 0;
        while (dlNode != null) {
            if (index == i) {
                dlNode.setData(data);
                return dlNode.getData();
            }
            i++;
            dlNode = dlNode.getNext();
        }
        return null;
    }

    @Override
    public boolean add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        DLNode<T> previous = this.head;
        DLNode<T> current = this.head.getNext();

        if (current == null) {//只有一个节点
            DLNode<T> dlNode = new DLNode<T>(data);
            dlNode.setPrev(previous);
            previous.setNext(dlNode);
            return true;
        }

        while (current != null) {
            previous = current;
            current = current.getNext();
        }

        DLNode<T> dlNode = new DLNode<T>(data);
        dlNode.setPrev(previous);
        previous.setNext(dlNode);

        return true;
    }

    @Override
    public boolean insert(int index, T data) {
        if (index < 0 || data == null) {
            throw new IllegalArgumentException("index cannot be negtive or data cannot be null");
        }
        DLNode<T> previous = this.head;
        DLNode<T> current = this.head.getNext();

        if (index == 0) {
            if (current == null) {//当只有一个节点
                DLNode<T> dlNode = new DLNode<T>(data);
                dlNode.setNext(previous);
                previous.setPrev(dlNode);
                this.head = dlNode;
                return true;
            } else {
                DLNode<T> dlNode = new DLNode<T>(data);
                dlNode.setNext(previous);
                previous.setPrev(dlNode);
                this.head = dlNode;
                return true;
            }
        }
        int i = 1;
        while (current != null) {
            if (i == index) {
                DLNode<T> dlNode = new DLNode<T>(data);
                dlNode.setPrev(previous);
                dlNode.setNext(current);
                previous.setNext(dlNode);
                current.setPrev(dlNode);
                return true;
            }
            i++;
            previous = current;
            current = current.getNext();
        }
        if (index > i) {
            index = i;//如果插入大于当前链表长度，默认放到尾部
        }
        if (i == index) {//插入尾部
            DLNode<T> dlNode = new DLNode<T>(data);
            dlNode.setPrev(previous);
            previous.setNext(dlNode);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index cannot be negtive");
        }

        DLNode<T> previous = this.head;
        DLNode<T> current = this.head.getNext();
        if (current == null && index == 0) {//只有一个节点
            this.head = null;
            return false;
        }
        int i = 0;
        while (current != null) {
            if (index == 0) {
                this.head = current;
                return true;
            }
            if (i == index) {
                previous.setNext(current.getNext());
                current.getNext().setPrev(previous);
                current.setPrev(null);
                current.setNext(null);
                return true;
            }
            i++;
            previous = current;
            current = current.getNext();
        }
        if (i == index) {
            DLNode<T> dlNode = previous.getPrev();
            dlNode.setNext(null);
            previous.setPrev(null);
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        DLNode<T> previous = this.head;
        DLNode<T> current = this.head.getNext();

        if (previous.getData().equals(data)) {
            if (current == null) {
                this.head = null;
                return true;
            } else {
                current.setPrev(null);
                this.head = current;
                return true;
            }
        }

        while (current != null) {
            if (current.getData().equals(data)) {
                if (current.getNext() != null) {
                    previous.setNext(current.getNext());
                    DLNode<T> dlNode = current.getNext();
                    dlNode.setPrev(previous);
                    return true;
                } else {
                    previous.setNext(null);
                    return true;
                }
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean removeAll(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        DLNode<T> previous = this.head;
        DLNode<T> current = this.head.getNext();
        if (previous.getData().equals(data)) {
            if (current == null) {
                this.head = null;
                return true;
            } else {
                current.setPrev(null);
                this.head = current;
            }
        }

        while (current != null) {
            if (current.getData().equals(data)) {
                if (current.getNext() != null) {
                    previous.setNext(current.getNext());
                    current.getNext().setPrev(previous);
                } else {
                    previous.setNext(null);
                    return true;
                }
            }
            previous = current;
            current = current.getNext();
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        DLNode<T> dlNode = this.head;
        while (dlNode != null) {
            sb.append(dlNode.toString());
            if (dlNode.getNext() != null) {
                sb.append("->");
            }
            dlNode = dlNode.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c", "d"};
        DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<String>(array);
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.length());
        System.out.println(doubleLinkedList.contains("c"));
        System.out.println(doubleLinkedList.get(3));
        System.out.println(doubleLinkedList.set(3, "dd"));
        System.out.println(doubleLinkedList.add("e"));
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.insert(5, "f"));
        System.out.println(doubleLinkedList);
//        System.out.println(doubleLinkedList.remove(5));
//        System.out.println(doubleLinkedList);
//        System.out.println(doubleLinkedList.remove("f"));
//        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.insert(0, "f"));
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.insert(3, "f"));
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.removeAll("f"));
        System.out.println(doubleLinkedList);
    }
}
