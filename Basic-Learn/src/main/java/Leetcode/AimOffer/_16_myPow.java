package Leetcode.AimOffer;

/**
 * 16. 数值的整数次方
 * Medium
 */
public class _16_myPow {

    public static void main(String[] args) {
        System.out.println(myPow(2.0000, -2));
    }

    public static double myPow(double x, int n) {

        // 一、快速幂
        // 将指数进行二分拆分：y = x^(n/2)。偶数指数：y * y，奇数指数：y * y * x.

        return n > 0 ? quickMul(x, n) : 1/quickMul(x, -n);
    }

    public static double quickMul(double x, int n) {
        if (n == 0) {
            return 1.0f;
        }
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }


}
