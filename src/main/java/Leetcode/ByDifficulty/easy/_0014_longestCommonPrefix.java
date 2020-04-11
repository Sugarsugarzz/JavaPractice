package Leetcode.ByDifficulty.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.NumberUpSupported;
import javax.xml.bind.annotation.W3CDomHandler;

/**
 * 
 * title:编写一个函数来查找字符串数组中的最长公共前缀。
		 如果不存在公共前缀，返回空字符串 ""。
 * label：字符串
 */

public class _0014_longestCommonPrefix {

	public static void main(String[] args) {

		// 测试
		String[] s = {"flower", "flow", "flight"};
		String result = longestCommonPrefix_method1(s);
		
	}

	/*
	 * String.indexof()用法
	 * 查找指定字符或字符串在字符串中第一次出现地方的索引，未找到的情况返回 -1。
	 */
	
	// 水平扫描法
	// 先得到前两个字符串的最长前缀子串，用这个子串与后一个字符串比较，一直到结束。
	public static String longestCommonPrefix_method1(String[] s) {
		if (s.length == 0) return "";
		String prefix = s[0];
		for (int i=1; i<s.length; i++) 
			while(s[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) return "";
			}
		
		return prefix;
	}
	
	// 纵向扫描法
	// 如果数组最后有个非常短的字符串，上述方法比较次数就较多。
	// 这里采用从前往后枚举字符串每一列的方法。
	public static String longestCommonPrefix_metho2(String[] s){
		if (s.length == 0) return "";
		for (int i=0; i<s[0].length(); i++) {
			char c = s[0].charAt(i);
			for (int j=1; j<s.length; j++) {
				if (i == s[j].length() || s[j].charAt(i) != c)
					return s[0].substring(0, i);
			}
		}
		return s[0];
	}
	

}
