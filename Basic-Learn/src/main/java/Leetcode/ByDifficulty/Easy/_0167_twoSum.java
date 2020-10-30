package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title：两数之和 II - 输入有序数组
 * 
 * description：  给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。 函数应该返回这两个下标值 index1 和
 * index2，其中 index1 必须小于 index2。
 * 
 * limit：返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 
 * label：数组
 * 
 */

public class _0167_twoSum {

	public static void main(String[] args) {
		// 测试
		int[] numbers = {2, 7, 11, 15};
		int target = 9;
		int[] ans = twoSum(numbers, target);
		for (int i : ans) {
			System.out.print(i + " ");
		}
	}
	
	// 双指针法
	public static int[] twoSum(int[] numbers, int target) {
		// 从左右同时推进，如果两数之和小于target，则left向右移一位，否则right向左移一位
		int left = 0, right = numbers.length - 1;
		while (left < right) {
			int sum = numbers[left] + numbers[right];
			if (sum == target)
				return new int[] {left+1, right+1};
			else if (sum < target) 
				++left;
			else 
				--right;
		}
		return new int[] {-1, -1};
	}

}
