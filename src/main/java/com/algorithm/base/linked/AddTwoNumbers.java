package com.algorithm.base.linked;


/**
 * 测试链接：https://leetcode.com/problems/add-two-numbers/
 *
 * @author miclefengzss
 * 2022/2/24 下午8:58
 */
public class AddTwoNumbers {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = lenListNode(head1);
        int len2 = lenListNode(head2);
        ListNode l = len1 > len2 ? head1 : head2;
        ListNode s = len1 > len2 ? head2 : head1;

        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;
        int carry = 0;
        int curNum;
        while (curS != null) {
            curNum = curS.val + curL.val + carry;
            curL.val = curNum % 10;
            carry = curNum / 10;
            last = curL;
            curS = curS.next;
            curL = curL.next;
        }

        while (curL != null) {
            curNum = curL.val + carry;
            curL.val = curNum % 10;
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
        }

        if (carry != 0) {
            last.next = new ListNode(carry);
        }
        return l;
    }

    public static int lenListNode(ListNode head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }

    public static void main(String[] args) {

    }
}
