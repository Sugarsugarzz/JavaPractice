package Leetcode.ByTags.Math;

/**
 * 191. 位1的个数
 * Easy
 */

public class _0191_hammingWeight {

    public static void main(String[] args) {
        int n = 3;
        System.out.println(hammingWeight(n));
    }

    public static int hammingWeight(int n) {

        // 一、自带库
//        return Integer.bitCount(n);

        // 二、位掩码移动
        // 无符号整数，32位
        // 位掩码与原数做 位的与运算，如果结果为1则原数位置上也为1
//        int ans = 0, mask = 1;
//        for (int i = 0; i < 32; i++) {
//            if ((mask & n) != 0)  ans++;
//            mask <<= 1;
//        }
//        return ans;

        // 三、位运算的小技巧
        // n & (n-1) 会将 n 的最低位的 1 变成 0
        int ans = 0;
        while (n != 0) {
            ans++;
            n &= (n - 1);
        }
        return ans;
    }
}
