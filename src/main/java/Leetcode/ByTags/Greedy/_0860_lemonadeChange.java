package Leetcode.ByTags.Greedy;

/**
 * 860. 柠檬水找零
 * Easy
 */

public class _0860_lemonadeChange {

    public static void main(String[] args) {

        int[] bills = {5, 5, 5, 10, 20};
        boolean res = lemonadeChange(bills);
        System.out.println(res);
    }

    public static boolean lemonadeChange(int[] bills) {

        // 1、找零 = 贪心
        int five = 0, ten = 0;
        for (int bill: bills) {
            switch (bill) {
                case 5:
                    five++;
                    break;
                case 10:
                    if (five < 1)
                        return false;
                    five--;
                    ten++;
                    break;
                case 20:
                    if (five > 0 && ten > 0) {
                        five--;
                        ten--;
                    }
                    else if (five > 2)
                        five -= 3;
                    else
                        return false;
                    break;
            }
        }
        return true;
    }

}
