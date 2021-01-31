package Leetcode.AimOffer;

import java.util.Stack;

/**
 *  09. 用两个栈实现队列
 *  Easy
 */

public class _09_CQueue {

    public static void main(String[] args) {
        _09_CQueue obj = new _09_CQueue();
        obj.appendTail(3);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
    }

    // 一、Stack
//    Stack<Integer> in_stack;
//    Stack<Integer> out_stack;
//
//    public _09_CQueue() {
//        in_stack = new Stack<>();
//        out_stack = new Stack<>();
//    }
//
//    public void appendTail(int value) {
//        in_stack.push(value);
//    }
//
//    public int deleteHead() {
//        if (out_stack.isEmpty()) {
//            while (!in_stack.isEmpty()) {
//                out_stack.push(in_stack.pop());
//            }
//            if (out_stack.isEmpty())
//                return -1;
//        }
//        return out_stack.pop();
//    }

    // 二、数组实现Stack
    int[] s1, s2;
    int i1, i2;

    public _09_CQueue() {
        s1 = new int[10000];
        s2 = new int[10000];
        s2[0] = -1;
        i1 = 0;
        i2 = 0;
    }

    public void appendTail(int value) {
        s1[i1++] = value;
    }

    public int deleteHead() {
        if (i2 <= 0) {
            i2 = 0;
            while (i1 > 0) {
                s2[++i2] = s1[--i1];
            }
        }
        return s2[i2--];
    }


}
