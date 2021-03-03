package Leetcode.ByTags.Stack;

/**
 * 946. 验证栈序列
 * Medium
 */

public class _0946_validateStackSequences {
    public static void main(String[] args) {

    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int[] stack = new int[1000];
        int popIndex = 0, index = -1;
        for (int i = 0; i < pushed.length; i++) {
            stack[++index] = pushed[i];
            while (index != -1 && stack[index] == popped[popIndex]) {
                index--;
                popIndex++;
            }
        }
        return index == -1;
    }
}
