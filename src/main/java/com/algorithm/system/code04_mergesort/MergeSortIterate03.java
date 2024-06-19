package com.algorithm.system.code04_mergesort;

import java.util.Arrays;

/**
 * @Author: miclefengzss
 * @Date: 2024/6/19 09:56
 */
public class MergeSortIterate03 {

    public static void mergeSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        int mergeSize = 1;
        int n = arr.length;
        while (mergeSize < n) {
            int l = 0;

            while (l < n) {
                // 找中点位置
                int m = l + mergeSize - 1;
                // 中点位置为数组最后一个位置，即没有右组，直接退出
                if (m >= n - 1) {
                    break;
                }
                // 第一个 merge 的右组终点位置为 m + mergeSize
                int r = Math.min(m + mergeSize, n - 1);
                // 进行 merge
                merge(arr, l, m, r);
                // 左组起始位置来到下一个
                l = r + 1;
            }

            if (mergeSize > (n >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }

    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r -l +1];
        int index = 0;

        int p = l;
        int q = m + 1;

        while (p <= m && q <= r) {
            help[index++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        }
        while (p <= m) {
            help[index++] = arr[p++];
        }
        while (q <= r) {
            help[index++] = arr[q++];
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
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
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
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("Error.");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("Test End.");
    }
}
