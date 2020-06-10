package Leetcode.ByTags.String;

import java.util.HashMap;

/**
 * 13.罗马数字转整数
 * Easy
 */

public class _0013_romanToInt {

    public static void main(String[] args) {

        String s = "MCMXCIV";
        int result = romanToInt(s);
        System.out.println(result);
    }

    public static int romanToInt(String s) {

        // 哈希法，比switch慢好多...
//        HashMap<Character, Integer> map = new HashMap<>();
//        map.put('I', 1);
//        map.put('V', 5);
//        map.put('X', 10);
//        map.put('L', 50);
//        map.put('C', 100);
//        map.put('D', 500);
//        map.put('M', 1000);
//        HashMap<String, Integer> map2 = new HashMap<>();
//        map2.put("IV", 4);
//        map2.put("IX", 9);
//        map2.put("XL", 40);
//        map2.put("XC", 90);
//        map2.put("CD", 400);
//        map2.put("CM", 900);
//
//        int result = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (i < s.length() - 1 && map2.containsKey(s.substring(i, i+2))) {
//                result += map2.get(s.substring(i, i+2));
//                i++;
//                continue;
//            }
//            if (map.containsKey(s.charAt(i)))
//                result += map.get(s.charAt(i));
//
//        }
//        return result;

        // Switch法，比哈希快，小数据量的情况
        int result = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num)
                result -= preNum;
            else
                result += preNum;
            preNum = num;
        }
        result += preNum;
        return result;
    }

    private static int getValue(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
