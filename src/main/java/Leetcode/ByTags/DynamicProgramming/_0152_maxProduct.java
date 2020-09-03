package Leetcode.ByTags.DynamicProgramming;

import java.util.Arrays;

/**
 * 152. 乘积最大子数组
 * Medium
 */
public class _0152_maxProduct {

    public static void main(String[] args) {

        int[] nums = {2, 3, -2, 4, -2};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {

        // 一、动态规划
        /*
        - nums[i] >= 0 && dpMax[i-1] > 0  ===> dpMax[i] = dpMax[i-1] * nums[i]
        - nums[i] >= 0 && dpMax[i-1] < 0  ===> dpMax[i] = nums[i]
        - nums[i] < 0
            - dpMin[i-1] < 0  ===> dpMax[i] = dpMin[i-1] * nums[i]
            - dpMin[i-1] >= 0 ===> dpMax[i] = nums[i]
         */
        if (nums.length == 0)
            return 0;

        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(dpMax[i-1] * nums[i], Math.max(dpMin[i-1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMax[i-1] * nums[i], Math.min(dpMin[i-1] * nums[i], nums[i]));
            max = Math.max(max, dpMax[i]);
        }

        return max;
    }
}
