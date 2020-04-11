package Leetcode.easy;

/**
 * 
 * title:报数
 * 
 * description：报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 
 * limit:整数顺序将表示为一个字符串。
 * 
 * label：字符串
 * 
 */

public class _0038_countAndSay {

	public static void main(String[] args) {
		// 测试
		int n = 5;  //13112221
		String result = countAndSay(n);
		System.out.println(result);
//		StringBuffer sb = new StringBuffer("1");
//		int count = 4;
//		char begin = '5';
////		sb.append(count + begin);
//////		sb.append(count);
//////		sb.append(begin);
////		System.err.println(begin);
////		System.out.println(sb.toString());
	}

	// 递归
	public static String countAndSay(int n) {
		
		//n=1时，直接返回1
		if (n==1) 
			return "1";	
		
		// 递归，通过第n-1个字符串获取第n个
		String newString = countAndSay(n-1);
		StringBuffer sb = new StringBuffer();
		// 获取字符串第一个字符为比较对象
		char begin = newString.charAt(0);
		// count用来计数，拿第一个字符作为比较对象，比较时至少有一个匹配，所以count从1开始
		int count = 1;
		for (int i=1; i<newString.length(); i++) {
			if (newString.charAt(i) == begin) {
				count++;
			} else {
				// 得分开append，一起append会相加
				sb.append(count);
				sb.append(begin);
				begin = newString.charAt(i); // 将当前字符设为比较对象
				count = 1; // 重新计数
			}
		}
		if (begin == newString.charAt(newString.length()-1)) {
			sb.append(count);
			sb.append(begin);
		}
		
		
		return sb.toString();
	}
}
