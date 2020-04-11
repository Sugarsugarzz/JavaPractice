package Leetcode.easy;

/**
 * 
 * title:二进制求和		
 * 
 * description：给定两个二进制字符串，返回他们的和（用二进制表示）。
	
 * limit:输入为非空字符串且只包含数字 1 和 0。
 * 
 * label：
 * 
 */

public class _0067_addBinary {

	public static void main(String[] args) {
		// 测试
		String a = "11";
		String b = "1";
		String result = addBinary_method2(a, b);
		System.out.println(result);
		
	}
	
	// 库方法
	// 大值计算不可行
	public static String addBinary(String a, String b) {
	        
		int aa = Integer.valueOf(a, 2);
		int bb = Integer.valueOf(b, 2);
		int add = aa + bb;
		String result = Integer.toBinaryString(add);

		return result;
	}
	
	// 正思考方法
	public static String addBinary_method2(String a, String b) {
		
		// 转数组
		char[] aArray = a.toCharArray();
		char[] bArray = b.toCharArray();
		// 取出结果数组长度
		int len = Math.max(aArray.length, bArray.length);
		// 生成结果数组
		char[] rArray = new char[len];
		// 进位标识符
		boolean jw = false;
		
		for (int i=1; i<=len; i++) {
			// 从后往前获取char，对于较短数组，超出的部分补'0'
			char aChar = aArray.length - i >= 0 ? aArray[aArray.length - i] : '0';
			char bChar = bArray.length - i >= 0 ? bArray[bArray.length - i] : '0';
			char benChar = ' ';
			
			// 计算
			if (aChar == '0' && bChar == '0') {
				benChar = jw ? '1' : '0';
				jw = false;
			} else if (aChar == '0' && bChar == '1') {
				benChar = jw ? '0' : '1';
			} else if (aChar == '1' && bChar == '0') {
				benChar = jw ? '0' : '1';
			} else if (aChar == '1' && bChar == '1') {
				benChar = jw ? '1' : '0';
				jw = true;
			}
			rArray[len - i] = benChar;
		}
		return jw ? "1" + new String(rArray) : new String(rArray);
	}
	
	
	
	
}