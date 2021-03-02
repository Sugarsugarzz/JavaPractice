package Leetcode.AimOffer;

/**
 * 14-II. 剪绳子
 * Medium
 */
public class _14_02_cuttingRope {
    public static void main(String[] args) {
        System.out.println(cuttingRope(900));
    }

    public static int cuttingRope(int n) {
        // 动态规划超出范围，根据数学推导，尽可能多划分出3，能使乘积最大
        if (n < 4) {
            return n - 1;
        }
        long result = 1L;
        while (n > 4) {
            result = result * 3 % 1000000007;
            n -= 3;
        }
        result = result * n % 1000000007;
        return (int) result;
    }
}
