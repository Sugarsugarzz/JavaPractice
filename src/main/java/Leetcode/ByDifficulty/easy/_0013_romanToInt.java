package Leetcode.ByDifficulty.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.NumberUpSupported;
import javax.xml.bind.annotation.W3CDomHandler;

/**
 * 
 * title:罗马数字转整数 description：给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * label：
 */

public class _0013_romanToInt {

	public static void main(String[] args) {

		// 测试
		String s = "MCMXCIV";

//		int result = romanToInt_method1(s);
//		System.out.println(result);
		
		int result = romanToInt_method2(s);
		System.out.println(result);
		
		
	}

	// 自己的方法（暴力法）
	// 转为char数组，挨个匹配，加到count上
	public static int romanToInt_method1(String s) {

		int count = 0;
		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			switch (c[i]) {
			case 'I': {
				if ((i+1)<c.length && c[i+1] == 'V') {
					count += 4;
					i++;
					continue;
				}
				if ((i+1)<c.length && c[i + 1] == 'X') {
					count += 9;
					i++;
					continue;
				}
				count += 1;
				continue;
			}

			case 'V': {
				count += 5;
				continue;
			}

			case 'X': {
				if ((i+1)<c.length && c[i + 1] == 'L') {
					count += 40;
					i++;
					continue;
				}
				if ((i+1)<c.length && c[i + 1] == 'C') {
					count += 90;
					i++;
					continue;
				}
				count += 10;
				continue;
			}

			case 'L': {
				count += 50;
				continue;
			}

			case 'C': {
				if ((i+1)<c.length && c[i + 1] == 'D') {
					count += 400;
					i++;
					continue;
				}
				if ((i+1)<c.length && c[i + 1] == 'M') {
					count += 900;
					i++;
					continue;
				}
				count += 100;
				continue;
			}

			case 'D': {
				count += 500;
				continue;
			}

			case 'M': {
				count += 1000;
				continue;
			}
			}
		}
		return count;
	}
	
	// 哈希表法
	//
	public static int romanToInt_method2(String s) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("I", 1);
		map.put("IV", 4);
		map.put("IX", 9);
		map.put("V", 5);
		map.put("X", 10);
		map.put("XL", 40);
		map.put("XC", 90);
		map.put("L", 50);
		map.put("C", 100);
		map.put("CD", 400);
		map.put("CM", 900);
		map.put("D", 500);
		map.put("M", 1000);
		
		int ans = 0;

		for (int i=0; i<s.length(); ) {
			
			if (i+1<s.length() && map.containsKey(s.substring(i, i+2))){
				ans += map.get(s.substring(i, i+2));
				i += 2;
			}else {
				ans += map.get(s.substring(i, i+1));
				i += 1;
			}
		}

		return ans;
	}
	

}
