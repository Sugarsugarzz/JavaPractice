package Leetcode.ByTags.HashTable;

/**
 * 204. 计数质数
 * Easy
 */

public class _0204_countPrimes {

    public static void main(String[] args) {
        int n = 10;
        int res = countPrimes(n);
        System.out.println(res);
    }

    public static int countPrimes(int n) {

        // 一、暴力法（超时）
        // 计算一个数是否为质数的思路：对每一个比其小的数进行取余运算（大于2），并将取余为零的情况进行计数，如/果计数结果为1，则该数为质数
        // 实际操作中，由于1和任意数必然取余为零，所以直接排除。当没有取余为零的情况时，其才为质数。
//        int count = 0;
//        for (int i = 2; i < n; i++) {
//            boolean flag = true;
//            for (int j = 2; j < i; j++) {
//                if ( i % j == 0) {
//                    flag = false;
//                    break;
//                }
//            }
//            if (flag)
//                count++;
//        }
//

        // 二、厄拉多塞筛法
        int count = 0;
        boolean[] flags = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!flags[i]) {
                count++;
                for (int j = i * 2; j < n; j = j + i) {
                    flags[j] = true;
                }
            }
        }
        return count;
    }
}
