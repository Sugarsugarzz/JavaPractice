package Leetcode.ByTags.DynamicProgramming;

/**
 * 746. 使用最小花费爬楼梯
 * Easy
 */

public class _0746_minCostClimbingStairs {

    public static void main(String[] args) {

        int[] cost = {1, 2, 3, 4};
        System.out.println(minCostClimbingStairs(cost));
    }

    public static int minCostClimbingStairs(int[] cost) {

        // 一、动态规划
        if (cost.length < 2)
            return 0;
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }

        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }
}
