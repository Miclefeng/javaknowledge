package com.algorithm.base;

import com.algorithm.base.basesort.BasicSort;

/**
 * 求数组中 arr[L] 到 arr[R] 的和,可以生成一个前缀和的数组
 *
 * @author miclefengzss
 * 2021/12/16 下午4:21
 */
public class PrefixSum {

    /**
     * H[i] = arr[0 - i]的累加和
     * H[2] = 0 - 2 累加和
     * H[7] = 0 - 7 累加和
     *  3 - 7 的累加和 = H[7] - H[2]
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int rangeSum(int[] arr, int L, int R) {
        int[] ans = new int[arr.length];

        int pre = 0;
        for (int i = 0; i < arr.length; i++) {
            pre += arr[i];
            ans[i] = pre;
        }

        if (L == 0) {
            return ans[R];
        }

        return ans[R] - ans[L - 1];
    }

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static int test(int[] arr, int L, int R) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        if (len == 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxLen = 50;
        int maxValue = 1000;
        int times = 10000;

        for (int i = 0; i < times; i++) {
            int[] arr = lenRandomValueRandom(maxLen, maxValue);
            if (arr == null) {
                continue;
            }
            BasicSort.selectionSort(arr);
            int l = (int) (Math.random() * arr.length - 1);
            int r;
            if (l == arr.length - 1) {
                r = l;
            } else {
                r = l + 1;
            }
            final int test = test(arr, l, r);
            final int sum = rangeSum(arr, l, r);
            printArray(arr);
            System.out.println("L : " + l + ", R : " + r);
            System.out.println("Test: " + test + ", Sum: " + sum);
            if (test != sum) {
                System.out.println("出错了！");
            }
        }
    }
}
