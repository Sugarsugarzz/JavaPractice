package Leetcode.ByTags.DynamicProgramming;

import java.util.Stack;

/**
 * 85. 最大矩形
 * Hard
 */

public class _0085_maximalRectangle {

    public static void main(String[] args) {

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {

        // 一、暴力法
        // 初始化：每个点代表在当前行的最长宽度
        // 遍历每个点，向上一行行扩展，高度不断加一，选当前列最小的数字作为矩阵的宽，计算面积
//        if (matrix.length ==0)  return 0;
//        // 保存以当前数字结尾的连续 1 的数量
//        int[][] width = new int[matrix.length][matrix[0].length];
//        int maxArea = 0;
//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < matrix[0].length; col++) {
//                // 更新width
//                if (matrix[row][col] == '1') {
//                    if (col == 0) {
//                        width[row][col] = 1;
//                    } else {
//                        width[row][col] = width[row][col - 1] + 1;
//                    }
//                } else {
//                    width[row][col] = 0;
//                    continue;
//                }
//                // 记录所有行中最小的数
//                int minWidth = width[row][col];
//                // 向上扩展行
//                for (int up_row = row; up_row >= 0; up_row--) {
//                    if (width[up_row][col] == '0')  continue;
//                    int height = row - up_row + 1;
//                    // 找最小的数作为矩阵的宽
//                    minWidth = Math.min(minWidth, width[up_row][col]);
//                    // 更新面积
//                    maxArea = Math.max(maxArea, height * minWidth);
//                }
//            }
//        }
//        return maxArea;

        // 二、利用84题
        // 如果计算每层的heights，就是84题的题目
        // 可分别作为参数，传入84题的函数
        if (matrix.length == 0)  return 0;
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            // 遍历每行，更新高度
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1') {
                    heights[col]++;
                } else {
                    heights[col] = 0;
                }
            }
            // 调用84题
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;

    }

    // 84题
    public static int largestRectangleArea(int[] heights) {
        // 三、单调栈+哨兵
        // 左右加一个高度为0的柱形，让栈元素能够一边遍历后全部出栈
        int len = heights.length;
        if (len == 0)  return 0;
        if (len == 1)  return heights[0];

        int max = 0;
        // 添加哨兵
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[0] = 0;
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peek()]) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                max = Math.max(max, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        return max;
    }

}
