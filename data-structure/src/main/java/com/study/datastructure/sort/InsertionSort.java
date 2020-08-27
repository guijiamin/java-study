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

        if (array.length <= 1) {
            return array;
        }

        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                //当发现前面的数比当前数大时，把前面数往后移一位
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                }
            }
            //找到可以插入的位置，把tmp放到j，这里需要+1是j已经往前面跑了一位
            array[j+1] = tmp;
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sort(array)));
    }
}
