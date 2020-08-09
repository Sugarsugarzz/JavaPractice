package Leetcode.ByTags.DynamicProgramming;

/**
 * 121. 买卖股票的最佳时机
 * Easy
 */

public class _0121_maxProfit {

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {

        // 一、双指针（暴力法）
//        int max = 0;
//        for (int i = 0; i < prices.length; i++)
//            for (int j = i + 1; j < prices.length; j++) {
//                max = Math.max(prices[j] - prices[i], max);
//            }
//        return max;

        // 二、动态规划
        // 维护两个变量，买入价格 和 利润
        if (prices.length == 0)
            return 0;
        int buy = prices[0], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (buy >= prices[i])
                buy = prices[i];
            else if (prices[i] - buy > profit)
                profit = prices[i] - buy;
        }
        return profit;
    }
}
