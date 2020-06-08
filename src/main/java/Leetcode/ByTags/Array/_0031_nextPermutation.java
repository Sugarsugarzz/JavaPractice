package Leetcode.ByTags.Array;

import sun.applet.Main;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * Medium
 */

public class _0031_nextPermutation {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 8, 5, 4};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {

        int n = nums.length - 1;
        for (int i = n - 1; i >= 0 ; i--) {
            if (nums[i] < nums[i+1]) {
                int j = n;
                while (nums[j] <= nums[i]) j--;
                // 交换i和j
                swap(nums, i, j);
                // 将i后的升序排列
                reverse(nums, i+1, n);
                return;
            }
        }
        // 全数组为降序
        reverse(nums, 0, n);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end)
            swap(nums, start++, end--);
    }
}
