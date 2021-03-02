package Leetcode.ByTags.DynamicProgramming;

/**
 * 343. 整数拆分
 * Medium
 */
public class _0343_integerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(8));
    }

    public static int integerBreak(int n) {
        // 一、动态规划
        int[] dp = new int[n+1];

        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i-j, dp[i-j]));
            }
        }
        return dp[n];
    }

}
