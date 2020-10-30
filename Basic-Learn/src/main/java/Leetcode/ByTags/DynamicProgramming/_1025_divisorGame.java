package Leetcode.ByTags.DynamicProgramming;

/**
 * 1025. 除数博弈
 * Easy
 */

public class _1025_divisorGame {

    public static void main(String[] args) {

        System.out.println(divisorGame(2));
    }

    public static boolean divisorGame(int N) {

        // Alice 先手
        // N 为奇数 Alice必败；N 为偶数，Alice必胜
        // 奇数的因数一定是因数
        return N % 2 == 0;
    }
}
