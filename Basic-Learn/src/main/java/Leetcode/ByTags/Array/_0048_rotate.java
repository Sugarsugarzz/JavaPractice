package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 48. 旋转图像
 * Medium
 */

public class _0048_rotate {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        /*
        顺时针90°       逆时针90°
        1  2  3         1  2  3
        4  5  6         4  5  6
        7  8  9         7  8  9
           ⏬              ⏬
        1  4  7         3  2  1
        2  5  8         6  5  4
        3  6  9         9  8  7
           ⏬              ⏬
        7  4  1         3  6  0
        8  5  2         2  5  8
        9  6  3         1  4  7
         */

        rotate(matrix);

        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void rotate(int[][] matrix) {

        int n = matrix.length;
        // 一、转置+逐行翻转
        // 如果是逆时针旋转90°，则先逐行翻转再转置

        // 矩阵转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 矩阵按行翻转
        // j 到一半即可，否则会再次反转回来
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }


    }
}
