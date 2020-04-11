package Leetcode.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.NumberUpSupported;

/**
 * 
 * title:整数反转
 * description：给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * limit: 存储空间最多32位数字
 * label：数学
 * 
 */

public class _0007_Reverse {
	
	public static void main(String[] args) {
		
		// 测试
		int x = 123;
		int result = reverse(x);
		System.out.println(result);
		
	}
	
	/*
	 * 用数学方法实现堆栈操作
	 * //pop操作：
		pop = x % 10;
		x /= 10;
		
		//push操作：
		temp = rev * 10 + pop;
		rev = temp;
	 */
	
	// 数学方法进行堆栈操作，MAX_VALUE = 2147483647 ，判定条件中的7和-8由此而来
	// 如果rev大于Integer最大值个位前面的数，在push后，自然会大于Integer最大值。
	// rev等于最大值除以10后的值，pop就与个位的数相比较，若大于7，则整数会溢出。
	public static int reverse(int x) {

		int rev = 0;
		while (x!=0) {
			int pop = x%10;
			x /= 10;
			if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop > 7)) return 0;
			if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop < -8)) return 0;
			rev = rev * 10 + pop;
		}
	
		return rev;
	}

}
