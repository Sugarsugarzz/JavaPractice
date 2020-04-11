package Leetcode.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.Stack;

/**
 * 
 * title：最长回文子串
 * 
 * description：  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * limit：
 * 
 * label：字符串、动态规划
 * 
 */

public class _0005_longestPalindrome {

	public static void main(String[] args) {
		// 测试
		String s = "cbbd";
		String ans = longestPalindrome_2(s);
		System.out.println(ans);

	}

	// 1、暴力法
	public static String longestPalindrome(String s) {

		int len = s.length();
		if (len < 2)
			return s;
		int maxLen = 1;
		// 不断调整左右下标，遍历子串
		String res = s.substring(0, 1);
		for (int i = 0; i < len; i++)
			for (int j = i + 1; j < len; j++) {
				// 长度比当前保存的最长长度长，且是有效回文子串，则存储长度和子串
				if (j - i + 1 > maxLen && valid(s, i, j)) {
					maxLen = j - i + 1;
					res = s.substring(i, j + 1);
				}
			}

		return res;
	}

	// 子方法，用于判断字符串是否是回文字符串
	public static boolean valid(String s, int start, int end) {

		while (start < end) {
			if (s.charAt(start) != s.charAt(end))
				return false;
			start++;
			end--;
		}

		return true;
	}

	// 2、动态规划
	// “状态”定义为原字符串的一个子串是否为回文子串。
	// (1)dp[i][j] → s[i,j]是否是回文子串
	// (2)状态转移方程：dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
	// 边界条件：j-1-(i+1)+1 < 2 → j-i<3  所以子串s[i,j]的长度为2或3的时候，比较一下头尾两个字符是否相等就可以判定是否是回文串
	// 在 s[i] == s[j] 成立和 j - i < 3 的前提下，直接可以下结论，dp[i][j] = true，否则才执行状态转移。
	// (3)初始化：初始化的时候，单个字符一定是回文串，因此把对角线先初始化为 1，即 dp[i][i] = 1 。
	// (4)考虑输出：只要一得到 dp[i][j] = true，就记录子串的长度和起始位置，没有必要截取
	// (5)考虑压缩
	public static String longestPalindrome_2(String s) {
		int len = s.length();
		if (len < 2)
			return s;
		boolean[][] dp = new boolean[len][len];
		
		// 初始化
		for (int i=0; i<len; i++)
			dp[i][i] = true;
		
		int maxLen = 1;
		int start = 0;
		
		for (int j = 1; j < len; j++) {
			for (int i = 0; i < j; i++) {
				
				if (s.charAt(i) == s.charAt(j)) {
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				} else {
					dp[i][j] = false;
				}
				
				// 只要dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
				if (dp[i][j]) {
					int curLen = j - i + 1;
					if (curLen > maxLen) {
						maxLen = curLen;
						start = i;
					}
				}
			}
		}
		
		return s.substring(start, start + maxLen);
	}
	
}
