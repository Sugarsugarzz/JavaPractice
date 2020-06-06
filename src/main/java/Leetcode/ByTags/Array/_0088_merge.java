package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * Easy
 */

public class _0088_merge {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int p = nums1.length - 1;
        int p1 = m-1, p2 = n-1;

        while (p1>=0 && p2>=0)
            nums1[p--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--];

        System.arraycopy(nums2, 0, nums1, 0, p2+1);
    }
}
