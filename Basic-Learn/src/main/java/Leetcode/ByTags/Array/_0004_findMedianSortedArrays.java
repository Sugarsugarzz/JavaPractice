package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * Difficult
 */

public class _0004_findMedianSortedArrays {

    public static void main(String[] args) {

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 一、合并数组
//        int len = nums1.length + nums2.length;
//        if (len == 0)
//            return 0;
//        int[] nums = new int[len];
//        // 合并两个有序数组
//        int a = 0, b = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (a < nums1.length && b < nums2.length) {
//                if (nums1[a] < nums2[b]) {
//                    nums[i] = nums1[a++];
//                } else {
//                    nums[i] = nums2[b++];
//                }
//            } else if (a < nums1.length) {
//                nums[i] = nums1[a++];
//            } else {
//                nums[i] = nums2[b++];
//            }
//        }
//        // 直接找中位数
//        len = nums.length;
//        if (len % 2 == 0) {
//            return (nums[len / 2] + nums[len / 2 - 1]) / 2.0;
//        } else {
//            return nums[len / 2];
//        }

        // 二、不合并数组，直接找到第 len/2 个数
        // time complexity: O(m+n)
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        if (len == 0)
            return 0;
        int left = 0, right = 0, a = 0, b = 0;

        for (int i = 0; i < len / 2 + 1; i++) {
            left = right;
            if (a < m && (b >= n || nums1[a] < nums2[b])) {
                right = nums1[a++];
            } else {
                right = nums2[b++];
            }
        }

        if (len % 2 == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }

        // 三、二分查找
        // TODO
    }
}
