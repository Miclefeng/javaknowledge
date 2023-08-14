package com.algorithm.base.binarysearch;

import java.util.Arrays;

/**
 * @author miclefengzss
 * 2021/12/20 下午12:56
 */
public class BinarySearch {

    public static void main(String[] args) {
        int testTimes = 50000;
        int maxSize = 10;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random());

            if (exist(arr, value)) {
                System.out.print("value : " + value + " exist arr : ");
                printArray(arr);
            }

            if (test(arr, value) != exist(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(exist(arr, value));
                System.out.println("Error.");
                break;
            }
        }
        System.out.println("nice.");
    }

    // 在有序数组中找到某个值
    public static boolean exist(int[] arr, int value) {
        // first
//        if (arr == null || arr.length == 0) {
//            return false;
//        }
//
//        int l = 0;
//        int r = arr.length - 1;
//        while (l <= r) { // 一直二分到只剩一个数为止或者找到value
//            int mid = (l + r) / 2;
//            if (arr[mid] == value) {
//                return true;
//            } else if (arr[mid] < value) { // 左边小于value，全部剔除
//                l = mid + 1;
//            } else { // 右边大于value，全部剔除
//                r = mid - 1;
//            }
//        }
//        return false;

        // second
//        if (arr == null || arr.length == 0) {
//            return false;
//        }
//        int l = 0;
//        int r = arr.length - 1;
//        while (l <= r) {
//            int mid = l + ((r - l) >> 1);
//            if (arr[mid] == value) {
//                return true;
//            } else if (arr[mid] < value) {
//                l = mid + 1;
//            } else {
//                r = mid - 1;
//            }
//        }
//        return false;

        // third
        if (arr == null || arr.length == 0) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == value) {
                return true;
            } else if (arr[mid] < value) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    public static boolean test(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
