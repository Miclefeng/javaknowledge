package com.algorithm.system.code03_linkedlist;

import java.util.Stack;

/**
 * @author miclefengzss
 * 2022/3/28 下午2:13
 */
public class TwoStacksImplementQueue {

    public static class TwoStacksQueue<T> {

        private Stack<T> pushStack;

        private Stack<T> popStack;

        public TwoStacksQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void push(T v) {
            this.pushStack.push(v);
        }

        public T poll() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            if (popStack.isEmpty()) {
                move();
            }
            return popStack.pop();
        }

        public T peek() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            if (popStack.isEmpty()) {
                move();
            }
            return popStack.peek();
        }

        public void move() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue<Integer> test = new TwoStacksQueue<>();
        for (int i = 0; i < 10; i++) {
            test.push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(test.poll() + " ");
        }
        System.out.println();
        for (int i = 10; i < 15; i++) {
            test.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(test.poll() + " ");
        }
        System.out.println();
    }
}
