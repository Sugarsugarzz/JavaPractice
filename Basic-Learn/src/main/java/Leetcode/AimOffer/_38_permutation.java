package Leetcode.AimOffer;

import java.util.*;

/**
 * 38. 字符串的排列
 * Medium
 */
public class _38_permutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abc")));
    }

    static List<String> list = new ArrayList<>();
    static char[] c;

    public static String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return list.toArray(new String[list.size()]);
    }

    public static void dfs(int x) {
        if (x == c.length - 1) {
            list.add(String.valueOf(c));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i]))  continue;
            set.add(c[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    public static void swap(int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}
