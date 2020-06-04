package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title: 移动零
 * 
 * description：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * limit:必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
 * 
 * label：数组
 * 
 */

public class _0283_moveZeroes {

	public static void main(String[] args) {
		// 测试
		int[] nums = {0, 1, 0, 3, 12};
		for (Integer i : nums) 
			System.out.print(i + ",");
		moveZeroes_2(nums);
		System.out.println();
		for (Integer i : nums) 
			System.out.print(i + ",");
	}
	
	// 双指针
	public static void moveZeroes(int[] nums) {
		// 遍历，遇到0，将0与后面第一个非零数字交换
		for (int i=0; i<nums.length; i++) {
			if (nums[i] == 0)
				for (int j=i+1; j<nums.length; j++) {
					if (nums[j] != 0) {
						nums[i] = nums[j];
						nums[j] = 0;
						break;
					}
				}
		}
	}
	
	// 一次遍历，将数字全部移到前面，然后补0
	public static void moveZeroes_2(int[] nums) {
		
		int count = 0;
		for (int i=0; i<nums.length; i++) {
			if (nums[i] != 0)
				nums[count++] = nums[i];
		}
		for (int i=count; i<nums.length; i++) {
			nums[i] = 0;
		}
		
	}
}
