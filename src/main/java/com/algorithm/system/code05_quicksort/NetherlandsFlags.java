package com.algorithm.system.code05_quicksort;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/30 13:50
 */
public class NetherlandsFlags {

    // 在一个数组中，去数组最后一位 x， < x 放左边， = x 放中间，> x 放右边
    public static void netherLandsFlag(int[] arr) {
        int n = arr.length;
        int less = -1;
        int more = n - 1;
        int i = 0;

        // 如果当前位置和大于区的边界相撞，表示所有的数已经划分完成
        while (i < more) {
            if (arr[i] == arr[n - 1]) {
                i++;
            } else if (arr[i] < arr[n - 1]) {
                swap(arr, i++, ++less);
            } else {
                swap(arr, i, --more);
            }
        }
        swap(arr, n - 1, more);
    }

    public static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    // for test
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
        int[] arr = new int[]{5, 3, 2, 4, 6, 7, 1, 9, 4};
        netherLandsFlag(arr);
        printArray(arr);
    }
}
