package Leetcode.ByTags.Tree;

/**
 * 543. 二叉树的直径
 * Easy
 */

public class _0543_diameterOfBinaryTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(diameterOfBinaryTree(root));
    }

    public static int ans;

    public static int diameterOfBinaryTree(TreeNode root) {

        // 二叉树的直径：任意两个结点路径长度中的最大值，这条路径可能穿过也可能不穿过根结点。
        // 路径长度以边的数目表示

        if (root == null)  return 0;

        ans = 1;
        getDepth(root);
        return ans - 1;
    }

    public static int getDepth(TreeNode node) {
        if (node == null)  return 0;
        int l = getDepth(node.left);
        int r = getDepth(node.right);
        int len = l + r + 1;
        ans = Math.max(ans, len);
        return Math.max(l, r) + 1;
    }
}
