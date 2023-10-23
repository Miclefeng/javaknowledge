package com.algorithm.system.code04_mergesort;

import java.util.Arrays;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/28 10:39
 */
public class Code04_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        // 递归分解到只剩一个数为止
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(arr, l, m);
        process(arr, m + 1, r);
        // 左右两两进行比较，然后排序合并
        merge(arr, l, m, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        // 辅助排序数组
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = m + 1;

        // 两遍的数组都不越界，谁小 copy 谁
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }

        // p2 越界，copy p1
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        // p1 越界，copy p2
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        // 将原数组中的值替换为排好序的辅助数组中的值
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxValue = 300;
        int maxSize = 100;
        int testTimes = 1000000;

        System.out.println("Test Begin: ");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            mergeSort(arr1);
            int[] arr2 = copyArray(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                printArray(arr1);
                printArray(arr2);
                System.out.println("error.");
                break;
            }
        }
        System.out.println("Test End.");
    }
}
