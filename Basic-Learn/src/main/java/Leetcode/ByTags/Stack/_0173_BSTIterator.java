package Leetcode.ByTags.Stack;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 173. 二叉搜索树迭代器
 * Medium
 */

public class _0173_BSTIterator {

    ArrayList<Integer> nodes;
    int index;

    public _0173_BSTIterator(TreeNode root) {

        // 递归遍历
        nodes = new ArrayList<>();
        index = -1;
        _inorder(root);
    }

    private void _inorder(TreeNode root) {

        if (root == null)
            return;
        _inorder(root.left);
        nodes.add(root.val);
        _inorder(root.right);
    }

    public int next() {
        return nodes.get(++index);
    }

    public boolean hasNext() {
        return index + 1 < nodes.size();
    }
}

//public class _0173_BSTIterator {
//
//    LinkedList<Integer> queue = new LinkedList<>();
//
//    public _0173_BSTIterator(TreeNode root) {
//
//        // 二叉搜索树，中序遍历得到的就是个升序数列
//        // 自己的方法，非递归遍历
//        Stack<TreeNode> stack_helper = new Stack<>();
//
//        while (root != null || !stack_helper.isEmpty()) {
//            while (root != null) {
//                stack_helper.push(root);
//                root = root.left;
//            }
//            root = stack_helper.pop();
//            queue.add(root.val);
//            root = root.right;
//        }
//    }
//
//    public int next() {
//        return queue.pop();
//    }
//
//    public boolean hasNext() {
//        return !queue.isEmpty();
//    }
//}

class Test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        _0173_BSTIterator obj = new _0173_BSTIterator(root);
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
    }
}
