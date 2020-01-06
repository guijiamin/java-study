package com.study.datastructure.sort;

/**
 * Decription
 * <p>
 *     冒泡排序：O(n^2)
 * </p>
 * DATE 18/12/27.
 *
 * @author guijiamin.
 */
public class BubbleSort {
    public static int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        if (array.length <= 1) {
            return array;
        }
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;//添加一个标志，如果没有数据交换，就退出，减少遍历次数，提高效率
        }
        return array;
    }
}
