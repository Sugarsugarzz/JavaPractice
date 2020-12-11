package Leetcode.ByTags.Array;

/**
 * 74. 搜索二维矩阵
 * Medium
 */

public class _0074_searchMatrix {

    public static void main(String[] args) {
//
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
//        int[][] matrix = {{1}, {3}};
        int target = 30;
        System.out.println(searchMatrix(matrix, target));

    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0)  return false;

        int target_i = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][matrix[i].length - 1] >= target) {
                target_i = i;
                break;
            }
        }

        int left = 0, right = matrix[target_i].length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (matrix[target_i][mid] < target) {
                left = mid + 1;
            } else if (matrix[target_i][mid] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
