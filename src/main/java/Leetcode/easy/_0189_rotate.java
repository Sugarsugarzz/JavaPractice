package Leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * title：旋转数组
 * 
 * description：  给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 
 * limit：要求使用空间复杂度为 O(1) 的 原地 算法。
 * 
 * label：数组
 * 
 */

public class _0189_rotate {

	public static void main(String[] args) {
		// 测试
		int[] nums = {1, 2, 3, 4, 5, 6, 7};
		int k = 2;
		rotate(nums, k);
		for (int i : nums)
			System.out.print(i + " ");
	}

	// 暴力法
	public static void rotate(int[] nums, int k) {
		int pre, tmp;
		for (int i=0; i<k; i++) {
			pre = nums[nums.length - 1];
			for (int j=0; j<nums.length; j++) {
				tmp = nums[j];
				nums[j] = pre;
				pre = tmp;
			}
		}
	}
	
	// 环状替换
	public static void rotate_2(int[] nums, int k) {
		k = k % nums.length;
		
		
	}

}
