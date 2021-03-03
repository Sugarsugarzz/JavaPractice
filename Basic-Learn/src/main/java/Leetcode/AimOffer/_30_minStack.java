package Leetcode.AimOffer;

/**
 * 30. 包含min函数的栈
 * Easy
 */
public class _30_minStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
    }
}

class MinStack {

    int[] stack, minStack;
    int index = -1, minIndex = -1;

    public MinStack () {
        stack = new int[20000];
        minStack = new int[20000];
    }

    public void push(int x) {
        stack[++index] = x;
        if (minIndex == -1 || x <= minStack[minIndex]) {
            minStack[++minIndex] = x;
        }
    }

    public void pop() {
        if (minStack[minIndex] == stack[index]) {
            minIndex--;
        }
        index--;
    }

    public int top() {
        return stack[index];
    }

    public int min() {
        return minStack[minIndex];
    }

}
