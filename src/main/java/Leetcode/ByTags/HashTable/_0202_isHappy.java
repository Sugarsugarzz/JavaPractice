package Leetcode.ByTags.HashTable;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * Easy
 */

public class _0202_isHappy {

    public static void main(String[] args) {

        int n = 19;
        boolean res = isHappy(n);
        System.out.println(res);
    }

    public static boolean isHappy(int n) {

        // 如果无法到达1，会进入循环
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public static int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int i = n % 10;
            n = n / 10;
            total += i * i;
        }
        return total;
    }
}
