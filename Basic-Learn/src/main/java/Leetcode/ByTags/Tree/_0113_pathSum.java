package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * Medium
 */

public class _0113_pathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(pathSum(root, 22));
    }

    static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {

        // 一、递归，不停创建list，效率低
//        dfs(root, sum, null);

        // 二、回溯，将使用过的值remove掉，防止分支污染
        dfs2(root, sum, new ArrayList<>());
        return ans;
    }

    public static void dfs(TreeNode root, int sum, List<Integer> nodes) {

        if (root == null)  return;
        List<Integer> list = nodes == null ? new ArrayList<>() : new ArrayList<>(nodes);
        list.add(root.val);

        if (root.left == null && root.right == null && sum == root.val) {
            ans.add(list);
            return;
        }
        dfs(root.left, sum - root.val, list);
        dfs(root.right, sum - root.val, list);
    }

    public static void dfs2(TreeNode root, int sum, List<Integer> list) {
        if (root == null)  return;

        list.add(root.val);

        if (root.left == null && root.right == null) {
            if (sum == root.val)
                ans.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }

        dfs2(root.left, sum - root.val, list);
        dfs2(root.right, sum - root.val, list);
        list.remove(list.size() - 1);

    }
}
