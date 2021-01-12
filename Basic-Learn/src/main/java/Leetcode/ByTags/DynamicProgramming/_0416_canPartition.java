package Leetcode.ByTags.DynamicProgramming;

import java.util.Arrays;

/**
 * 416. 分割等和子集
 * Medium
 */

public class _0416_canPartition {
    public static void main(String[] args) {
        int[] nums = {1, 5, 5, 11};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        // 一、动态规划
//        int len = nums.length;
//        int sum = Arrays.stream(nums).sum();
//        // 边界问题，奇数无法处理
//        if ((sum & 1) == 1 || len < 2)  return false;
//        int target = sum >> 1;
//
//        boolean[][] dp = new boolean[len][target + 1];
//
//        if (target > nums[0])  dp[0][nums[0]] = true;
//
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j <= target; j++) {
//                dp[i][j] = dp[i-1][j];
//
//                if (nums[i] == j) {   // 等价于初始化 dp[0][0] = true
//                    dp[i][j] = true;
//                    continue;
//                }
//
//                if (nums[i] < j) {
//                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
//                }
//            }
//        }
//
//        return dp[len-1][target];

        // 二、动态规划（初始值优化）
//        int len = nums.length;
//        int sum = Arrays.stream(nums).sum();
//        if ((sum & 1) == 1 || len < 2)  return false;
//        int target = sum >> 1;
//
//        boolean[][] dp = new boolean[len][target + 1];
//
//        dp[0][0] = true;
//
//        if (target >= nums[0])  dp[0][nums[0]] = true;
//
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j <= target; j++) {
//                dp[i][j] = dp[i-1][j];
//                if (j >= nums[i]) {
//                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
//                }
//            }
//            if (dp[i][target])  // 剪枝，dp[i][target]为true，dp[len-1][target]必为true
//                return true;
//        }
//
//        return dp[len-1][target];

        // 三、动态规划（空间优化）
        int len = nums.length;
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) == 1 || len < 2)  return false;
        int target = sum >> 1;

        boolean[] dp = new boolean[target + 1];

        dp[0] = true;
        if (target >= nums[0])  dp[nums[0]] = true;

        for (int i = 1; i < len; i++) {
            for (int j = target; j >= nums[i]; j--) {
                if (dp[target])  return true;

                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
