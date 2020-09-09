package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.Stack;

/**
 * 32. 最长有效括号
 * Difficult
 */

public class _0042_trap {

    public static void main(String[] args) {

        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {

        // 一、按列求解
//        int sum = 0;
//        for (int i = 1; i < height.length - 1; i++) {
//            // 找出左边最高
//            int left_max = 0;
//            for (int j = i - 1; j >= 0; j--) {
//                left_max = Math.max(left_max, height[j]);
//            }
//            // 找出右边最高
//            int right_max = 0;
//            for (int j = i + 1; j < height.length - 1; j++) {
//                right_max = Math.max(right_max, height[j]);
//            }
//            // 找出最小一端
//            int min = Math.min(left_max, right_max);
//            // 只有最小一端高于当前列的高度才会有水，否则不会有水
//            if (min >= height[i]) {
//                sum += min - height[i];
//            }
//        }
//        return sum;

        // 二、动态规划
        // 利用空间换时间，提前算好左右两边的最高
        int[] left_max = new int[height.length];
        for (int i = 1; i < height.length - 1; i++) {
            left_max[i] = Math.max(left_max[i-1], height[i-1]);
        }
        
        int[] right_max = new int[height.length];
        for (int i = height.length - 2; i > 0; i--) {
            right_max[i] = Math.max(right_max[i+1], height[i+1]);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(right_max[i], left_max[i]);
            if (min >= height[i]) {
                sum += min - height[i];
            }
        }
        
        return sum;
    }
}

