package Leetcode.ByDifficulty.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * title：反转字符串中的单词 III
 * 
 * description：  给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 
 * limit：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * 
 * label：字符串
 * 
 */

public class _0557_reverseWords {
									
	public static void main(String[] args) {
		// 测试
		String s = "Let's take LeetCode contest";
		String ans = reverseWords(s);
		System.out.println(ans);
	}
	
	// 
	public static String reverseWords(String s) {
		// 以空格为分隔符将字符串分隔
		StringTokenizer token = new StringTokenizer(s, " ");
		// 存储最后结果
		StringBuilder sb = new StringBuilder();
		// 对每个单词单独处理
		while (token.hasMoreTokens()) {
			String word = token.nextToken();
			// 转为char数组，交换位置
			char[] wordChars = word.toCharArray();
			int left = 0, right = word.length()-1;
			while (left < right) {
				char tmp = wordChars[left];
				wordChars[left++] = wordChars[right];
				wordChars[right--] = tmp;
			}
			sb.append(new String(wordChars) + " ");
		}
		
		return sb.toString().trim();
	}
}
