package com.algorithm.system.code03_linkedlist;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/21 16:11
 */
public class Code03_GetMinStack {

    public static class Node<E> {
        public Node<E> next;
        public E value;

        public Node(E e) {
            value = e;
            next = null;
        }
    }

    public static class MyStack<E> {
        public Node<E> head;

        private int size;

        public MyStack() {
            this.head = null;
            this.size = 0;
        }

        public int getSize() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public void push(E v) {
            Node<E> n = new Node<>(v);

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

        public E peek() {
            E ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        // 用一个额外的栈，每次添加元素和自己栈顶的元素比较
        // 如果小于栈顶的元素，添加这新元素，反之重复添加栈顶的元素
        MyStack<Integer> s = new MyStack<>();
        MyStack<Integer> h = new MyStack<>();

        s.push(1);
        s.push(2);
        s.push(3);

        System.out.println("s.pop() = " + s.pop());
        System.out.println("s.pop() = " + s.pop());
        System.out.println("s.pop() = " + s.pop());
    }
}
