package Leetcode.ByDifficulty.easy;

/**
 * 
 * title:搜索插入位置
 * 
 * description：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * limit:你可以假设数组中无重复元素。
 * 
 * label：数组、二分查找
 * 
 */

public class _0035_searchInsert {

	public static void main(String[] args) {
		// 测试
		int[] nums = { 1, 3, 5, 6 };
		int target = 5;
		// int result = searchInsert_method1(nums, target);
		// System.out.println(result);

		int result = searchInsert_method2(nums, target);
		System.out.println(result);

	}

	/*
	 * 二分查找模板： URL ：
	 * https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-
	 * hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
	 */

	// 自己的方法
	// 暴力法
	// 大于最后一个数，直接插入到最后；相等的而直接返回i；遇到大于target的，直接将target插入到该位置
	public static int searchInsert_method1(int[] nums, int target) {

		if (nums[nums.length - 1] < target)
			return nums.length;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target)
				return i;
			if (nums[i] > target)
				return i;
		}
		return 0;
	}

	// 二分查找法
	// 
	public static int searchInsert_method2(int[] nums, int target) {

		int len = nums.length;
		if (nums[len-1] < target) 
			return len;
		
		int left = 0;
		int right = len - 1;
		
		while(left <= right) {
			int mid = (left + right) >>> 1;
			if (nums[mid] == target)
				return mid;
			else if (nums[mid] < target)
				left = mid + 1;
			else if (nums[mid] > target)
				right = mid -1;
		}
		return left;
	}
}
