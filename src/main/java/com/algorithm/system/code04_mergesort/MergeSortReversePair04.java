package com.algorithm.system.code04_mergesort;

/**
 * @Description:
 * @Author: miclefengzss
 * @Date: 2024/6/19 15:15
 */
public class MergeSortReversePair04 {

    public static int reversePair(int[] arr) {
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
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p = m;
        int q = r;
        int res = 0;
        while (p >= l && q > m) {
            res += arr[p] > arr[q] ? q - m : 0;
            help[i--] = arr[p] > arr[q] ? arr[p--] : arr[q--];
        }
        while (p >= l) {
            help[i--] = arr[p--];
        }
        while (q > m) {
            help[i--] = arr[q--];
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
                res += arr[i] > arr[j] ? 1 : 0;
            }
        }

        return res;
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
            if (reversePair(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
