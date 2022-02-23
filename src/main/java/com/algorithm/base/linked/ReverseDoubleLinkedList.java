package com.algorithm.base.linked;

/**
 * @author miclefengzss
 * 2022/2/23 下午4:10
 */
public class ReverseDoubleLinkedList {

    static class Node {

        public int value;
        public Node pre;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static Node reverseDoubleLinkedList(Node head) {
        Node next;
        Node pre = null;

        while (head != null) {
            // 记一下后面的节点，以便于后续调整后还能找到下一个节点
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node2.pre = node1;
        node3.pre = node2;

        node1 = reverseDoubleLinkedList(node1);

        while (node1 != null) {
            System.err.print(node1.value + " <-> ");
            node1 = node1.next;
        }
        System.out.println();

    }
}
