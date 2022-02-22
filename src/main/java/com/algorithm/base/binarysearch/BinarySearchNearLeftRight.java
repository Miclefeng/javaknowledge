package com.algorithm.base.binarysearch;

import java.util.Arrays;

/**
 * @author miclefengzss
 * 2021/12/20 下午1:20
 */
public class BinarySearchNearLeftRight {

    public static void main(String[] args) {
        int testTimes = 50000;
        int maxSize = 10;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = BinarySearch.generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random());
            if (testLeft(arr, value) != nearestLeftIndex(arr, value)) {
                BinarySearch.printArray(arr);
                System.out.println(value);
                System.out.println(testLeft(arr, value));
                System.out.println(nearestLeftIndex(arr, value));
                System.out.println("Left Error.");
                break;
            }
        }

        for (int i = 0; i < testTimes; i++) {
            int[] arr = BinarySearch.generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random());
            if (testRight(arr, value) != nearestRightIndex(arr, value)) {
                BinarySearch.printArray(arr);
                System.out.println(value);
                System.out.println(testRight(arr, value));
                System.out.println(nearestRightIndex(arr, value));
                System.out.println("Right Error.");
                break;
            }
        }
        System.out.println("nice.");
    }

    // 找到有序数组中 >=value 最左边的值的位置
    public static int nearestLeftIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= value) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

//        int l = 0;
//        int r = arr.length - 1;
//        int ans = -1;
//        while (l <= r) { // 一直二分到只剩一个数为止
//            int mid = (l + r) / 2;
//            if (arr[mid] >= value) { // 右边大于 value，剔除 mid 右边的继续查找
//                ans = mid;
//                r = mid - 1;
//            } else {
//                l = mid + 1; // 数组中左边小于value，需要全部剔除
//            }
//        }
        return ans;
    }

    // 找到有序数组中 <= value 最右边的值的位置
    public static int nearestRightIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 1 4 5 7 9 10 11    6
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > value) {
                r = mid - 1;
            } else {
                ans = mid;
                l = mid + 1;
            }
        }

        return ans;
    }

    public static int testRight(int[] arr, int value) {
        int ans = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= value) {
                ans = i;
            }
        }
        return ans;
    }

    public static int testLeft(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }
}
