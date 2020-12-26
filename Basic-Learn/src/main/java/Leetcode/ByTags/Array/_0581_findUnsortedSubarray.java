package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 581. 最短无序连续子数组
 * Medium
 */

public class _0581_findUnsortedSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 1};
        System.out.println(findUnsortedSubarray(nums));
    }

    public static int findUnsortedSubarray(int[] nums) {

        int max = 0, min = Integer.MAX_VALUE, right = 0, left = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                min = Math.min(min, nums[i]);
                right = i;
            }
        }
        System.out.println(min);


        for (int i = 0; i < nums.length; i++) {
            if (min != Integer.MAX_VALUE && min > nums[i]) {
                left = i;
            }
        }
        System.out.println(left + " - " + right);
        return right - left;
    }
}
