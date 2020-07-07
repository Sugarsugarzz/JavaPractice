package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * Medium
 */

public class _0098_isValidBST {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
//        root.right = new TreeNode(1);
        root.left = new TreeNode(1);

        boolean res = isValidBST(root);
        System.out.println(res);
    }

    public static boolean isValidBST(TreeNode root) {

        // 搜索二叉树，中序遍历得到的是一个升序数组
        Stack<TreeNode> stack = new Stack<>();
        long inorder = Long.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果当前节点的值小于等于前一个inorder，则不是二叉搜索树
            if (root.val <= inorder)
                return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
