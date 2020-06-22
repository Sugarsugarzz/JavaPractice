package Leetcode.ByTags.Tree;

/**
 * 100. 相同的树
 * Easy
 */

public class _0100_isSameTree {

    public static void main(String[] args) {

        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        boolean res = isSameTree(p, q);
        System.out.println(res);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        // 深度优先搜索，递归
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        // 左右子树也相同
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);

        // 另外一种是 广度优先搜索，非递归，用队列实现
    }
}