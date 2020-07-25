package Leetcode.ByTags.Greedy;

/**
 * 392. 判断子序列
 * Easy
 */

public class _0392_isSubsequence {

    public static void main(String[] args) {

        String s = "d";
        String t = "axxbxxxc";

        boolean res = isSubsequence(s, t);
        System.out.println(res);
    }

    public static boolean isSubsequence(String s, String t) {

        // 一、自己的，以扫描t为主
//        int pos = 0;
//        for (Character c: t.toCharArray()) {
//            if (pos == s.length())
//                break;
//            if (s.charAt(pos) == c)
//                pos++;
//        }
//        return pos >= s.length();

        // 二、以扫描s为主
        int pos = 0;
        for (char ch: s.toCharArray()) {
            while (pos < t.length() && t.charAt(pos) != ch)
                pos++;
            pos++;
        }
        return pos <= t.length();
    }
}
