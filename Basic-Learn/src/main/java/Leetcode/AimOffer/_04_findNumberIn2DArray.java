package Leetcode.AimOffer;

/**
 *  04. 二维数组中的查找
 *  Medium
 */

public class _04_findNumberIn2DArray {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };
        int target = 5;
        System.out.println(findNumberIn2DArray(matrix, target));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {

        // 利用数组每行递增和每列递增的特点，访问到一个元素时，可以排除一些元素
        // 从右上角起始，小走左，大走下
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;

        int x = 0, y = matrix[0].length - 1;

        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                return true;
            }
        }
        return false;
    }
}
