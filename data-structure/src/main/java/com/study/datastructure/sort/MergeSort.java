package com.study.datastructure.sort;

import java.util.Arrays;

/**
 * Decription
 * <p>
 *     归并排序：O(nlogn)
 * </p>
 * DATE 18/12/28.
 *
 * @author guijiamin.
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(sort(left), sort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (j >= right.length) {
                result[index] = left[i++];
            } else if (i >= left.length) {
                result[index] = right[j++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
