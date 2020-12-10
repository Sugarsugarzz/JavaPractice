package Leetcode.ByTags.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236. 二叉树的最近公共祖先
 * Medium
 */

public class _0236_lowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode node = lowestCommonAncestor(root, root.left, root.right);
        System.out.println(node.val);
    }

    // 递归
    static TreeNode ans;
    // 存储
    static  Map<Integer, TreeNode> parentMap = new HashMap<>();
    static  Set<Integer> visited = new HashSet<>();

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 一、递归法
//        dfs(root, p, q);
//        return ans;

        // 二、存储父子节点关系
        // 题目中提到，每个节点的值是唯一的
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parentMap.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parentMap.get(q.val);
        }
        return null;
    }

    // 存储父子节点关系
    public static void dfs(TreeNode root) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfs(root.right);
        }
    }

    // 递归：判断左右子树或当期节点是否包含或是目标节点
    public static boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return rson || lson || root.val == q.val || root.val == p.val;
    }
}
