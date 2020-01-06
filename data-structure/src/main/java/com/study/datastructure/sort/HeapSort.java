package com.study.datastructure.sort;

import com.study.datastructure.heap.Heap;

import java.util.Arrays;

/**
 * Decription
 * <p>
 *     堆排序
 * </p>
 * DATE 18/12/29.
 *
 * @author guijiamin.
 */
public class HeapSort {
    public static int[] sort(int[] array) {
        Heap heap = new Heap();
        heap.build(array);
        System.out.println(Arrays.toString(heap.array));
        int[] result = new int[array.length];

        for (int i = 1; i < array.length; i++) {
            result[array.length - i] = heap.array[i];
            heap.removeMax();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {0,2,1,8,7,6,5,9,15,13,16,21,17,33};
        System.out.println(Arrays.toString(sort(a)));
    }
}
