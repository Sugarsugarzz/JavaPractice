package Leetcode.ByTags.DynamicProgramming;

/**
 * 64. 最小路径和
 * Medium
 */

public class _0064_minPathSum {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {

        // 一、动态规划
        int m = grid.length;
        int n = grid[0].length;
        if (m <= 0 || n <= 0)
            return 0;
        // 初始化
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i-1][0] + grid[i][0];
        for (int i = 1; i < n; i++)
            dp[0][i] = dp[0][i-1] + grid[0][i];
        // 根据关系计算每个位置的最小路径和
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];

        return dp[m-1][n-1];
    }
}
