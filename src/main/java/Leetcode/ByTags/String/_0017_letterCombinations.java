package Leetcode.ByTags.String;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * Medium
 */

public class _0017_letterCombinations {

    static HashMap<String, String> map = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    static List<String> result = new ArrayList<String>();

    public static void main(String[] args) {

        String digits = "23";
        List<String> result = letterCombination(digits);
        for (String s: result) {
            System.out.println(s);
        }
    }

    public static void backtrack(String combination, String next_digits) {

        if (next_digits.length() == 0) {
            result.add(combination);
        } else {
            String digit = next_digits.substring(0, 1);
            String letters = map.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = map.get(digit).substring(i, i+1);
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public static List<String> letterCombination(String digits) {

        // 回溯法
        if (digits.length() != 0) {
            backtrack("", digits);
        }
        return result;
    }
}
