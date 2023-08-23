package com.algorithm.system.code03_linkedlist;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/21 14:52
 */
public class Code03_RemoveGivenValueInLinkedList {

    public static class Node<E> {

        public Node<E> next;

        public E value;

        public Node(E e) {
            value = e;
        }
    }

    public static Node<Integer> removeGivenValue(Node<Integer> h, Integer v) {
        // 找到第一个没有被删除的节点作为头节点
        while (h != null) {
            if (!h.value.equals(v)) {
                break;
            }
            h = h.next;
        }

        // 遍历删除节点
        Node<Integer> cur = h;
        Node<Integer> pre = h;
        while (cur != null) {
            // 找到删除元素的前一个节点
            // 如果当前节点是删除节点，前一个节点的next = 当前节点的 next
            if (cur.value.equals(v)) {
                pre.next = cur.next;
            } else {
                // 如果不是需要删除的元素，将当前节点设置为前一个节点
                pre = cur;
            }
            cur = cur.next;
        }
        return h;
    }

    public static void printLinkedList(Node<Integer> h) {
        while (h != null) {
            System.out.print(h.value + " ");
            h = h.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node<Integer> h = new Node<>(3);
        Node<Integer> a = new Node<>(4);
        Node<Integer> b = new Node<>(2);
        Node<Integer> c = new Node<>(2);
        Node<Integer> d = new Node<>(3);
        Node<Integer> e = new Node<>(5);
        Node<Integer> f = new Node<>(6);
        Node<Integer> g = new Node<>(2);
        Node<Integer> i = new Node<>(7);
        h.next = a;
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = i;
        printLinkedList(h);
        System.out.println("========================");
        Node<Integer> nH = removeGivenValue(h, 2);
        printLinkedList(nH);
    }
}
