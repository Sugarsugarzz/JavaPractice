package Leetcode.ByTags.Array;

/**
 * 221. 最大正方形
 * Medium
 */

public class _0221_maximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        };
        System.out.println(maximalSquare(matrix));
    }

    public static int maximalSquare(char[][] matrix) {

        // 一、暴力法
        // 将遇到的 1 作为正方形左上角，计算当前位置能得到的最大正方形边长，遍历正方形内部是否均为 1
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
//            return 0;
//
//        int maxSide = 0;
//        int rows = matrix.length, columns = matrix[0].length;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                if (matrix[i][j] == '0')
//                    continue;
//
//                maxSide = Math.max(1, maxSide);
//                // 计算当前位置能得到的最大边长
//                int currentMaxSide = Math.min(rows - i, columns - j);
//                for (int k = 1; k < currentMaxSide; k++) {
//
//                    boolean flag = true;
//                    if (matrix[i + k][j + k] == '0')
//                        break;
//
//                    for (int m = 0; m < k; m++) {
//                        if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if (flag) {
//                        maxSide = Math.max(maxSide, k+1);
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//
//        return maxSide * maxSide;

        // 二、动态规划
        // matrix[i][j] = 0, dp[i][j] = 0
        // matrix[i][j] = 1, dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        int maxSide = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}
