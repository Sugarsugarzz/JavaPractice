package Leetcode.AimOffer;

/**
 * 10-II. 青蛙跳台阶问题
 * Easy
 */
public class _10_02_numWays {
    public static void main(String[] args) {
        int n = 7;
        System.out.println(numWays(n));
    }

    public static int numWays(int n) {

        if (n <= 1)
            return 1;

        int a = 1, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return b;
    }
}
