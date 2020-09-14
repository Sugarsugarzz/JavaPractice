package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * Medium
 */
public class _0075_sortColors {

    public static void main(String[] args) {

        int[] nums = {2, 0, 1};
        System.out.println(Arrays.toString(nums));
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {

        // 一、计数法
//        int a = 0, b = 0, c = 0;
//        for (int num : nums) {
//            if (num == 0) a++;
//            else if (num == 1) b++;
//            else if (num == 2) c++;
//        }
//        int idx = 0;
//        while (a-- != 0) nums[idx++] = 0;
//        while (b-- != 0) nums[idx++] = 1;
//        while (c-- != 0) nums[idx++] = 2;


        // 二、双指针
        /*
        p0指向0的边沿，p2指向2的边沿
        三种情况：
            0：nums[p0] <-> nums[curr]，p0++，cur++
            1：curr++
            2：nums[p2] <-> nums[curr]，p2--
         */
        int p0 = 0, p2 = nums.length - 1, curr = 0;
        int temp;
        while (curr <= p2) {
            if (nums[curr] == 2) {
                temp = nums[p2];
                nums[p2] = nums[curr];
                nums[curr] = temp;
                p2--;
            } else if (nums[curr] == 0) {
                temp = nums[p0];
                nums[p0] = nums[curr];
                nums[curr] = temp;
                p0++;
                curr++;
            } else {
                curr++;
            }
        }





    }
}
