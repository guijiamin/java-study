package com.study.datastructure.heap;

import java.util.Arrays;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/29.
 *
 * @author guijiamin.
 */
public class Heap {
    public int[] array;
    private int max;
    private int count;

    public Heap() {}

    public Heap(int capacity) {
        this.array = new int[capacity + 1];
        max = capacity;
        count = 0;
    }

    public void build(int[] a) {
        int length = a.length;
        for (int i = length / 2; i >= 1; i--) {
            while (true) {
                int maxIndex = i;
                int leftIndex = 2 * i;
                int rightIndex = 2 * i + 1;
                if (leftIndex < length && a[i] < a[leftIndex]) {
                    maxIndex = leftIndex;
                }
                if (rightIndex < length && a[maxIndex] < a[rightIndex]) {
                    maxIndex = rightIndex;
                }
                if (maxIndex == i) break;
                swap(a, i, maxIndex);
                i = maxIndex;
            }
        }
        array = a;
        max = array.length;
        count = array.length - 1;
    }

    public void insert(int data) {
        if (count >= max) return;

        array[++count] = data;
        int i = count;
        while (i / 2 > 0 && data > array[i / 2]) {
            int tmp = array[i / 2];
            array[i / 2] = data;
            array[i] = tmp;
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) return;

        array[1] = array[count];
        count--;
        heapify(array, count, 1);
    }

    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxIndex = i;
            if (2 * i <= n && a[i] < a[2 * i]) maxIndex = 2 * i;
            if (2 * i + 1 <= n && a[maxIndex] < a[2 * i + 1]) maxIndex = 2 * i + 1;
            if (maxIndex == i) break;
            swap(a, i, maxIndex);
            i = maxIndex;
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] a = {0,2,1,8,7,6,5,9,15,13,16,21,17,33};
        heap.build(a);
        System.out.println(Arrays.toString(heap.array));

//        heap.insert(30);
//        System.out.println(Arrays.toString(heap.array));
//        heap.removeMax();
//        System.out.println(Arrays.toString(heap.array));
    }
}
