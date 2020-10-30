package Leetcode.ByTags.HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 217. 存在重复元素
 * Easy
 */

public class _0217_containsDuplicate {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {

        // 一、利用外部空间实现
//        Set<Integer> set = new HashSet<>();
//        for (int num : nums) {
//            if (set.contains(num))
//                return true;
//            else
//                set.add(num);
//        }
//        return false;

        // 二、基于数组自身实现
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i+1])
                return true;
        }
        return false;
    }
}
