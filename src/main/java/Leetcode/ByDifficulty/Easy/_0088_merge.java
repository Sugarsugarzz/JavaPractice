package Leetcode.ByDifficulty.Easy;

import java.util.Arrays;

/**
 * 
 * title: 合并两个有序数组
 * 
 * description：给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 
 * limit:初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
		你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 
 * label：数组
 * 
 */

public class _0088_merge {
	
	public static void main(String[] args) {
		// 测试
		int m = 3;
		int[] nums1 = {2, 4, 6, 0, 0, 0};
		int n = 3;
		int[] nums2 = {1, 3, 5};
		merge_3(nums1, m, nums2, n);
		for (int i : nums1)
			System.out.println(i);
	}
	
	// 合并数组并排序，时间复杂度高  O((n+m)log(n+m))
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		
		System.arraycopy(nums2, 0, nums1, m, n);
		Arrays.sort(nums1);
	}
	
	// 双指针法，从前往后  O(n+m)
	public static void merge_2(int[] nums1, int m, int[] nums2, int n) {
		int[] nums1_copy = new int[m];
		
		System.arraycopy(nums1, 0, nums1_copy, 0, m);
		
		int p1 = 0;
		int p2 = 0;
		int p = 0;
		
		while (p1 < m && p2 < n) 
			nums1[p++] = nums1_copy[p1] > nums2[p2] ? nums2[p2++] : nums1_copy[p1++];
		
		if (p1 < m)
			System.arraycopy(nums1_copy, p1, nums1, p1+p2, m-p1);
		if (p2 < n)
			System.arraycopy(nums2, p2, nums1, p1+p2, n-p2);
	}
	
	// 双指针法，从后往前
	public static void merge_3(int[] nums1, int m, int[] nums2, int n) {
		
		int p = n + m - 1;
		int p1 = m - 1;
		int p2 = n - 1;
		
		while (p1 >= 0 && p2 >= 0) 
			nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
			
		System.arraycopy(nums2, 0, nums1, 0, p2+1);
		
		
	}
}
