package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title: 爬楼梯
 * 
 * description：假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 				每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * limit:给定 n 是一个正整数。
 * 
 * label：
 * 
 */

public class _0070_climbStairs {

	public static void main(String[] args) {
		// 测试
		int n = 8;
		int result = climbStairs_method3(n);
		System.out.println(result);
	}

	// 递归
	// 超时
	public static int climbStairs_method1(int n) {
		
		if (n < 1)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		
		return climbStairs_method1(n-1) + climbStairs_method1(n-2);
	}
	
	
	// 动态规划法
	// 第 i 阶可以由以下两种方法得到：1、在第 (i-1) 阶后向上爬一阶。 2、在第 (i−2) 阶后向上爬 22 阶。
	// 所以到达第 ii 阶的方法总数就是到第 (i-1) 阶和第 (i-2) 阶的方法数之和。
	//   dp[i]=dp[i−1]+dp[i−2]
	public static int climbStairs_method2(int n) {
		
		if (n < 1)
			return 0;
		if (n == 1)
			return 1;
		
		int[] dp = new int[n+1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i=3; i<dp.length; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}

		return dp[n];
	}
	
	// 斐波那契数
	// n就是第 n个斐波那契数。
	public static int climbStairs_method3(int n) {
		if (n < 1)
			return 0;
		if (n == 1)
			return 1;
		
		int first = 1;
		int second = 2;
		for (int i=3; i<=n; i++) {
			int third = first + second;
			first = second;
			second = third;
		}
		return second;
	}
	

}