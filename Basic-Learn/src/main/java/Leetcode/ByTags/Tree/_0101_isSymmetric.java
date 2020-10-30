package Leetcode.ByTags.Tree;

import java.util.LinkedList;

/**
 * 101. 对称二叉树
 * Easy
 */

public class _0101_isSymmetric {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        boolean res = isSymmetric(root);
        System.out.println(res);
    }

    public static boolean isSymmetric(TreeNode root) {

        // 广度优先搜索，迭代（队列实现）
        if (root == null || (root.left == null && root.right == null))
            return true;
        if (root.left == null || root.right == null)
            return false;
        // 队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            if (left == null && right == null)
                continue;
            if (left == null || right == null)
                return false;
            if (left.val != right.val)
                return false;

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;

        // 深度优先搜索，递归
//        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode p, TreeNode q) {

        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return (p.val == q.val) && isMirror(p.right, q.left) && isMirror(p.left, q.right);
    }
}
