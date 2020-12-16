package Leetcode.ByTags.Array;

/**
 * 240. 搜索二维矩阵 II
 * Medium
 */

public class _0240_searchMatrix {

    public static void main(String[] args) {

        int[][] matrix = {
                {5},
                {6}
        };
        int target = 6;
        System.out.println(searchMatrix(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        // 一、二分法
//        if (matrix.length == 0 || matrix[0].length == 0)  return false;
//
//        for (int[] array : matrix) {
//            if (array[array.length - 1] >= target) {
//                if (searchArray(array, target)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;

        // 二、找规律
        // 从左下角开始，大于target则上移，小于target则下移
        if (matrix.length == 0 || matrix[0].length == 0)  return false;

        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean searchArray(int[] array, int target) {

        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
