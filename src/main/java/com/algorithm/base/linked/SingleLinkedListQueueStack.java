package com.algorithm.base.linked;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author miclefengzss
 * 2022/2/23 下午7:49
 */
public class SingleLinkedListQueueStack {

    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
            this.next = null;
        }
    }

    static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(V value) {
            Node<V> cur = new Node<>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            this.head = null;
            this.size = 0;
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> testQueue = new LinkedList<>();
        int maxValue = 2000000;
        int testTime = 1000000;
        System.out.println("Queue testing...");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != testQueue.isEmpty()) {
                System.out.println("Queue Error.");
            }
            if (myQueue.size() != testQueue.size()) {
                System.out.println("Queue Error.");
            }
            double divisor = Math.random();
            if (divisor < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                testQueue.offer(num);
            } else if (divisor < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = testQueue.poll();
                    if (num1 != num2) {
                        System.out.println("Queue Error.");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = testQueue.peek();
                    if (num1 != num2) {
                        System.out.println("Queue Error.");
                    }
                }
            }
//            System.out.print(testQueue.size() + "=" + myQueue.size() + ", ");
        }
        if (myQueue.size() != testQueue.size()) {
            System.out.println("Queue Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = testQueue.poll();
            if (num1 != num2) {
                System.out.println("Queue Oops!");
            }
        }
        System.out.println("Queue test end...");
    }


    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> testStack = new Stack<>();
        int maxValue = 2000000;
        int testTime = 1000000;
        System.out.println("Stack testing...");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != testStack.isEmpty()) {
                System.out.println("Stack Error.");
            }
            if (myStack.size() != testStack.size()) {
                System.out.println("Stack Error.");
            }
            double divisor = Math.random();
            if (divisor < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                testStack.push(num);
            } else if (divisor < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = testStack.pop();
                    if (num1 != num2) {
                        System.out.println("Stack Error.");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = testStack.peek();
                    if (num1 != num2) {
                        System.out.println("Queue Error.");
                    }
                }
            }
            System.out.print(testStack.size() + "=" + myStack.size() + ", ");
        }
        if (myStack.size() != testStack.size()) {
            System.out.println("Stack Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = testStack.pop();
            if (num1 != num2) {
                System.out.println("Stack Oops!");
            }
        }
        System.out.println("Stack test end...");
    }

    public static void main(String[] args) {
       testQueue();
       testStack();
    }
}
