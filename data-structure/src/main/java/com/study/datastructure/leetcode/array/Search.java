package com.study.datastructure.leetcode.array;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-09.
 *
 * @author guijiamin.
 */
public class Search {
    /**
     * 35、搜索插入位置
     * 时间复杂度：无要求
     * 空间复杂度：无要求
     *
     * @param nums
     * @param target
     * @return
     */
    private static int searchInsertForSortedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            //left+(right-left)是为了防止left+right的和太大溢出
            //>>>1无符号右移比除以2效率更快
            int mid = (left + (right - left)) >>> 1;
            if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid - 1;
            }
        }
        return left;
    }

    /**
     * 53、查找最大和的子序列
     * 时间复杂度：无要求
     * 空间复杂度：无要求
     * TODO
     * @param nums
     * @return
     */
    private static int searchSubSequenceForMaxSum(int[] nums) {

        return 0;
    }
}
