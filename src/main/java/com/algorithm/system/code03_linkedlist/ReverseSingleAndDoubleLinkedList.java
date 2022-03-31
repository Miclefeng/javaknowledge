package com.algorithm.system.code03_linkedlist;

/**
 * @author miclefengzss
 * 2022/3/19 下午9:23
 */
public class ReverseSingleAndDoubleLinkedList {

    public static class SingleNode {
        public int value;
        public SingleNode next;

        public SingleNode(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static SingleNode reverseSingleLinkedList(SingleNode head) {
        SingleNode pre = null;
        SingleNode next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
            this.last = null;
            this.next = null;
        }
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next;

        while (head != null) {
            next = head.next;
            head.last = next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static DoubleNode createLinkedList(int[] arr) {
        DoubleNode head = new DoubleNode(arr[0]);
        DoubleNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new DoubleNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void printLinkedList(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen + 1);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int v = (int) (Math.random() * maxValue + 1);
            arr[i] = v;
        }
        return arr;
    }

    private static boolean isEqual(int[] arr, DoubleNode head) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 200;
        int testTimes = 1000000;
        System.out.println("Test Begin:");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            DoubleNode head = createLinkedList(arr);
            head = reverseDoubleLinkedList(head);
            if (!isEqual(arr, head)) {
                System.out.println("Error:");
                printArray(arr);
                printLinkedList(head);
            }
        }
        System.out.println("Test End.");
    }
}
