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
    private static int fixedPartition(int[] array, int lo, int hi) {
        //以lo的值为基准
        int key = array[lo];
        int i = lo;
        int j = hi;
        while (i != j) {
            //从右边开始
            //找到第一个比基准数小的数
            while (array[j] >= key && i < j) {
                j--;
            }
            //找到第一个比基准数大的数
            while (array[i] <= key && i < j) {
                i++;
            }
            //如果此时i和j还未碰撞，则交换i和j指向的数
            //使得大于基准数的位于右边，小于基准数的位于左边
            if (i < j) {
                int t = array[j];
                array[j] = array[i];
                array[i] = t;
            }
        }
        //当i和j碰撞了，将基准数放置在碰撞位置
        array[lo] = array[i];
        array[i] = key;
        return i;
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void fixedSort(int[] array, int lo, int hi) {
        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        if (array.length <= 1) return;

        if (hi < lo) return;
        //查找一个基准数，使得大于该基准的数位于该基准位置的右边，小于该基准的数位于该基准位置的左边
        //然后对左边和右边分别递归排序
        int privot = fixedPartition(array, lo, hi);
        fixedSort(array, lo, privot-1);
        fixedSort(array, privot+1, hi);
    }

    public static void main(String[] args) {
//        int[] array = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        int[] array = {9,8,7,6,5,4,3,2,1};
        fixedSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] arr, int lo, int hi) {
        if (arr == null) throw new IllegalArgumentException();
        if (arr.length <= 1) return;

        if (lo > hi) return;
        int privot = findPrivot(arr, lo, hi);
        quickSort(arr, 0, privot - 1);
        quickSort(arr, privot + 1, hi);
    }
    private static int findPrivot(int[] arr, int lo, int hi) {
        int key = arr[lo];
        int i = lo;
        int j = hi;
        while (i != j) {
            while (i < j && arr[j] > key) {
                j--;
            }

            while (i < j && arr[i] < key) {
                i++;
            }
            if (i < j) {
                //swap(arr, i, j)
            }
        }
        int tmp = arr[i];
        arr[i] = key;
        return i;
    }
}
