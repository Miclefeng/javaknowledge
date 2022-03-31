package com.algorithm.system.code03_linkedlist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author miclefengzss
 * 2022/3/28 下午2:26
 */
public class TwoQueuesImplementStack {

    public static class TwoQueueStack<T> {

        private Queue<T> queue;

        private Queue<T> help;

        public TwoQueueStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(T v) {
            queue.offer(v);
        }

        public T poll() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Queue is empty.");
            }
            move();
            T v = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return v;
        }

        public T peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Queue is empty.");
            }
            move();
            T v = queue.poll();
            help.offer(v);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return v;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void move() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");
    }
}
