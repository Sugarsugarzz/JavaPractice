package Leetcode.ByTags.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * Medium
 */

public class _0438_findAnagrams {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }

    public static List<Integer> findAnagrams(String s, String p) {

        // 一、滑动窗口
        List<Integer> ans = new ArrayList<>();
        if (s.length() == 0 || p.length() == 0)
            return ans;

        int[] dict = new int[26];
        for (int i = 0; i < p.length(); i++) {
            dict[p.charAt(i) - 'a']++;
        }

        int[] window = new int[26];
        int left = 0, right = 0;
        while (right < s.length()) {
            int curR = s.charAt(right) - 'a';
            window[curR]++;
            right++;
            while (window[curR] > dict[curR]) {
                int curL = s.charAt(left) - 'a';
                window[curL]--;
                left++;
            }
            if (right - left== p.length()) {
                ans.add(left);
            }
        }

        return ans;
    }


}
