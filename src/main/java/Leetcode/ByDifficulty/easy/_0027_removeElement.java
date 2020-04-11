package Leetcode.ByDifficulty.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.print.attribute.standard.NumberUpSupported;
import javax.xml.bind.annotation.W3CDomHandler;

/**
 * 
 * title:给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 
 * limit:不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
		 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *		 
 * label：数组
 */

public class _0027_removeElement {

	public static void main(String[] args) {

		// 测试
		int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
		int result = removeElement(nums, 2);
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
	public static int removeElement(int[] nums,int val) {
		
		int i = 0;
		for (int j=0; j<nums.length; j++) {
			if (nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
		}
		return i;
	}
}
