package com.study.datastructure.search;

/**
 * Decription
 * <p>
 *     二分查找
 * </p>
 * DATE 18/12/29.
 *
 * @author guijiamin.
 */
public class BinarySearch {
    public static int search(int[] array, int value) {
        int lo = 0;
        int hi = array.length - 1;

        while (lo <= hi) {
//            int mid = lo + (hi - lo) / 2;//(lo+hi)/2当lo、hi很大时可能会溢出
            int mid = lo + ((hi - lo) >> 1);//计算机处理位运算要快很多
            if (value > array[mid]) {
                lo = mid + 1;
            } else if (value < array[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int searchRecursion(int[] array, int value) {
        return searchRecursionInternal(array, 0, array.length - 1, value);
    }

    private static int searchRecursionInternal(int[] array, int lo, int hi, int value) {
        if (lo > hi) return -1;

        int mid = lo + ((hi - lo) >> 1);
        if (value > array[mid]) {
            return searchRecursionInternal(array, mid + 1, hi, value);
        } else if (value < array[mid]) {
            return searchRecursionInternal(array, lo, mid - 1, value);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        System.out.println(search(array, 6));
        System.out.println(searchRecursion(array, 7));
    }
}
