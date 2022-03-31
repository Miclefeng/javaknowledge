package com.algorithm.system.code03_linkedlist;

/**
 * @author miclefengzss
 * 2022/3/28 上午10:52
 */
public class ArrayToStackAndRingArrayToQueue {

    public static class MyStack {
        private int[] arr;
        private int index;
        private int size;
        private int limit;

        public MyStack(int limit) {
            this.arr = new int[limit];
            this.index = -1;
            this.size = 0;
            this.limit = limit;
        }

        public void push(int v) {
            if (size == limit) {
                throw new RuntimeException("stack ss full.");
            }

            arr[index + 1] = v;
            index++;
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }

            int v = arr[index];
            index--;
            size--;
            return v;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }

            return arr[index];
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class MyQueue {
        private int[] arr;
        private int pushIndex;
        private int pollIndex;
        private int size;
        private int limit;

        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.pushIndex = 0;
            this.pollIndex = 0;
            this.size = 0;
            this.limit = limit;
        }

        public void push(int v) {
            if (size == limit) {
                throw new RuntimeException("queue is full.");
            }
            arr[pushIndex] = v;
            pushIndex = nextIndex(pushIndex);
            size++;
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("queue is empty.");
            }
            int v = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            size--;
            return v;
        }

        public int nextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(10);
        for (int i = 0; i < 10; i++) {
            myStack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(myStack.pop() + " ");
        }
        System.out.println();
        System.out.println("===================");
        MyQueue myQueue = new MyQueue(10);
        for (int i = 0; i < 10; i++) {
            myQueue.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(myQueue.poll() + " ");
        }
    }
}
