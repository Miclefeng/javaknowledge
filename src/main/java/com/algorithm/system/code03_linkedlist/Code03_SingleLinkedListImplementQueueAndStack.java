package com.algorithm.system.code03_linkedlist;

import com.javase.thread.oldbase.juc.threadlocal.M;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/21 16:52
 */
public class Code03_SingleLinkedListImplementQueueAndStack {

    public static class Node<E> {
        public Node<E> next;
        public E value;

        public Node(E e) {
            value = e;
        }
    }

    public static class MyQueue<E> {
        private Node<E> head;
        private Node<E> tail;

        public int size;

        public MyQueue() {
            this.size = 0;
            this.head = this.tail = null;
        }

        public int getSize() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public E peek() {
            E ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }

        public void offer(E e) {
            Node<E> n = new Node<>(e);
            if (tail == null) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
            size++;
        }

        public E poll() {
            E ans = null;
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
    }

    public static void testMyQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        LinkedList<Integer> testQueue = new LinkedList<>();
        int maxValue = 300;
        int testTimes = 1000000;
        System.out.println("Queue test start...");
        for (int i = 0; i < testTimes; i++) {
            if (myQueue.isEmpty() != testQueue.isEmpty()) {
                System.out.println("Queue Error.");
                break;
            }
            if (myQueue.getSize() != testQueue.size()) {
                System.out.println("Queue Error.");
                break;
            }
            double divisor = Math.random();
            if (divisor < 0.33) {
                int value = (int) (Math.random() * maxValue);
                myQueue.offer(value);
                testQueue.offer(value);
            } else if (divisor < 0.66) {
                if (!myQueue.isEmpty()) {
                    Integer poll = myQueue.poll();
                    Integer poll1 = testQueue.poll();
                    if (!poll1.equals(poll)) {
                        System.out.println("Queue Error.");
                        break;
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    Integer peek = myQueue.peek();
                    Integer peek1 = testQueue.peek();
                    if (!peek.equals(peek1)) {
                        System.out.println("Queue Error.");
                        break;
                    }
                }
            }
        }

        if (myQueue.getSize() != testQueue.size()) {
            System.out.println("Queue Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = testQueue.poll();
            if (num1 != num2) {
                System.out.println("Queue Oops!");
                break;
            }
        }
        System.out.println("Queue test end...");
    }

    public static class MyStack<E> {
        private Node<E> head;
        private int size;

        public MyStack() {
            this.size = 0;
            this.head = null;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public E peek() {
            E ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }

        public void push(E e) {
            Node<E> n = new Node<>(e);
            if (head == null) {
                head = n;
            } else {
                n.next = head;
                head = n;
            }
            size++;
        }

        public E pop() {
            E ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
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
        testMyQueue();
        System.out.println("=======================");
        testMyStack();
    }
}
