package Leetcode.ByTags.Array;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * 加一
 * Easy
 */

public class _0066_plusOne {

    public static void main(String[] args) {

        int[] digits = {9};
        int[] result = plusOne(digits);
        System.out.println(Arrays.toString(result));
    }

    public static int[] plusOne(int[] digits) {

        for (int i=digits.length-1; i>=0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0)
                return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
