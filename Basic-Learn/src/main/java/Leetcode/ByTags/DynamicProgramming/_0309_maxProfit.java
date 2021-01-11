package Leetcode.ByTags.DynamicProgramming;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * Medium
 */

public class _0309_maxProfit {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {

        // 一、动态规划
        // dp[i][j] j：0 - 不持股，非卖出导致；1 - 持股；2 - 不持股，卖出导致
        // 可转移的状态：
        //      0 - （0 -> 0） or （0 -> 1）
        //      1 - （1 -> 1） or （1 -> 2）
        //      2 - （2 -> 0）
        // 第i的状态：
        //      0 - （0 -> 0）and （2 -> 0）     dp[i][0] = max(dp[i-1][0], dp[i-1][2])
        //      1 - （0 -> 1）and （1 -> 1）     dp[i][1] = max(dp[i-1][0] - prices[i], dp[i-1][1])
        //      2 - （1 -> 2）                  dp[i][2] = dp[i-1][1] + prices[i]

//        if (prices.length == 0)  return 0;
//        int[][] dp = new int[prices.length][3];
//        dp[0][1] = -prices[0];
//        for (int i = 1; i < prices.length; i++) {
//            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]);
//            dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
//            dp[i][2] = dp[i-1][1] + prices[i];
//        }
//        return Math.max(dp[prices.length-1][0], dp[prices.length-1][2]);

        // 二、动态规划（空间优化）
        if (prices.length == 0)  return 0;
        int[] dp = new int[3];
        dp[1] = -prices[0];
        int preZero = dp[0];
        int preOne = dp[1];

        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(preZero, dp[2]);
            dp[1] = Math.max(preZero - prices[i], preOne);
            dp[2] = preOne + prices[i];

            preZero = dp[0];
            preOne = dp[1];
        }
        return Math.max(dp[0], dp[2]);

    }
}
