package com.study.datastructure.leetcode.array;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-09.
 *
 * @author guijiamin.
 */
public class Remove {
    /**
     * 26、移除排序数组中重复元素
     * 时间复杂度：无要求
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    private static int RemoveDuplicatesForSortedArray(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[idx] != nums[i]) {
                idx++;
                nums[idx] = nums[i];
            }
        }
        return idx + 1;
    }

    /**
     * 27、移除数组中指定元素
     * 时间复杂度：无要求
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param val
     * @return
     */
    private static int RemoveElements(int[] nums, int val) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[idx] = nums[i];
                idx++;
            }
        }
        return idx + 1;
    }
}
