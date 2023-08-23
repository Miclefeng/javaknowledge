package com.algorithm.system.code03_linkedlist;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/23 11:12
 */
public class Code03_ArrayGetMaxRecursion {

    public static int getMax(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int m = l + ((r - l) >> 1);
        int leftMax = getMax(arr, l, m);
        int rightMax = getMax(arr, m + 1, r);
        return Math.max(leftMax, rightMax);
    }


    public static int findMax(int[] arr) {
        int max = 0;
        for (int v : arr) {
            max = Math.max(max, v);
        }
        return max;
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
            int max1 = getMax(arr, 0, arr.length - 1);
            int max2 = findMax(arr);
            if (max1 != max2) {
                System.out.println("Error:");
                printArray(arr);
            }
        }
        System.out.println("Test Finish.");
    }
}
