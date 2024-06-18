package com.algorithm.system.code04_mergesort;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/28 20:56
 */
public class MergeSortBiggerRightTwice02 {

    public static int biggerRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
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
        int res = 0;
        // [L....M] [M+1....R]
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = m + 1;
        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > arr[windowR] * 2) {
                // 因为左组和右组都是有序的所有 windowR 不需要回退
                // 左组的前一个数走到 windowR，下一个数比当前数大，至少走到 windowR，所以不需要回退
                windowR++;
            }
            res += windowR - m - 1;
        }

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
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
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
        int testTime = 500000;
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
