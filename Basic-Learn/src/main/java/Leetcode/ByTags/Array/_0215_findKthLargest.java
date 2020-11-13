package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 215. 数组中的第K个最大元素
 * Medium
 */

public class _0215_findKthLargest {
    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {

        // 一、库实现  O(nlogn)， top-k算法需要O(n)
        Arrays.sort(nums);
        int i = nums.length - k;
        return nums[i];

        // 二、快速排序


        // 三、优先队列


    }
}
