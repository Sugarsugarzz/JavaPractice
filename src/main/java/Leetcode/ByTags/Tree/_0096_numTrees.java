package Leetcode.ByTags.Tree;

import java.util.PrimitiveIterator;

/**
 * 96. 不同的二叉搜索树
 * Medium
 */

public class _0096_numTrees {

    public static void main(String[] args) {

        int n = 3;
        int res = numTrees(n);
        System.out.println(res);
    }

    public static int numTrees(int n) {

        // 动态规划（卡特兰数）
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i - j];
            }

        return dp[n];
    }
}
