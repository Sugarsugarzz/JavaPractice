package Leetcode.ByTags.Array;

public class _0279_numSquares {

    public static void main(String[] args) {

        int n = 10;
        System.out.println(numSquares(n));
    }

    public static int numSquares(int n) {
        // 一、动态规划w
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
