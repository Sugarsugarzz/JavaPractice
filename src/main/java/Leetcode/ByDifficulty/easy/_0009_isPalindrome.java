package Leetcode.ByDifficulty.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * title:回文数 
 * description：判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * label：
 */

public class _0009_isPalindrome {

	public static void main(String[] args) {

		// 测试
		int x = 123;
		// boolean result = isPalindrome_method1(x);
		// System.out.println(result);

		boolean result = isPalindrome_method2(x);
		System.out.println(result);

	}

	// 自己的方法
	// 0是，负数不是，整数进行反转，与原数比较一下，一样则是。
	public static boolean isPalindrome_method1(int x) {

		int rev = 0;
		int temp = x;
		if (x < 0)
			return false;

		while (temp != 0) {
			int pop = temp % 10;
			temp /= 10;
			rev = rev * 10 + pop;
		}
		return rev == x;
	}

	// 反转一半数字
	// 反转一半，将反转后的，与该数字前一半比较，相同则是回文数
	public static boolean isPalindrome_method2(int x) {

		// 负数false || 尾数为0，只有0满足回文数的情况
		if (x < 0 || (x % 10 == 0 && x != 0 ))
			return false;
		
		// 反转到一半停止，即x大于rev时(不用太关注为什么这种情况下是到一半，它能处理所有情况)
		int rev = 0;
		while (x > rev) {
			int pop = x % 10;
			x /= 10;
			rev = rev * 10 + pop;
		}
		
		// 数字为奇数个时，用 rev/10去除中间位
		return x == rev || x == rev/10;
	}
}
