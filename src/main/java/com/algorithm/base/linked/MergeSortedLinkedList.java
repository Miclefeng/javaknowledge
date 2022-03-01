package com.algorithm.base.linked;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * @author miclefengzss
 * 2022/3/1 上午7:34
 */
public class MergeSortedLinkedList {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }

        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }

        return head;
    }
}
