package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 53. 最大子序列
 * Easy
 */

public class _0053_maxSubArray {

    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = maxSubArray(nums);
        System.out.println(result);
    }

    public static int maxSubArray(int[] nums) {

        // 动态规划
        for (int i=1; i<nums.length; i++)
            if (nums[i-1] > 0)
                nums[i] += nums[i-1];

        return Arrays.stream(nums).max().getAsInt();
    }
}
