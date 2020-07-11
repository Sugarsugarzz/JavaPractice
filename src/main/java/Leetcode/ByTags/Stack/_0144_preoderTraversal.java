package Leetcode.ByTags.Stack;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 * Medium
 */

public class _0144_preoderTraversal {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> res = preorderTraversal(root);
        System.out.println(res);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {

        // 迭代法
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            root = root.right;
        }
        return res;
    }
}
