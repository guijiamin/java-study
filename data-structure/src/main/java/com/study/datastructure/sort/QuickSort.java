package com.study.datastructure.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Decription
 * <p>
 *     快速排序：O(nlogn)
 * </p>
 * DATE 18/12/27.
 *
 * @author guijiamin.
 */
public class QuickSort {

    public static void QuickSort(int[] arr, int l, int r) {
        if(l >= r) {
            return;
        }
        int i = l, j = r;
        //以第一个为基准数
        int privot = arr[i];
        int tmp;
        while(i < j) {
            while(arr[j] >= privot && i < j) {
                j--;
            }
            while(arr[i] <= privot && i < j) {
                i++;
            }
            if (i < j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        //找到一个节点，使其左边的数小于它，右边的数大于它
        arr[l] = arr[i];
        arr[i] = privot;
        QuickSort(arr, l, i - 1);
        QuickSort(arr, i + 1, r);
    }

    public static void main(String[] args) {
//        int[] array = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        int[] array = {9,8,7,6,5,4,3,2,1};
        QuickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
