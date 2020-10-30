package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * Medium
 */

public class _0105_buildTree {

    public static void main(String[] args) {

        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root.val);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0 || inorder.length == 0)
            return null;

        // 根据前序数组的第一个元素，确定根节点
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                int[] pre_left = Arrays.copyOfRange(preorder, 1, i+1);
                int[] pre_right = Arrays.copyOfRange(preorder, i+1, preorder.length);
                int[] in_left = Arrays.copyOfRange(inorder, 0, i);
                int[] in_right = Arrays.copyOfRange(inorder, i+1, inorder.length);
                root.left = buildTree(pre_left, in_left);
                root.right = buildTree(pre_right, in_right);
                break;
            }
        }
        return root;
    }
}
