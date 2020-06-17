package Leetcode.ByTags.String;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * Medium
 */

public class _0003_lengthOfLongestSubstring {

    public static void main(String[] args) {

        String s = "abba";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public static int lengthOfLongestSubstring(String s) {

        // 双指针 复杂度O^2
        if (s.length() == 1)
            return 1;

        // 前面指针不变，后面的挨个遍历后半个字符串。
        // 将后面指针指的数和前半段中的每个字符挨个比较，相同的话，则前指针pre指向相同字符的下一个字符，组成新的不重复子字符串
        int pre = 0, count = 0;
        for (int i = 1; i < s.length(); i++) {
            // p用来遍历子字符串每个字符，和i指的作比较
            int p = pre;
            while (p < i) {
                if (s.charAt(p) == s.charAt(i)) {
                    pre = p + 1;
                    break;
                }
                p++;
            }
            // while结束后，前后指针中间的字符串，必然是一个无重复字符的字符串
            int len = i - pre + 1;
            if (len > count)
                count = len;
        }

        return count;

        // 滑动窗口（哈希）
//        if (s.length() == 0)
//            return 0;
//        // abba
//        HashMap<Character, Integer> map = new HashMap<>();
//        int max = 0;
//        int left = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (map.containsKey(s.charAt(i))) {
//                left = Math.max(map.get(s.charAt(i)) + 1, left);
//            }
//            map.put(s.charAt(i), i);
//            max = Math.max(max, i - left + 1);
//            System.out.println(max);
//        }
//        return max;
    }
}
