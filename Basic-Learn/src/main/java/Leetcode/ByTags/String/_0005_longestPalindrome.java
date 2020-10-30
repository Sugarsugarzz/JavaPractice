package Leetcode.ByTags.String;

/**
 * 5. 最长回文子串
 * Medium
 */

public class _0005_longestPalindrome {

    public static void main(String[] args) {

        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {

        // 一、暴力法
//        String ans = "";
//        int max = 0;
//        int len = s.length();
//        for (int i = 0; i < len; i++) {
//            for (int j = i + 1; j <= len; j++) {
//                String test = s.substring(i, j);
//                if (isPalindromic(test) && test.length() > max) {
//                    ans = test;
//                    max = ans.length();
//                }
//            }
//        }
//        return ans;

        // 二、动态规划
        int len = s.length();
        if (len < 2)
            return s;

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        // 初始化
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < len; i++)
            dp[i][i] = true;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] =  true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true，则表示 s[i...j] 是回文，记录此回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    // 判断整个字符串是否是回文串
    private static boolean isPalindromic(String s) {

        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i))
                return false;
        }
        return true;
    }
}
