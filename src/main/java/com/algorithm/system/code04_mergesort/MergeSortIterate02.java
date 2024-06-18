package com.algorithm.system.code04_mergesort;

import java.util.Arrays;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/12 22:17
 */
public class MergeSortIterate02 {

    public static void mergeSort(int[] arr) {
        if (arr == null && arr.length < 2) {
            return;
        }

        int n = arr.length;
        // 步长
        int step = 1;

        // 步长大于n 表示最大的左右组merge结束
        while (step < n) {
            // l 每次从 0 开始
            int l = 0;
            // l > n 当前步长分割的左右组，merge结束
            while (l < n) {
                // m 开始位置，step为1 m和l 位置重合
                int m = l + step - 1;
                // m 大于或者等于数组最后一位位置，说明没有右组，直接跳过不需要比较
                if (m >= n - 1) {
                    break;
                }
                int r = Math.min(m + step, n - 1);
                merge(arr, l, m, r);
                l = r + 1;
            }
            if (step > (n >> 1)) {
                break;
            }
            step <<= 1;
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        System.arraycopy(help, 0, arr, l, help.length);
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
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr);
            mergeSort(arr);
            Arrays.sort(arr2);
            if (!isEqual(arr, arr2)) {
                System.out.println("Error.");
                printArray(arr);
                printArray(arr2);
                break;
            }
        }
        System.out.println("Test End.");
    }
}
