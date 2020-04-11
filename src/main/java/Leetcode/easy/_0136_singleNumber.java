package Leetcode.easy;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * title: 只出现一次的数字
 * 
 * description：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * limit:
 * 
 * label：位运算、哈希表
 * 
 */

public class _0136_singleNumber {

	public static void main(String[] args) {
		// 测试
		int[] numbers = { 4, 1, 2, 2, 1 };
		int result = singleNumber(numbers);
		System.out.println(result);
		// 异或法
		System.out.println(4^1^2^2^1);
	}

	// Hash表法
	public static int singleNumber(int[] nums) {
		HashMap<Integer, Integer> tmp = new HashMap<>();
		
		for (Integer i : nums) {
			Integer count = tmp.get(i);
			count = count == null? 1: tmp.get(nums[i])+1;
			tmp.put(i, count);
		}
		
		for(Integer i : tmp.keySet()) {
			Integer count = tmp.get(i);
			if (count == 1)
				return i;
		}

		return -1;
	}
}
