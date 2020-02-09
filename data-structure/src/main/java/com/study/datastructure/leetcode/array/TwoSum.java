package com.study.datastructure.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-09.
 *
 * @author guijiamin.
 */
public class TwoSum {
    /**
     * 1、找出两数之和的索引
     * 时间复杂度：无要求
     * 空间复杂度：无要求
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] solution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other)) {
                return new int[]{i, map.get(other)};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
