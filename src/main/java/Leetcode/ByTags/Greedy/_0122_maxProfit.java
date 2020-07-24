package Leetcode.ByTags.Greedy;

public class _0122_maxProfit {

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};
        int res = maxProfit(prices);
        System.out.println(res);
    }

    public static int maxProfit(int[] prices) {

        // 一、贪心算法（局部最优选择）
        // 贪心的地方，在于 今日股价 - 昨日股价，贪心决策是只加正数。
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0)
                res += diff;
        }
        return res;
    }
}
