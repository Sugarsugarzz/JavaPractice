package Leetcode.ByDifficulty.easy;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * 
 * title: 验证回文串
 * 
 * description：给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 
 * limit: 本题中，我们将空字符串定义为有效的回文串。
 * 
 * label：双指针、字符串
 * 
 */

public class _0125_isPalindrome {

	public static void main(String[] args) {
		// 测试
		String s = "0P";
		boolean result = isPalindrome(s);
		System.out.println(result);

	}
	
	public static boolean isPalindrome(String s) {
		// 转小写，去除非字母字符
		s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
		System.out.println(s);
		// 双指针
		int i = 0;
		int j = s.length() - 1;
		while(i < j) {
			if (s.charAt(i++) != s.charAt(j--))
				return false;
		}
		return true;
	}
}
