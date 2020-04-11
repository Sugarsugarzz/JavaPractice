package Leetcode.easy;

/**
 * 
 * title:最大子序和
 * 
 * description：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * limit:
 * 
 * label：数组
 * 
 */

public class _0053_maxSubArray {

	public static void main(String[] args) {
		// 测试
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		int result = maxSubArray(nums);
		System.out.println(result);
	}

	// 暴力法
	public static int maxSubArray(int[] nums) {
		
		int max = Integer.MIN_VALUE;
		for (int i=0; i<nums.length; i++) {
			int sum = 0;
			for (int j=i; j<nums.length; j++) {
				sum += nums[j];
				if (sum > max)
					max = sum;
			}
		}		
		return max;
	}
	
	
}
