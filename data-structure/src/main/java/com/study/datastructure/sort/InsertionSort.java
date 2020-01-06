package com.study.datastructure.sort;

import java.util.Arrays;

/**
 * Decription
 * <p>
 *     插入排序：O(n^2)
 * </p>
 * DATE 18/12/27.
 *
 * @author guijiamin.
 */
public class InsertionSort {
    public static int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        if (array.length <= 1) return array;

        for (int i = 0; i < array.length - 1; i++) {
            int current = array[i + 1];
            int preIndex = i;
            //该次遍历为查找要插入的位置
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sort(array)));
    }
}
