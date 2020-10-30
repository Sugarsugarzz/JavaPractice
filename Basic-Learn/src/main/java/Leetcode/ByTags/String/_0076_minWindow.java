package Leetcode.ByTags.String;

/**
 * 76. 最小覆盖子串
 * Difficult
 */
public class _0076_minWindow {

    public static void main(String[] args) {

        String s = "AD0BEC0DEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {

        // 一、滑动窗口
        if (s == null || t == null || s.equals("") || t.equals("") || s.length() < t.length())
            return "";

        // 记录窗口字符串指定字符出现的次数，和目标字符串指定字符出现的次数
        int[] need = new int[128];
        int[] have = new int[128];

        // 统计目标字符串指定字符的出现次数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        // 初始化左右指针，最小长度（初始值为一定不可达到的长度）
        // 已有字符串中窗口字符串指定字符的出现总频次以及最小覆盖子串在原字符串中起始位置
        // [left, right)
        int left = 0, right = 0, min = s.length() + 1, count = 0, start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            /*
            如果该字符不被目标字符串需要，则有两种情况：
                1. 循环刚开始，可以直接移动右指针即可，不需要做多余判断
                2. 循环已经开始，又有两种情况：
                    2.1 上一次条件不满足，窗口字符串指定字符出现的次数不满足目标字符串指定字符出现次数，那么此时如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
                    2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
             */
            if (need[r] == 0) {
                right++;
                continue;
            }
            // 当前仅当窗口字符串目标字符出现次数小于目标字符串字符的出现次数时，count才会+1
            // 为了后续能直接判断窗口字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (have[r] < need[r]) {
                count++;
            }

            // 窗口字符串中目标字符出现的次数+1
            have[r]++;
            // 移动右指针
            right++;
            // 当且仅当窗口字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == t.length()) {
                // 当窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                // 如果左边即将去掉的字符不被目标字符串需要。则不需要多余判断，直接移动左指针
                if (need[l] == 0) {
                    left++;
                    continue;
                }
                // 如果左边即将去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么去掉该字符，
                // 就不再满足目标字符串的条件，此时破坏循环条件，跳出循环，使 count --
                if (have[l] == need[l]) {
                    count--;
                }
                // 窗口字符串中目标字符出现次数-1
                have[l]--;
                // 移动左指针
                left++;
            }
        }
        // 如果最小长度仍为初始值，说明没有符合条件的子串
        if (min == s.length() + 1)
            return "";

        // 返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);

    }
}
