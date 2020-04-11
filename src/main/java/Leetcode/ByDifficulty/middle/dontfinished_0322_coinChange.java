package Leetcode.ByDifficulty.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 *   *****不会做*****
 * title：零钱兑换
 * 
 * description： 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 
 * limit：你可以认为每种硬币的数量是无限的。
 * 
 * label：动态规划
 * 
 */

public class dontfinished_0322_coinChange {

	public static void main(String[] args) {
		// 测试
		int[] coins = { 1, 2, 5 };
		int amount = 11;
		int result = coinChange(coins, amount);
		System.out.println(result);
	}

	// 暴力法
	public static int coinChange(int[] coins, int amount) {
		
		if (amount == 0)
			return 0;
		int ans = Integer.MAX_VALUE;
		for (int coin : coins) {
			if (amount - coin < 0) continue;
			int subProb = coinChange(coins, amount - coin);
			if (subProb == -1) continue;
			ans = Math.min(ans, subProb + 1);
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
}
