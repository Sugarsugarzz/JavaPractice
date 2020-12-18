package Leetcode.ByTags.Array;

import java.util.*;

/**
 * 448. 找到所有数组中消失的数字
 * Easy
 */

public class _0448_findDisappearedNumbers {

    public static void main(String[] args) {

        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers(nums));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {

        // 一、利用额外空间
//        Set<Integer> set = new HashSet<>();
//        for (int num : nums) {
//            set.add(num);
//        }
//
//        List<Integer> ans = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (!set.contains(i+1)) {
//                ans.add(i+1);
//            }
//        }
//        return ans;

        // 二、不利用额外空间
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            int index = num - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i+1);
            }
        }
        return ans;
    }
}
