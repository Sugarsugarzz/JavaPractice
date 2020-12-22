package Leetcode.ByTags.String;

/**
 * 647. 回文子串
 * Medium
 */

public class _0647_countSubstrings {

    public static void main(String[] args) {

        String s = "aaa";
        System.out.println(countSubstrings(s));
    }

    public static int countSubstrings(String s) {

        /**
         * 枚举回文子串的两种方式：
         *  - 枚举所有子串，然后判断
         *  - 枚举每一个可能的回文中心，用两个指针分别向左右扩展
         */

        // 一、双指针（暴力）
//        int count = 0;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i; j < s.length(); j++) {
//                if (isPalindrome(s.substring(i, j + 1)))
//                    count++;
//            }
//        }
//        return count;

        // 二、动态规划 time O(n^2)  space O(n^2)
//        int count = 0;
//        boolean[][] dp = new boolean[s.length()][s.length()];
        // 这里注意循环方式：外层循环是每一列，使当前列的前面的列都确定下来
//        for (int j = 0; j < s.length(); j++) {
//            for (int i = 0; i <= j; i++) {
//                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
//                    dp[i][j] = true;
//                    count++;
//                }
//            }
//        }
//        return count;

        // 三、中心扩展法 time O(n^2)  space O(1)
        // 1. 分别考虑回文长度是奇数还是偶数
//        int count = 0;
//        for (int i = 0; i < s.length(); i++) {
//            count += extendSubstrings(s, i, i);  // 奇数个
//            count += extendSubstrings(s, i, i+1);  // 偶数个
//        }
//        return count;

        // 1. 一次同时考虑奇数和偶数情况
        // 设字符串长度为n，则有 2*n-1 个回文中心，每次 left = i / 2, right = i / 2 + i % 2
        int count = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        return count;
    }

    // 分两种情况中心扩展
    public static int extendSubstrings(String s, int left, int right) {

        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            System.out.println(s.substring(left, right + 1));
            count++;
            left--;
            right++;
        }
        return count;
    }


    // 判断子串是否是回文串
    public static boolean isPalindrome(String s) {

        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
