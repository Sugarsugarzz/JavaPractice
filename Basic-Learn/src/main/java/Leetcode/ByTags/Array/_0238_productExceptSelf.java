package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * Medium
 */

public class _0238_productExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] ans = productExceptSelf(nums);
        System.out.println(Arrays.toString(ans));
    }

    public static int[] productExceptSelf(int[] nums) {

        // 一、暴力法（超时）
//        int[] ans = new int[nums.length];
//
//        for (int i = 0; i < nums.length; i++) {
//            int result = 1;
//            for (int j = 0; j < nums.length; j++) {
//                if (j == i)  continue;
//                result *= nums[j];
//            }
//            ans[i] = result;
//        }
//
//        return ans;

        // 二、（if）先计算所有数的乘积，然后用除法就能得到每个位置的结果，但是如果有 0 就失效了，且题目禁止用除法
        // 利用两个数组，得到左右两边的乘积 L R
//        int len = nums.length;
//
//        int[] L = new int[len];
//        int[] R = new int[len];
//        int[] ans = new int[len];
//
//        L[0] = 1;
//        for (int i = 1; i < len; i++) {
//            L[i] = L[i-1] * nums[i-1];
//        }
//        R[len - 1] = 1;
//        for (int i = len - 2; i >= 0; i--) {
//            R[i] = R[i+1] * nums[i+1];
//        }
//
//        for (int i = 0; i < len; i++) {
//            ans[i] = L[i] * R[i];
//        }
//        return ans;

        // 三、空间复杂度 O(1)
        // 将输出数组直接作为 L，节省 R 的空间
        int len = nums.length;
        int[] ans = new int[len];

        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i-1] * nums[i-1];
        }

        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }

        return ans;
    }
}
