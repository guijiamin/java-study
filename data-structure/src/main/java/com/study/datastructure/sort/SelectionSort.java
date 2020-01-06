package com.study.datastructure.sort;

import java.util.Arrays;

/**
 * Decription
 * <p>
 *     选择排序：O(n^2)
 * </p>
 * DATE 18/12/27.
 *
 * @author guijiamin.
 */
public class SelectionSort {
    public static int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        if (array.length <= 1) {
            return array;
        }
        //遍历找到最小的元素的下标，然后交换
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = minIndex; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            int tmp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = tmp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sort(array)));
    }

}
