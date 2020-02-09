package com.study.datastructure.list;

/**
 * Decription
 * <p>
 *     线性表：数组实现
 * </p>
 * DATE 18/12/10.
 *
 * @author guijiamin.
 */
public class SequenceList<T> implements List<T> {
    private Object[] data;
    private int length;
    private static final int DEFAULT_CAPACITY = 64;

    public SequenceList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be negtive");
        }
        this.data = new Object[capacity];
        this.length = 0;
    }

    public SequenceList() {
        this(DEFAULT_CAPACITY);
    }

    public SequenceList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        this.data = new Object[array.length];
        this.length = array.length;
        for (int i = 0; i < array.length; i++) {
            this.data[i] = array[i];
        }
    }

    public int indexOf(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        for (int i = 0; i < this.length; i++) {
            if (this.data[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public void clear() {
        this.data = null;
        this.length = 0;
    }

    @Override
    public boolean contains(T data) {
        return this.indexOf(data) > -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.length) {
            throw new IllegalArgumentException("index is out of bounds");
        }
        return (T) this.data[index];
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || index > this.length) {
            throw new IllegalArgumentException("index is out of bounds");
        }

        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        this.data[index] = data;
        return (T) this.data[index];
    }

    @Override
    public boolean add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        return this.insert(this.length, data);
    }

    @Override
    public boolean insert(int index, T data) {
        if (index < 0 || index > this.length) {
            throw new IllegalArgumentException("index is out of bounds");
        }

        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        //如果已经到最大容量了，需要扩容
        //默认扩容到原来长度的两倍
        if (this.data.length == this.length) {
            Object[] tmp = this.data;
            this.data = new Object[this.length * 2];
            for (int i = 0; i < this.length; i++) {
                this.data[i] = tmp[i];
            }
        }

        for (int i = this.length - 1; i >= index; i--) {
            this.data[i + 1] = this.data[i];
        }

        this.data[index] = data;
        this.length++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > this.length) {
            throw new IllegalArgumentException("index is out of bounds");
        }
        for (int i = index; i < this.length; i++) {
            this.data[i] = this.data[i + 1];
        }
        this.data[this.length - 1] = null;
        this.length--;
        return true;
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        return this.remove(this.indexOf(data));
    }

    @Override
    public boolean removeAll(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        for (int i = 0; i < this.length; i++) {
            if (this.data[i].equals(data)) {
                this.remove(i);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < this.length - 1; i++) {
            sb.append(this.data[i]);
            sb.append(",");
        }
        sb.append(this.data[this.length - 1]);
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        SequenceList<String> sequenceList = new SequenceList<String>(array);
        System.out.println("顺序表：" + sequenceList);
        System.out.println(sequenceList.isEmpty() + "," + sequenceList.length() + "," + sequenceList.contains("a"));
        System.out.println(sequenceList.get(8));
        sequenceList.set(9,"jj");
        sequenceList.add("k");
        sequenceList.insert(1, "start");
        System.out.println("顺序表：" + sequenceList);
        sequenceList.remove(1);
        System.out.println("顺序表：" + sequenceList);
        sequenceList.remove("jj");
        System.out.println("顺序表：" + sequenceList);
        sequenceList.add("a");
        sequenceList.removeAll("a");
        System.out.println("顺序表：" + sequenceList);
    }
}
