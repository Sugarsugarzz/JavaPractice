package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. 二叉树的层次遍历 II
 * Easy
 */

public class _0107_levelOrderBottom {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> res = levelOrderBottom(root);
        System.out.println(res);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {

        // 迭代，层次遍历
        List<List<Integer>> res = new ArrayList<>();

        if (root == null)
            return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            // 每次需要把队列清空，即为一层的所有节点
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.removeFirst();
                tmp.add(node.val);
                // 补充左右子树
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(0, tmp);
        }

        return res;
    }
}
