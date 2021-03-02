package Leetcode.ByTags.Math;

/**
 * 50. Pow(x,n)
 * Medium
 */
public class _0050_myPow {
    public static void main(String[] args) {
        System.out.println(myPow(2.0000, 10));
    }

    public static double myPow(double x, int n) {
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
