package Leetcode.ByDifficulty.Easy;


/**
 * 
 * title:给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 
 * limit:不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * label：数组
 */

public class _0026_removeDuplicates {

	public static void main(String[] args) {

		// 测试
		int[] nums = { 1, 1, 2 };
		int result = removeDuplicates_method1(nums);
		System.out.println(">>>length:" + result);

		for (int i = 0; i < result; i++) {
			System.out.println(nums[i]);
		}

	}

	/*
	 * 数组是引用传参，方法内对数组修改，原数组会被修改
	 */

	// 自己的方法
	// 双指针
	public static int removeDuplicates_method1(int[] nums) {
		
		if (nums.length == 0) return 0;

		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			if (j + 1 < nums.length && nums[j + 1] == nums[j])
				continue;
			nums[i] = nums[j];
			i++;
		}
		return i;
	}

	// 官方题解
	// 双指针
	public static int removeDuplicates_method2(int[] nums) {

		int i = 0;
		for (int j=1; j<nums.length; j++) {
			if (nums[i] != nums[j]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i+1;
	}
}
