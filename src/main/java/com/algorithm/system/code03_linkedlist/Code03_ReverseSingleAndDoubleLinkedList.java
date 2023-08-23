package com.algorithm.system.code03_linkedlist;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/21 13:58
 */
public class Code03_ReverseSingleAndDoubleLinkedList {

    public static class SingleNode<E> {

        public SingleNode next;

        public E value;

        public SingleNode(E v) {
            value = v;
            next = null;
        }
    }

    public static class DoubleNode<E> {

        public DoubleNode last;

        public DoubleNode next;

        public E value;

        public DoubleNode(E v) {
            value = v;
            last = next = null;
        }
    }

    public static SingleNode<Integer> reverseSingleLinkedList(SingleNode<Integer> h) {
        SingleNode<Integer> pre = null;
        SingleNode<Integer> next;
        while (h != null) {
            next = h.next;
            h.next = pre;
            pre = h;
            h = next;
        }
        return pre;
    }

    public static void printSingleLinkedList(SingleNode<Integer> h) {
        while (h != null) {
            System.out.print(h.value + " ");
            h = h.next;
        }
        System.out.println();
    }

    public static DoubleNode<Integer> reverseDoubleLinkedList(DoubleNode<Integer> h) {
        DoubleNode<Integer> pre = null;
        DoubleNode<Integer> next;
        while (h != null) {
            next = h.next;
            h.last = next;
            h.next = pre;
            pre = h;
            h = next;
        }
        return pre;
    }

    public static void printDoubleLinkedList(DoubleNode<Integer> h) {
        DoubleNode<Integer> pre = null;
        while (h != null) {
            System.out.print(h.value + " ");
            pre = h;
            h = h.next;
        }
        System.out.println();
        while (pre != null) {
            System.out.print(pre.value + " ");
            pre = pre.last;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SingleNode<Integer> h = new SingleNode<>(1);
        SingleNode<Integer> a = new SingleNode<>(2);
        SingleNode<Integer> b = new SingleNode<>(3);
        SingleNode<Integer> c = new SingleNode<>(4);
        h.next = a;
        a.next = b;
        b.next = c;
        printSingleLinkedList(h);
        System.out.println("========================");
        SingleNode<Integer> nH = reverseSingleLinkedList(h);
        printSingleLinkedList(nH);
        System.out.println("======SINGLE DOUBLE=====");
        DoubleNode<Integer> dh = new DoubleNode<>(1);
        DoubleNode<Integer> da = new DoubleNode<>(2);
        DoubleNode<Integer> db = new DoubleNode<>(3);
        DoubleNode<Integer> dc = new DoubleNode<>(4);
        dh.next = da;
        dh.last = null;
        da.next = db;
        da.last = dh;
        db.next = dc;
        db.last = da;
        dc.last = db;
        printDoubleLinkedList(dh);
        System.out.println("========================");
        DoubleNode<Integer> dNH = reverseDoubleLinkedList(dh);
        printDoubleLinkedList(dNH);
    }
}
