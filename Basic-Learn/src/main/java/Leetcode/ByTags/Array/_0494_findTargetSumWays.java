package Leetcode.ByTags.Array;

/**
 * 494. 目标和
 * Medium
 */

public class _0494_findTargetSumWays {

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(findTargetSumWays(nums, S));
    }

    public static int count = 0;

    public static int findTargetSumWays(int[] nums, int S) {

        // 一、递归
        // 枚举出所有可能的情况 time O(2^N) space O(N)
        if (nums.length == 0)  return 0;

        calculate(nums, 0, 0, S);
        return count;

        // 二、动态规划
        // dp[i][j] 定义为从数据nums中 0~i 的元素进行加减得到 j 的方法个数
        // dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]

    }

    public static void calculate(int[] nums, int index, int sum, int S) {

        if (index == nums.length && sum == S) count++;
        if (index >= nums.length)  return;

        calculate(nums, index+1, nums[index] + sum, S);
        calculate(nums, index+1, -nums[index] + sum, S);
    }
}
