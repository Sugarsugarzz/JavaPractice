package Leetcode.ByTags.Greedy;

import java.util.Arrays;

/**
 * 455. 分发饼干
 * Easy
 */

public class _0455_findContentChildren {

    public static void main(String[] args) {

        int[] g = {10, 9, 8, 7};
        int[] s = {5, 6, 7, 8};

        int res = findContentChildren(g, s);
        System.out.println(res);
    }

    public static int findContentChildren(int[] g, int[] s) {

        // 一、贪心
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0, si = 0;
        while (gi < g.length && si < s.length) {
            if (g[gi] <= s[si])
                gi++;
            si++;
        }
        return gi;
    }
}
