package Leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * title: 最小栈
 * 
 * description：设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。 push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。 top() -- 获取栈顶元素。 getMin() -- 检索栈中的最小元素。
 * 
 * limit:
 * 
 * label：栈
 * 
 */

public class _0155_minStack {

	public static void main(String[] args) {
		// 测试
		MinStack minStack = new MinStack();
		minStack.push(-1);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack);
		System.err.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.getMin());
		
	}
}

// 借助辅助栈。 数据栈和辅助栈同步加入数据。
class MinStack {
	
	private Stack<Integer> stack;
	private Stack<Integer> helper;
	
	public MinStack() {
		stack = new Stack<>();
		helper = new Stack<>();
	}

	public void push(int x) {
		stack.push(x);
		if (helper.isEmpty() || helper.peek() > x) {
			helper.push(x);
		} else {
			helper.push(helper.peek());
		}
	}

	public void pop() {
		if (!stack.isEmpty()) {
			stack.pop();
			helper.pop();
		}
	}

	public int top() {
		if (!stack.isEmpty()) {
			return stack.peek();
		} else {
			return -1;
		}
	}

	public int getMin() {
		if (!helper.isEmpty()) {
			return helper.peek();
		} else {
			return -1;
		}
	}

}
