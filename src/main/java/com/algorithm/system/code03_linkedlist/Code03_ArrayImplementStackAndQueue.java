package com.algorithm.system.code03_linkedlist;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/22 14:35
 */
public class Code03_ArrayImplementStackAndQueue {

    public static class ArrayStack {
        private int[] array;

        private int size;

        private int capacity;

        private int index;

        public ArrayStack(int capacity) {
            array = new int[capacity];
            size = index = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int e) {
            if (size == capacity) {
                throw new RuntimeException("stack is full.");
            }
            array[index++] = e;
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }
            int ans = array[index--];
            size--;
            return ans;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }
            return array[index];
        }
    }

    public static class ArrayQueue {
        public int[] array;

        private int size;

        private int capacity;

        private int head;

        private int tail;

        public ArrayQueue(int capacity) {
            array = new int[capacity];
            this.capacity = capacity;
            size = head = tail = 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int e) {
            if (size == capacity) {
                throw new RuntimeException("queue is full.");
            }
            array[tail] = e;
            tail = getNext(tail);
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("queue is empty.");
            }
            int ans = array[head];
            head = getNext(head);
            size--;
            return ans;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("queue is empty.");
            }
            return array[head];
        }

        private int getNext(int index) {
            return index < capacity - 1 ? index + 1 : 0;
        }
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(6);

        arrayQueue.push(1);
        printArray(arrayQueue.array);
        arrayQueue.push(2);
        printArray(arrayQueue.array);
        arrayQueue.push(3);
        printArray(arrayQueue.array);
        arrayQueue.push(4);
        printArray(arrayQueue.array);
        arrayQueue.push(5);
        printArray(arrayQueue.array);
        arrayQueue.push(6);
        printArray(arrayQueue.array);

        System.out.println("arrayQueue.pop() = " + arrayQueue.pop());
        arrayQueue.push(7);
        printArray(arrayQueue.array);
        System.out.println("arrayQueue.pop() = " + arrayQueue.pop());
        System.out.println("arrayQueue.pop() = " + arrayQueue.pop());
        System.out.println("arrayQueue.pop() = " + arrayQueue.pop());
        System.out.println("arrayQueue.pop() = " + arrayQueue.pop());
        System.out.println("arrayQueue.pop() = " + arrayQueue.pop());
    }
}
