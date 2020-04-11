package Leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * title：存在重复元素
 * 
 * description：  给定一个整数数组，判断是否存在重复元素。 如果任何值在数组中出现至少两次，函数返回true。
 * 				如果数组中每个元素都不相同，则返回 false。
 * 
 * limit：
 * 
 * label：数组、哈希表s
 * 
 */

public class _0217_containsDuplicate {

	public static void main(String[] args) {
		// 测试
		int[] nums = {1, 2, 3, 1};
		boolean ans = containsDuplicate_2(nums);
		System.out.println(ans);
	}

	// 哈希表 时间复杂度O(n) 空间O(n)
	public static boolean containsDuplicate(int[] nums) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<nums.length; i++) {
			if (map.containsKey(nums[i])) 
				return true;
			else 
				map.put(nums[i], 1);
		}
		return false;
	}
	
	// 排序后，遍历找相同元素 时间复杂度O(nlogn)
	public static boolean containsDuplicate_2(int[] nums) {

		Arrays.sort(nums);
		for (int i=1; i<nums.length; i++) {
			if (nums[i-1] == nums[i])
				return true;
		}
		
		return false;
	}
	
	
}
