package com.algorithm.base.linked;

/**
 * leetcode: https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @author miclefengzss
 * 2022/2/24 上午9:22
 */
public class ReverseNodesInKGroup {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        // 第一组筹齐了，第一组的结尾就是转变后新的链表的头部，需要抓住
        head = end;
        reverse(start, end);
        // 上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            // 将翻转后的上一段链表节点的尾部指向新的段的链表的尾部
            lastEnd.next = end;
            lastEnd = start;
        }

        return head;
    }

    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode cur = start;
        ListNode pre = null;
        ListNode next;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode9 = new ListNode(9);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;

        listNode = reverseKGroup(listNode, 2);

        while (listNode != null) {
            System.err.print(listNode.val + "->");
            listNode = listNode.next;
        }
    }
}
