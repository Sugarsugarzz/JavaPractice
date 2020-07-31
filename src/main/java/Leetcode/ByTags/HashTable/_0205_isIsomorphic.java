package Leetcode.ByTags.HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * Easy
 */

public class _0205_isIsomorphic {

    public static void main(String[] args) {

        String s = "aa";
        String t = "ab";
        boolean res = isIsomorphic(s, t);
        System.out.println(res);
    }

    public static boolean isIsomorphic(String s, String t) {

        // 需要验证两个方向
//        return helper(s, t) && helper(t, s);
//        return helper(s).equals(helper(t));

        // 五、用连个map记录当前字母映射到哪个数字上，发现映射不一致立即返回false
        int[] mapS = new int[128];
        int[] mapT = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            // 看同一字母的映射是否相同
            if (mapS[c1] != mapT[c2]) {
                return false;
            } else {
                // 如果已经修改过，就不需要再处理了
                if (mapS[c1] == 0) {
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
            }
        }
        return true;
    }

//    public static boolean helper(String s, String t) {
//        // 一、对每个字母构建映射关系，然后根据映射关系构建字符串，与另一个字符串进行比较
//        Map<Character, Character> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++)
//            map.put(s.charAt(i), t.charAt(i));
//
//        StringBuffer sb = new StringBuffer();
//        for (char c: s.toCharArray())
//            sb.append(map.get(c));
//
//        return sb.toString().equals(t);
//    }

//    public static boolean helper(String s, String t) {
//        // 二、对每个字母构建映射关系，如果出现映射关系与之前不同的，说明不是同构结构
//        Map<Character, Character> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c1 = s.charAt(i);
//            char c2 = t.charAt(i);
//            if (map.containsKey(c1)) {
//                if (map.get(c1) != c2)
//                    return false;
//            } else {
//                map.put(c1, c2);
//            }
//        }
//        return true;
//    }

//    public static String helper(String s) {
//        // 三、利用位置信息，将每个字母映射成数字，组成一个新的数字字符串
//        StringBuffer sb = new StringBuffer();
//        Map<Character, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (!map.containsKey(c)) {
//                map.put(c, i);
//            }
//            sb.append(map.get(c));
//        }
//        return sb.toString();
//    }

    public static String helper(String s) {
        // 四、利用位置信息，将每个字母映射成数字，组成一个新的数字字符串（数组实现map）
        StringBuffer sb = new StringBuffer();
        int[] map = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c] == 0) {
                map[c] = i + 1;
            }
            sb.append(map[c]);
        }
        return sb.toString();
    }
}
