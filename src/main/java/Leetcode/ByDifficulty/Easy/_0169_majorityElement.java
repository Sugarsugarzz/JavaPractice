package Leetcode.ByDifficulty.Easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * title：多数元素
 * 
 * description：  给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *				你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * limit：
 * 
 * label：数组
 * 
 */

public class _0169_majorityElement {

	public static void main(String[] args) {
		// 测试
		int[] nums = {3, 2, 3};
		int ans = majorityElement(nums);
		System.out.println(ans);
	}

	// 哈希表法
	public static int majorityElement(int[] nums) {
		// 存储 <数字，出现次数>
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer i : nums) {
			if (map.containsKey(i))
				map.put(i, map.get(i)+1);
			else 
				map.put(i, 1);
		}
		int len = nums.length / 2;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > len)
				return entry.getKey();
		}
		return 0;
	}
}
