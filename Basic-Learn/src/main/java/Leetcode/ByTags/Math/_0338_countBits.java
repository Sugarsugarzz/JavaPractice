package Leetcode.ByTags.Math;

import java.util.Arrays;

/**
 * 338. 比特位计数
 * Medium
 */

public class _0338_countBits {

    public static void main(String[] args) {
        int num = 5;
        System.out.println(Arrays.toString(countBits(num)));
    }

    public static int[] countBits(int num) {

        // 一、自带库
//        int[] ans = new int[num+1];
//        for (int i = 0; i <= num; i++) {
//            ans[i] = Integer.bitCount(i);
//        }
//        return ans;

        // 二、位掩码
//        int[] res = new int[num+1];
//        for (int i = 0; i <= num; i++) {
//            int ans = 0, mask = 1;
//            for (int j = 0; j < 32; j++) {
//                if ((mask & i) != 0) ans++;
//                mask <<= 1;
//            }
//            res[i] = ans;
//        }
//        return res;

        // 三、位运算技巧
//        int[] ans = new int[num+1];
//        for (int i = 0; i <= num; i++) {
//            int sum = 0, n = i;
//            while (n != 0) {
//                sum++;
//                n &= (n - 1);
//            }
//            ans[i] = sum;
//        }
//        return ans;

        // 四、奇偶性判断
        // 奇数的，只需要在前一个数上加一；偶数的，1的个数和末尾抹0后的数一样
        int[] ans = new int[num+1];
        for (int i = 0; i <= num; i++) {
            if (i % 2 == 1) {
                ans[i] = ans[i-1] + 1;
            } else {
                ans[i] = ans[i >> 1];
            }
        }
        return ans;
    }
}
