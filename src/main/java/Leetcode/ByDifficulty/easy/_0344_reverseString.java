package Leetcode.ByDifficulty.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * title: 反转字符串
 * 
 * description：编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 
 * limit:不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 
 * label：字符串，双指针
 * 
 */

public class _0344_reverseString {

	public static void main(String[] args) {
		// 测试
		char[] s = { 'h', 'e', 'l', 'l', 'o' };
		reverseString(s);
		for (char c : s)
			System.out.print(c + " ");
	}

	// 双指针
	public static void reverseString(char[] s) {
		// p1指向开头，p2指向结尾，逐位交换反转
		int p1 = 0;
		int p2 = s.length - 1; 
		while (p1 < p2) {
			char tmpChar = s[p1];
			s[p1++] = s[p2];
			s[p2--] = tmpChar;
		}
	}
}
