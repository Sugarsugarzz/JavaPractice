package Leetcode.ByTags.Greedy;

/**
 * 1005. K次取反后最大化的数组和
 * Easy
 */

public class _1005_largestSumAfterKNegations {

    public static void main(String[] args) {

        int[] A = {3, -1, 0, 2};
        int res = largestSumAfterKNegations(A, 3);
        System.out.println(res);
    }

    public static int largestSumAfterKNegations(int[] A, int K) {

        int[] number  = new int[201];  // -100 <= A[i] <= 100，范围的大小为201
        for (int t: A)
            number[t + 100]++;  // 将[-100, 100]范围的数映射到[0, 200]范围上
        int i = 0;
        // 每次将最小的数取反，最后求和
        // i始终指向最小的数
        while (K > 0) {
            // 找到 A 中最小的数
            while (number[i] == 0)
                i++;
            number[i]--;
            number[200 - i]++;
            if (i > 100)
                i = 200 - i;
            K--;
        }
        // 遍历number求和
        int sum = 0;
        for (int j = i; j < number.length; j++) {
            sum += (j - 100) * number[j];  // j-100是数字大小，number[j]是该数字出现的次数
            System.out.println(j - 100);
        }

        return sum;
    }
}
