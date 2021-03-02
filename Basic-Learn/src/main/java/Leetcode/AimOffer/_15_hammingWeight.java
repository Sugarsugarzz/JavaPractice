package Leetcode.AimOffer;

/**
 * 15. 二进制中1的个数
 * Easy
 */
public class _15_hammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(3));
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);  // 清除最后一个1为0
        }
        return count;
    }
}
