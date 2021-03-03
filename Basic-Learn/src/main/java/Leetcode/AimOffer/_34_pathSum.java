package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 34. 二叉树中和为某一值的路径
 * Medium
 */
public class _34_pathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        System.out.println(pathSum(root, 20));
    }

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {

        if (root == null)  return res;
        recur(root, sum, new ArrayList<>());
        return res;
    }

    public static void recur(TreeNode root, int sum, List<Integer> list) {
        if (root == null)
            return;

        list.add(root.val);
        if (sum - root.val == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(list));  // 这里注意需要新List
        }
        if (root.left != null)  recur(root.left, sum - root.val, list);
        if (root.right != null)  recur(root.right, sum - root.val, list);
        list.remove(list.size() - 1);  // 回溯需要移掉当前的val
    }
}
