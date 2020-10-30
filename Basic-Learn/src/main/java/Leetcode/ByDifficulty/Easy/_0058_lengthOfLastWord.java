package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title:最后一个单词的长度
 * 
 * description：给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
			    如果不存在最后一个单词，请返回 0 。
 * 
 * limit:一个单词是指由字母组成，但不包含任何空格的字符串。
 * 
 * label：字符串遍历
 * 
 */

public class _0058_lengthOfLastWord {

	public static void main(String[] args) {
		// 测试
		String s = "Hello World";
		int len = lengthOfLastWord(s);
		System.out.println(len);
	}

	// 自己的方法
	// trim()去除首尾空格，split获得最后一个单词，得到长度
	public static int lengthOfLastWord(String s) {
		
		String s2 = s.trim();
		if (s2.length() == 0)
			return 0;
		String[] words = s2.split(" ");
		int result = words[words.length - 1].length();
		System.out.println(result);
				
		return result;
	}
	
	// 从后向前遍历
	public static int lengthOfLastWord_method2(String s) {
		
		int end = s.length() - 1;
		while(end >= 0 && s.charAt(end) == ' '){
			end--;
		}
		if (end < 0) return 0;
		int start = end;
		while (start >= 0 && s.charAt(start) != ' ') {
			start--;
		}
		
		return end - start;
	}
	
	
	
}