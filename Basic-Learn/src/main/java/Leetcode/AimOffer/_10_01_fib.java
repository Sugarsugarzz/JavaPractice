package Leetcode.AimOffer;

import java.util.Arrays;

/**
 * 10-I. 斐波那契数列
 * Easy
 *
 * 大数阶乘，大数的排列组合等，一般都要求将输出结果对1000000007取模 为什么总是1000000007呢
 *
 * 1000000007是一个质数
 * int32位的最大值为2147483647，所以对于int32位来说1000000007足够大
 * int64位的最大值为2^63-1，对于1000000007来说它的平方不会在int64中溢出
 * 所以在大数相乘的时候，因为(a∗b)%c=((a%c)∗(b%c))%c，所以相乘时两边都对1000000007取模，再保存在int64里面不会溢出 ｡
 */
public class _10_01_fib {
    public static void main(String[] args) {
        int n = 50;
        System.out.println(fib(n));
    }

    public static int fib(int n) {

        // 一、递归法（超时）
//        if (n <= 1)
//            return n;
//        return fib(n-2) + fib(n-1);

        // 二、记忆化递归
//        if (n <= 1)
//            return n;
//        int[] dp = new int[n + 1];
//        dp[0] = 0;
//        dp[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
//        }
//
//        return dp[n];

        // 动态规划
        if (n <= 1)
            return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return b;
    }
}
