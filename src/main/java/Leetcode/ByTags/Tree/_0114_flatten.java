package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 114. 二叉树展开为链表
 * Medium
 */

public class _0114_flatten {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten(root);

        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }

    public static void flatten(TreeNode root) {


        // 一、利用额外空间
        // 先序遍历，存入额外空间，然后构造单链表
//        if (root == null) return;
//        List<Integer> list = new ArrayList<>(preorderTraversal(root));
//        root.left = null;
//        for (int i = 1; i < list.size(); i++) {
//            root.right = new TreeNode(list.get(i));
//            root = root.right;
//        }

        // 二、原地实现
        // 重复 将左子树插入到右子树，更新新的右子树根节点 这一过程
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode temp = root.left;
                while (temp.right != null)  temp = temp.right;
                temp.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

    // 先序遍历
    private static List<Integer> preorderTraversal(TreeNode root) {
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
