package Leetcode.ByTags.DynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 120. 三角形最小路径和
 * Medium
 */
public class _0139_wordBreak {

    public static void main(String[] args) {

//        String s = "applepenapple";
        String s = "catsandog";
//        System.out.println(wordBreak(s, Arrays.asList("apple", "pen")));
        System.out.println(wordBreak(s, Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {

        // 一、暴力法
        // 不能通过测试用例   "cars"  ["car", "ca", "rs"]
//        int i = 0;
//        while (i < s.length()) {
//            boolean flag = false;  // 标记每轮是否有匹配的字符串
//            for (String word : wordDict) {
//                int j = i, k = 0;
//                while (k < word.length() && s.charAt(j) == word.charAt(k)) {
//                    j++;
//                    k++;
//                }
//                if (k == word.length()) {
//                    i = j;
//                    flag = true;
//                    break;
//                }
//            }
//            if (!flag)
//                break;
//        }
//
//        return i == s.length();

        // 二、动态规划
        // boolean  dp[i] = dp[j] && check(s[j...i] in wordDict)
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
