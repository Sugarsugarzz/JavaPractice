package Leetcode.ByDifficulty.easy;import java.util.Arrays;

import javax.sound.sampled.LineListener;

/**
 * 
 * title:加一	
 * 
 * description：给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
			最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
			你可以假设除了整数 0 之外，这个整数不会以零开头。
			
 * limit:
 * 
 * label：数组
 * 
 */

public class _0066_plusOne {

	public static void main(String[] args) {
		// 测试
		int[] nums = {9};
		int[] result = plusOne(nums);
		for(int i: result)
			System.out.println(i);
		
	}

	// 自己的方法
	public static int[] plusOne(int[] digits) {
				
		int position = digits.length - 1;
		// 遇到9就将该位置置0，position往前走一位
		while ( digits[position] == 9) {
			digits[position] = 0;
			position--;
			// 数组全为9的情况，新建一个长度为n+1的数组，以0填充，首位为1
			if (position == -1) {
				int[] array = new int[digits.length+1];
				Arrays.fill(array, 0);
				array[0] = 1;
				return array;
			}
		}
		digits[position] ++;
			
		return digits;
	}
	
	
	
}