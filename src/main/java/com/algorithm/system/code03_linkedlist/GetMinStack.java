package com.algorithm.system.code03_linkedlist;

import java.util.Stack;

/**
 * @author miclefengzss
 * 2022/3/28 下午1:55
 */
public class GetMinStack {

    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int v) {
            if (stackMin.isEmpty()) {
                stackMin.push(v);
            } else if (v <= getMin()) {
                stackMin.push(v);
            }
            stackData.push(v);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int v = stackData.pop();
            if (v == getMin()) {
                stackMin.pop();
            }
            return v;
        }

        public int peek() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackData.peek();
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());
    }
}
