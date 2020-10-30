package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title:实现strStr()  （类似Java的string.indexof())
 
 * description：给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

 * limit:

 * label：字符串
 * key: KMP字符串匹配算法
 */

public class _0028_strStr {

	public static void main(String[] args) {
		// 测试
		String haystack = "hello";
		String needle = "ll";
//		int result = strStr_method1(haystack, needle);
//		System.out.println(result);
		
//		int result = strStr_method2(haystack, needle);
//		System.out.println(result);
		
		int result = strStr_method3(haystack, needle);
		System.out.println(result);
	}

	// 自己的方法
	// 库方法
	public static int strStr_method1(String haystack, String needle) {

		return haystack.indexOf(needle);
	}
	
	// 暴力法
	public static int strStr_method2(String haystack, String needle) {
		int M = needle.length();
		int N = haystack.length();
		
		if (N < M) 
			return -1;
		else if (M == 0)
			return 0;
		for (int i=0; i<N-M+1; i++) {
			if (haystack.substring(i, i+M).equals(needle))
				return i;
		}
		return -1;
	}
	
	// KMP算法 (还没懂)
	// KMP 算法最关键的步骤就是构造这个状态转移图。要确定状态转移的行为，得明确两个变量，一个是当前的匹配状态，另一个是遇到的字符；
	//	  确定了这两个变量后，就可以知道这个情况下应该转移到哪个状态。
	// URL :  https://leetcode-cn.com/problems/implement-strstr/solution/kmp-suan-fa-xiang-jie-by-labuladong/
	public static int strStr_method3(String haystack, String needle) {
		
		_0028_KMP kmp = new _0028_KMP(needle);
		int result = kmp.search(haystack);
		
		return result;
	}
}
