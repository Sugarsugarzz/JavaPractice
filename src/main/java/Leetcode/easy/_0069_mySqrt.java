package Leetcode.easy;

/**
 * 
 * title: x 的平方根
 * 
 * description：实现 int sqrt(int x) 函数。 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 
 * limit:
 * 
 * label：
 * 
 */

public class _0069_mySqrt {

	public static void main(String[] args) {
		// 测试
		int x = 8;
		int result = mySqrt(x);
		System.out.println(result);
	}
	
	// 原理还是不太懂
	// 二分法
	public static int mySqrt(int x) {
		// 注意：针对特殊测试用例，例如 2147395599
		// 要把搜索的范围设置成长整型
		// 为了照顾到 0 把左边界设置为 0
		long left = 0;
		// # 为了照顾到 1 把右边界设置为 x // 2 + 1
		long right = x / 2 + 1;
		while (left < right) {
			// 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
			// long mid = left + (right - left + 1) / 2;
			long mid = (left + right + 1) >>> 1;
			long square = mid * mid;
			if (square > x) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		// 因为一定存在，因此无需后处理
		return (int) left;
	}

}