package com.algorithm.system.code04_mergesort;

import java.util.Arrays;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/27 12:32
 */
public class MergeSortIterate {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int step = 1;
        int n = arr.length;
        // 步长 > 数组长度停止
        while (step < n) {
            // l 每次从 0 开始
            int l = 0;
            // l 的位置每次都得变化，l > n 停止
            while (l < n) {
                // mid 位置
                int m = l + step - 1;
                // mid 大于或者等于数组最后一位位置，说明没有右组，直接跳过不需要比较
                if (m >= n - 1) {
                    break;
                }
                // 右组边界和数组最后的位置比较，谁小谁就是 r
                int r = Math.min(m + step, n - 1);
                // 进行merge
                merge(arr, l, m, r);
                // l 跳到下一个位置
                l = r + 1;
            }
            // 防止溢出
            if (step > n / 2) {
                break;
            }
            // 步长变为原来的2倍
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
            // TODO:
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
