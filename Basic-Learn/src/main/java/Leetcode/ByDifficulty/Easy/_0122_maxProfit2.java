package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title: 买卖股票的最佳时机 II
 * 
 * description：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 		设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 
 * limit: 注意你不能在买入股票前卖出股票。
 * 
 * label：数组、动态规划
 * 
 */

public class _0122_maxProfit2 {

	public static void main(String[] args) {
		// 测试
		int[] prices = {7,1,5,3,6,4};
		int result = maxProfit(prices);
		System.out.println(result);
	}

	// 贪心算法
	public static int maxProfit(int[] prices) {
		// “等价于每天都买卖”，这种理解好，把可能跨越多天的买卖都化解成相邻两天的买卖
		int profile = 0;
		for (int i=1; i<prices.length; i++) {
			int tmp = prices[i] - prices[i-1];
			if (tmp > 0)
				profile += tmp;
		}
		return profile;
	}
}
