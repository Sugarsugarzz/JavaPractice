package Leetcode.ByTags.Greedy;

/**
 * 944. 删列造序
 * Easy
 */

public class _0944_minDeletionSize {

    public static void main(String[] args) {

        String[] A = {"cba", "daf", "ghi"};
        int res = minDeletionSize(A);
        System.out.println(res);
    }

    public static int minDeletionSize(String[] A) {

        // 检查每一列是否有序，如果列中存在降序，则答案加一
        // 题目描述like shit，本质上是判断一个矩阵里，有多少列是降序排列的
        int res = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 0; j < A.length - 1; j++) {
                if (A[j].charAt(i) > A[j+1].charAt(i)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
