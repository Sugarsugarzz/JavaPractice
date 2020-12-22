package Leetcode.ByTags.Math;

/**
 * 461. 汉明距离
 * Easy
 */

public class _0461_hammingDistance {

    public static void main(String[] args) {
        int x = 1, y = 4;  // 0 0 0 1 ^ 0 1 0 0 = 0 1 0 1
        System.out.println(hammingDistance(x, y));
    }

    public static int hammingDistance(int x, int y) {

        // 一、自带库
        // 对两个数异或运算后，统计结果为1的个数，即为汉明距离
//        return Integer.bitCount(x ^ y);

        // 二、移位
        int xor = x ^ y;
        int count = 0;
        while (xor != 0) {
            if (xor % 2 == 1) {
                count++;
            }
            xor = xor >> 1;
        }
        return count;
    }
}
