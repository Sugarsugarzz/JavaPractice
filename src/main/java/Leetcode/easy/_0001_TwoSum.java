package Leetcode.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.NumberUpSupported;

/**
 * 
 * title: 两数之和
 * description：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
   你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * label：  
 *
 */

public class _0001_TwoSum {
	
	public static void main(String[] args) {
		
		// 测试
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		int[] result = new int[2];
		
//		result = twoSum_method1(nums, target);
//		for (int i: result)
//			System.out.println(">>>" + result[i]);
		
//		result = twoSum_method2(nums, target);
//		for (int i: result)
//			System.out.println(">>>" + result[i]);

		result = twoSum_method3(nums, target);
		for (int i: result)
			System.out.println(">>>" + result[i]);
	}
	
	// 暴力法
	// 遍历取值，和剩余值依次相加与target比较，找到匹配的两个数，返回它们的下标
	// 复杂度O(n^2)
	public static int[] twoSum_method1(int[] nums, int target) {
		
		for (int i=0; i<nums.length; i++) {
			for (int j=i+1; j<nums.length; j++) {
				if ((nums[i] + nums[j]) == target) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	// 两遍哈希法
	// 先将数组的值和其索引存入到表中，再检查每个元素对应的目标元素是否在表中，返回它们的下标
	public static int[] twoSum_method2(int[] nums, int target) {
		// Hashmap空间换时间
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i=0; i<nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i=0; i<nums.length; i++) {
			int aa = target - nums[i];
			if (map.containsKey(aa) && map.get(aa)!=i) {
				return new int[] {i, map.get(aa)};
			}
		}
		return null;
		
	}
	
	
	// 一遍哈希法
	// 在插入值的同时，回头看是否有匹配的目标元素，如有直接返回它们的下标
	public static int[] twoSum_method3(int[] nums, int target) {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i=0; i<nums.length; i++) {
			int aa = target - nums[i];
			if (map.containsKey(aa)) {
				return new int[] {map.get(aa), i};
			}
			map.put(nums[i], i);
		}
		
		return null;
	}

}
