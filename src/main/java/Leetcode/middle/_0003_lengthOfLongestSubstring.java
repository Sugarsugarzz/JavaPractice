package Leetcode.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.Stack;

/**
 * 
 * title：无重复字符的最长子串
 * 
 * description：  给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * limit：
 * 
 * label：字符串、双指针、哈希表
 * 
 */

public class _0003_lengthOfLongestSubstring {

	public static void main(String[] args) {
		// 测试
		String s = new String("pwwkew");
		int ans = lengthOfLongestSubstring_2(s);
		System.out.println(ans);
	}

	// 滑动窗口
	public static int lengthOfLongestSubstring(String s) {
		int ans = 0, i = 0, j = 0;
		Set<Character> set = new HashSet<>();
		// 不断从左缩小窗口，直到窗口中不存在与下一个字符重复的字符。
		while (i < s.length() && j < s.length()) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	// 优化的滑动窗口（Hashmap）
	public static int lengthOfLongestSubstring_2(String s) {
		int ans = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int start=0, end=0; end<s.length(); end++) {
			char c = s.charAt(end);
			if (map.containsKey(c)) {
				start = Math.max(start, map.get(c));
			}
			ans = Math.max(ans, end-start+1);
			map.put(c, end+1);
		}
		return ans;
	}
}
