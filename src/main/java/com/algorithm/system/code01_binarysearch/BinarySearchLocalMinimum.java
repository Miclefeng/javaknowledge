package com.algorithm.system.code01_binarysearch;

import com.threadcoreknowledge.juc.threadlocal.M;

/**
 * @author miclefengzss
 * 2022/3/18 上午10:08
 */
public class BinarySearchLocalMinimum {

    /**
     * 数组中相邻的两个数不相等
     *
     * @param arr
     * @return
     */
    public static int binarySearchLocalMinimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        if (arr[1] > arr[0]) {
            return 0;
        }
        if (arr[N - 2] > arr[N - 1]) {
            return N - 1;
        }
        int l = 0;
        int r = N - 1;
        // 一直划分到只剩 1 个数
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            } else {
                if (arr[mid] > arr[mid + 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        // 只剩 l = r 一个数，直接返回 l 或 r
        return l;
    }

    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int leftIndex = minIndex - 1;
        int rightIndex = minIndex + 1;
        boolean leftBigger = leftIndex < 0 || arr[leftIndex] > arr[minIndex];
        boolean rightBigger = rightIndex >= arr.length || arr[rightIndex] > arr[minIndex];
        return leftBigger && rightBigger;
    }

    /**
     * 生成随机数组，相邻不相等
     *
     * @return
     */
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen + 1);
        int[] arr = new int[len];
        arr[0] = (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 50;
        int maxValue = 200;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int minIndex = binarySearchLocalMinimum(arr);
            if (minIndex != -1) {
                System.out.println(arr.length);
                System.out.println(minIndex);
                printArr(arr);
                break;
            }
            if (!check(arr, minIndex)) {
                System.out.println(minIndex);
                printArr(arr);
                System.out.println("Error.");
            }
        }
        System.out.println("测试结束。");
    }
}
