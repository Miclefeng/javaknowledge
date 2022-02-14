package com.algorithm.base.binarysearch;

import java.util.Arrays;

/**
 * @author miclefengzss
 * 2021/12/20 下午1:20
 */
public class BinarySearchNearLeft {

    public static void main(String[] args) {
        int testTimes = 50000;
        int maxSize = 10;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = BinarySearch.generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random());
            if (test(arr, value) != nearestLestIndex(arr, value)) {
                BinarySearch.printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestLestIndex(arr, value));
                System.out.println("Error.");
                break;
            }
        }
        System.out.println("nice.");
    }

    // 找到有序数组中 >=value 最左边的值的位置
    public static int nearestLestIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) { // 一直二分到只剩一个数为止
            int mid = (l + r) / 2;
            if (arr[mid] >= value) { // 右边大于 value，剔除 mid 右边的继续查找
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1; // 数组中左边小于value，需要全部剔除
            }
        }
        return ans;
    }

    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }
}