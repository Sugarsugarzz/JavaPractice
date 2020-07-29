package Leetcode.ByTags.Greedy;

/**
 * 134. 加油站
 * Medium
 */

public class _0134_canCompleteCircuit {

    public static void main(String[] args) {

        int[] gas = {3, 3, 4};
        int[] cost = {3, 4, 4};

        int res = canCompleteCircuit(gas, cost);
        System.out.println(res);
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        // 一、最后一个测试用例未通过，输出超出限制
        // 以每个加油站作为出发站，并检查每个加油站剩余汽油量
        // Time Complexity O(n^2)
//        int n = gas.length;
//        for (int i = 0; i < n; i++) {
//            // i 为起始位置
//            if (gas[i] >= cost[i]) {
//                int j = i;
//                int gas_hold = 0;
//                do {
//                    gas_hold += gas[j] - cost[j];
//                    if (gas_hold < 0)
//                        break;
//                    System.out.println(i + " - " + j + " - " + gas_hold);
//                    j = (++j) % n;
//                } while (j != i);
//                // 成功回到起始位置的
//                if (j == i)
//                    return i;
//            }
//        }
//        return -1;

        // 二、一次遍历
        // Time Complexity O(n)
        int n = gas.length;

        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; i++) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            if (curr_tank < 0) {
                starting_station = i + 1;
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }
}
