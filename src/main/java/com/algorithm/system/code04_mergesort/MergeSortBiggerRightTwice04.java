package com.algorithm.system.code04_mergesort;

/**
 * @Description:
 * @Author: miclefengzss
 * @Date: 2024/6/19 15:44
 */
public class MergeSortBiggerRightTwice04 {

    public static int biggerRightTwice(int[] arr) {
        if (null == arr || 2 > arr.length) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int windowR = m + 1;
        int res = 0;
        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > arr[windowR] * 2) {
                windowR++;
            }
            res += windowR - m - 1;
        }

        int[] help = new int[r - l + 1];
        int i = 0;
        int p = l;
        int q = m + 1;
        while (p <= m && q <= r) {
            help[i++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        }
        while (p <= m) {
            help[i++] = arr[p++];
        }
        while (q <= r) {
            help[i++] = arr[q++];
        }
        System.arraycopy(help, 0, arr, l, help.length);
        return res;
    }

    public static int comparator(int[] arr) {
        if (null == arr || 2 > arr.length) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                res += arr[i] > arr[j] * 2 ? 1 : 0;
            }
        }
        return res;
    }
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int v1 = biggerRightTwice(arr1);
            int v2 = comparator(arr2);
            if (v1 != v2) {
                System.out.println("Oops!");
                System.out.println(v1);
                printArray(arr1);
                System.out.println(v2);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
