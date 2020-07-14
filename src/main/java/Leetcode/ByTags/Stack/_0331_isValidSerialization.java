package Leetcode.ByTags.Stack;

/**
 * 331. 验证二叉树的前序序列化
 * Medium
 */

public class _0331_isValidSerialization {

    public static void main(String[] args) {

        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean res = isValidSerialization(preorder);
        System.out.println(res);
    }

    public static boolean isValidSerialization(String preorder) {

        // 槽位思想
        // 初始时，只有一个槽位；
        // #和数字都会消耗一个槽位，但是数字会衍生创建出另外两个槽位。
        int slots = 1;
        for (String node: preorder.split(",")) {
            slots--;
            if (slots < 0)
                return false;
            if (!node.equals("#"))
                slots += 2;
        }

        return slots == 0;
    }
}
