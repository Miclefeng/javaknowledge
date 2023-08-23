package com.algorithm.system.code03_linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/22 17:25
 */
public class Code03_QueueImplementStack {

    public static class MyStack<E> {
        private LinkedList<E> queue;
        private LinkedList<E> help;
        private int size;

        public MyStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
            this.size = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(E e) {
            queue.offer(e);
            size++;
        }

        public E pop() {
            E ans = null;
            if (!isEmpty()) {
                transformQueue();
                ans = queue.poll();
                swapQueue();
                size--;
            }
            return ans;
        }

        public E peek() {
            E ans = null;
            if (!isEmpty()) {
                transformQueue();
                ans = queue.peek();
            }
            return ans;
        }

        private void transformQueue() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
        }

        private void swapQueue() {
            LinkedList<E> tmp = queue;
            queue = help;
            help = tmp;
        }
    }

    public static void testMyStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> testStack = new Stack<>();
        int maxValue = 300;
        int testTimes = 1000000;
        System.out.println("Stack test start...");
        for (int i = 0; i < testTimes; i++) {
            if (myStack.isEmpty() != testStack.isEmpty()) {
                System.out.println("stack error.");
                break;
            }
            if (myStack.getSize() != testStack.size()) {
                System.out.println("stack error.");
                break;
            }
            double divisor = Math.random();
            if (divisor < 0.33) {
                int v = (int) (Math.random() * maxValue);
                myStack.push(v);
                testStack.push(v);
                if (!myStack.peek().equals(testStack.peek())) {
                    System.out.println("stack error.");
                    break;
                }
            } else if (divisor < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = testStack.pop();
                    if (num1 != num2) {
                        System.out.println("stack error.");
                        break;
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = testStack.peek();
                    if (num1 != num2) {
                        System.out.println("stack error.");
                        break;
                    }
                }
            }
        }

        if (myStack.getSize() != testStack.size()) {
            System.out.println("stack error.");
        }

        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = testStack.pop();
            if (num1 != num2) {
                System.out.println("stack error.");
                break;
            }
        }

        System.out.println("Stack test end...");
    }

    public static void main(String[] args) {
        testMyStack();
    }
}
