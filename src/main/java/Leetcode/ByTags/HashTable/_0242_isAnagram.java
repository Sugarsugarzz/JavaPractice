package Leetcode.ByTags.HashTable;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * Easy
 */

public class _0242_isAnagram {

    public static void main(String[] args) {

        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        // 一、字符数组排序并比较
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);

        return Arrays.equals(ss, tt);

        // 二、利用Map
//        int[] alpha = new int[26];
//        for (int i = 0; i < s.length(); i++ ) {
//            alpha[s.charAt(i) - 'a'] ++;
//            alpha[t.charAt(i) - 'a'] --;
//        }
//
//        for (int i : alpha) {
//            if (i != 0)
//                return false;
//        }
//        return true;
    }

}
