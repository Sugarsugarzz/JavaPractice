package Leetcode.AimOffer;

import java.util.Arrays;

/**
 * 29. 顺时针打印矩阵
 * Easy
 */
public class _29_spiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(Arrays.toString(spiralOrder(matrix)));
    }

    public static int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0)  return new int[0];

        int count = 0, l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        int[] res = new int[(r + 1) * (b + 1)];

        while(true) {
            for (int i = l; i <= r; i++)  res[count++] = matrix[t][i];
            if (++t > b)  break;
            for (int i = t; i <= b; i++)  res[count++] = matrix[i][r];
            if (l > --r)  break;
            for (int i = r; i >= l; i--)  res[count++] = matrix[b][i];
            if (t > --b)  break;
            for (int i = b; i >= t; i--)  res[count++] = matrix[i][l];
            if (++l > r)  break;
        }
        return res;
    }
}
