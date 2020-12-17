package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 287. 寻找重复数
 * Medium
 */

public class _0287_findDuplicate {

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        System.out.println(findDuplicated(nums));
    }

    public static int findDuplicated(int[] nums) {

        // 一、排序后返回相邻重复的数（违反了不修改数组的规则）
//        Arrays.sort(nums);
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i-1] == nums[i])
//                return nums[i];
//        }

        // 二、二分法
        // 这道题用时间换空间，比较不合常理
        int left = 1, right = nums.length - 1;
        while (left < right) {

            int mid = (right - left) / 2 + left;

            int cnt = 0;
            for (int num : nums) {
                if (num <= mid)
                    cnt++;
            }

            if (cnt > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
