package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * Medium
 */

public class _0437_pathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        System.out.println(pathSum(root, 8));
    }

    static int ans = 0;

    public static int pathSum(TreeNode root, int sum) {
        if (root == null)  return 0;
        // 一、双重递归，将每个节点都视为根节点处理  29ms
//        dfs(root, sum);
//        pathSum(root.left, sum);
//        pathSum(root.right, sum);
//        return ans;

        // 二、递归+回溯+前缀和  3ms
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return recursion(root, sum, prefixSumMap, 0);
    }

    public static void dfs(TreeNode root, int sum) {
        if (root == null)  return;
        if (sum == root.val)  ans++;
        dfs(root.left, sum - root.val);
        dfs(root.right, sum - root.val);
    }

    public static int recursion(TreeNode root, int sum, Map<Integer, Integer> prefixSumMap, int currSum) {

        if (root == null)  return 0;

        int res = 0;
        currSum += root.val;

        res += prefixSumMap.getOrDefault(currSum - sum, 0);
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        // 进入下一层
        res += recursion(root.left, sum, prefixSumMap, currSum);
        res += recursion(root.right, sum, prefixSumMap, currSum);

        // 回溯，清除本层的状态
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);
        return res;
    }
}
