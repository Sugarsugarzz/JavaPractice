package Leetcode.ByTags.HashTable;

import java.util.*;

/**
 * 49. 字母异位词分组
 * Medium
 */

public class _0049_groupAnagrams {

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        for (List<String> groupAnagram : groupAnagrams(strs)) {
            for (String s : groupAnagram) {
                System.out.print(s + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        // 一、对字符进行排序，构成字符串，进行比较  24ms
//        Map<String, List<String>> map = new HashMap<>();
//        for (String str : strs) {
//            char[] chars = str.toCharArray();
//            Arrays.sort(chars);
//            String key = String.valueOf(chars);
//            if (!map.containsKey(key)) {
//                map.put(key, new ArrayList<>());
//            }
//            map.get(key).add(str);
//
//        }
//        return new ArrayList<>(map.values());

        // 二、计数统计，对每个字符出现的次数进行统计  25ms
        // 当数量大数据长度很多时，方案二优于方案一
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];

        for (String s : strs) {
            Arrays.fill(count, 0);
            char[] cs = s.toCharArray();
            for (char c : cs) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i : count) {
                sb.append("#");
                sb.append(i);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }


}
