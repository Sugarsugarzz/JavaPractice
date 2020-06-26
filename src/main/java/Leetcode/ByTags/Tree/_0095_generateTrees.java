package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * Medium
 */

public class _0095_generateTrees {

    public static void main(String[] args) {

        int n = 3;
        List<TreeNode> res = generateTrees(n);
        System.out.println(res.size());
    }

    public static List<TreeNode> generateTrees(int n) {

        if (n == 0)
            return new ArrayList<>();
        // 将1到n中间每个数作为根节点，利用二叉查找树的性质，小的作为左子树，大的作为右子树，左右组合
        return getAns(1, n);
    }

    public static List<TreeNode> getAns(int start, int end) {

        List<TreeNode> res = new ArrayList<>();

        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = getAns(start, i - 1);
            List<TreeNode> rightTrees = getAns(i + 1, end);

            for (TreeNode leftTree: leftTrees) {
                for (TreeNode rightTree: rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
