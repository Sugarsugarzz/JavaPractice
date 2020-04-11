package Leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * 
 * title: 路径总和
 * 
 * description：给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 
 * limit:  。
 * 
 * label：树
 * 
 */

public class _0112_hasPathSum {

	public static void main(String[] args) {
		// 测试
		TreeNode root = new TreeNode(5);
		TreeNode l1 = new TreeNode(1);
		TreeNode r1 = new TreeNode(1);
		TreeNode l2 = new TreeNode(1);
		TreeNode l3 = new TreeNode(1);
		
		root.left = l1;
		root.right = r1;
		l1.left = l2;
		l2.left = l3;
		
		int sum = 6;
		boolean result = hasPathSum(root, sum);
		System.out.println(result);
		
	}

	// 从根节点开始，每当遇到一个节点的时候，从目标值里扣除节点值，一直到叶子节点判断目标值是不是被扣完。
	public static boolean hasPathSum(TreeNode root, int sum) {
		
		// 节点为空
		if (root == null) return false;
		// 叶子节点，减去目标值，如果是0，表示这条路径是目标路径
		if (root.left == null && root.right == null) return root.val - sum == 0;
		// 有子节点的情况，减去当前节点的值，继续向下递归
		return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
	}
}
