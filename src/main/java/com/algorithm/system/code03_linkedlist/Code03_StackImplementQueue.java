package com.algorithm.system.code03_linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/22 17:25
 */
public class Code03_StackImplementQueue {

    public static class MyQueue<E> {
        private Stack<E> stackPush;
        private Stack<E> stackPop;
        private int size;

        public MyQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
            this.size = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(E e) {
            stackPush.push(e);
            pushToPop();
            size++;
        }

        public E poll() {
            if (isEmpty()) {
                return null;
            }
            pushToPop();
            size--;
            return stackPop.pop();
        }

        public E peek() {
            if (isEmpty()) {
                return null;
            }
            pushToPop();
            return stackPop.peek();
        }

        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
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
                System.out.println("Queue Error 1.");
                break;
            }
            if (myQueue.getSize() != testQueue.size()) {
                System.out.println("Queue Error 2.");
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
                        System.out.println("Queue Error 3.");
                        break;
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    Integer peek = myQueue.peek();
                    Integer peek1 = testQueue.peek();
                    if (!peek.equals(peek1)) {
                        System.out.println("Queue Error 4.");
                        break;
                    }
                }
            }
        }

        if (myQueue.getSize() != testQueue.size()) {
            System.out.println("Queue Oops 5!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = testQueue.poll();
            if (num1 != num2) {
                System.out.println("Queue Oops 6!");
                break;
            }
        }
        System.out.println("Queue test end...");
    }

    public static void printMyQueue(Stack<Integer> myQueue) {
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.pop() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        testMyQueue();
//        MyQueue<Integer> queue = new MyQueue<>();
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        queue.offer(4);
//        queue.offer(5);
//
//        System.out.println("queue.poll() = " + queue.poll());
//        System.out.println("queue.poll() = " + queue.poll());
//        System.out.println("queue.poll() = " + queue.poll());
//        System.out.println("queue.poll() = " + queue.poll());
//        System.out.println("queue.poll() = " + queue.poll());
//        System.out.println("queue.poll() = " + queue.poll());

    }
}
