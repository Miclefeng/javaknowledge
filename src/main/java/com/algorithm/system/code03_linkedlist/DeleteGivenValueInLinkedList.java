package com.algorithm.system.code03_linkedlist;

/**
 * @author miclefengzss
 * 2022/3/28 上午9:42
 */
public class DeleteGivenValueInLinkedList {


    public static class SingleNode {
        public int value;
        public SingleNode next;

        public SingleNode(int value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * 在链表中移除所有给定的值的节点，返回新头节点
     *
     * @param head
     * @param num
     * @return
     */
    public static SingleNode removeValue(SingleNode head, int num) {
        // 先确定新的头节点
        while (head != null) {
            if (head.value != num) {
                break;
            } else {
                head = head.next;
            }
        }

        SingleNode pre = head;
        SingleNode cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    public static SingleNode createLinkedList(int[] arr) {
        SingleNode head = new SingleNode(arr[0]);
        SingleNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new SingleNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    public static boolean findValueInLinkedList(SingleNode head, int v) {
        while (head != null) {
            if (head.value == v) {
                return true;
            }
            head = head.next;
        }
        return false;
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

    public static void printLinkedList(SingleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTimes = 1000000;
        System.out.println("Test Begin:");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int value = (int) (Math.random() * arr.length);
            SingleNode head = createLinkedList(arr);
            head = removeValue(head, arr[value]);
            System.out.println("=============================================");
            printArray(arr);
            System.out.println("Value: " + arr[value]);
            printLinkedList(head);
            if (findValueInLinkedList(head, arr[value])) {
                System.out.println("Error:");
                break;
            }
        }
        System.out.println("Test End.");
    }
}
