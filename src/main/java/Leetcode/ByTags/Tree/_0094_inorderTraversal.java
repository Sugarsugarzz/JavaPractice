package Leetcode.ByTags.Tree;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * Medium
 */

public class _0094_inorderTraversal {

    static List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> res = inorderTraversal(root);
        for (Integer n: res)
            System.out.print(n + "  ");
    }

    public static List<Integer> inorderTraversal(TreeNode root) {

        // 递归
//        helper(root);
//        return res;

        // 迭代（先序遍历）
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }

        return res;
    }

    public static void helper(TreeNode root) {

        if (root != null) {
            if (root.left != null) {
                helper(root.left);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right);
            }
        }
    }
}
