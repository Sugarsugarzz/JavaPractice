package Leetcode.ByTags.DynamicProgramming;

import com.sun.xml.internal.rngom.digested.DPattern;

import java.util.Arrays;
import java.util.Collections;

/**
 * 198. 打家劫舍
 * Easy
 */

public class _0198_rob {

    public static void main(String[] args) {

        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {

        // 一、动态规划（自己写）
//        if (nums.length < 1)
//            return 0;
//        if (nums.length < 3) {
//            Arrays.sort(nums);
//            return nums[nums.length - 1];
//        }
//
//        int[] dp = new int[nums.length + 1];
//        dp[0] = 0;
//        dp[1] = nums[0];
//        dp[2] = nums[1];
//        for (int i = 3; i <= nums.length; i++) {
//            dp[i] = Math.max(dp[i-2], dp[i-3]) + nums[i-1];
//        }
//        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);

        // 二、动态规划（参考，没必要将奇偶数割裂开讨论）
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[nums.length];
    }
}
