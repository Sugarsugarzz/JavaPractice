package Leetcode.ByTags.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * Medium
 */

public class _0560_subarraySum {

    public static void main(String[] args) {
        int[] nums = {1, 1, -1, 1, 2};
        int k = 2;
        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {

        // 一、暴力法
        // 如果直接确定start和end，再遍历之间的部分，需要 O(n^3)
        // 但在[start, end]基础上，不断加上一个，就可以 O(n^2) 搞定
//        int count = 0;
//        for (int left = 0; left < nums.length; left++) {
//            int sum = 0;
//            for (int right = left; right < nums.length; right++) {
//                sum += nums[right];
//                if (sum == k) {
//                    count++;
//                }
//            }
//        }
//        return count;

        // 二、前缀和 + 哈希表
        // pre数组统计[0, i]之和，这里只用一个变量表示即可
        int count = 0, pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);  // 当前位置的前缀和减去之前某一位的前缀和等于目标值，即存在不同子串和为目标值的情况
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
