package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * Medium
 */
public class _0054_spiralOrder {
    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0)  return list;

        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;

        while(true) {
            for (int i = l; i <= r; i++)  list.add(matrix[t][i]);
            if (++t > b)  break;
            for (int i = t; i <= b; i++)  list.add(matrix[i][r]);
            if (l > --r)  break;
            for (int i = r; i >= l; i--)  list.add(matrix[b][i]);
            if (t > --b)  break;
            for (int i = b; i >= t; i--)  list.add(matrix[i][l]);
            if (++l > r)  break;
        }
        return list;
    }
}
