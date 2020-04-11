package Leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * title：IP地址无效化
 * 
 * description：  给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。 所谓无效化 IP 地址，其实就是用
 * "[.]" 代替了每个 "."。
 * 
 * limit：
 * 
 * label：字符串
 * 
 */

public class _1108_defangIPaddr {

	public static void main(String[] args) {
		// 测试
		String address = "1.1.1.1";
		String ans = defangIPaddr_2(address);
		System.out.println(ans);
	}

	// 直接调用replace，时间上有点慢
	public static String defangIPaddr(String address) {
		return address.replace(".", "[.]");
	}
	
	// StringBuilder
	public static String defangIPaddr_2(String address) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<address.length(); i++) {
			if (address.charAt(i) == '.') {
				sb.append("[.]");
			} else {
				sb.append(address.charAt(i));
			}
		}
		
		return sb.toString();
	}

}
