package Leetcode.ByTags.DynamicProgramming;

/**
 * 70. 爬楼梯
 * Easy
 */

public class _0070_climbStairs {

    public static void main(String[] args) {

        System.out.println(climbStairs(10));
    }

    public static int climbStairs(int n) {

        // 最基本的动态规划
        int[] dp = new int[n + 1];
//        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;  // 设定 2 级 的为初始值更好理解些
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

        // 空间压缩（只存储 i-1 和 i-2 的值即可）
//        int a = 1, b = 1, t = 0;
//
//        for (int i = 2; i <= n; i++) {
//            t = b;
//            b = t + a;
//            a = t;
//        }
//        return  b;
    }
}
