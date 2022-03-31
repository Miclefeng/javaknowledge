package com.algorithm.system.code01_binarysearch;

import java.util.Arrays;

/**
 * @author miclefengzss
 * 2022/3/18 上午9:10
 */
public class BinarySearchAndNearLeftRight {

    /**
     * 有序数组中找到 n, 没有返回 -1
     *
     * @param arr
     * @param num
     * @return
     */
    public static boolean binarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public static boolean testBinarySearch(int[] arr, int num) {
        for (int value : arr) {
            if (value == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找到 >= num 最左的位置
     *
     * @param arr
     * @param num
     * @return
     */
    public static int binarySearchNearLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static int testBinarySearchNearLeft(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 有序数组中找到 <= num 的最右位置
     *
     * @param arr
     * @param num
     * @return
     */
    public static int binarySearchNearRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] <= num) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static int testBinarySearchNearRight(int[] arr, int num) {
        int ans = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                ans = i;
            }
        }
        return ans;
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

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxSize = 100;
        int maxValue = 300;
        System.out.println("Test Begin: ");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random());
            if (testBinarySearch(arr, value) != binarySearch(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(testBinarySearch(arr, value));
                System.out.println(binarySearch(arr, value));
                System.out.println("Search Error.");
                break;
            }
            if (testBinarySearchNearLeft(arr, value) != binarySearchNearLeft(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(testBinarySearchNearLeft(arr, value));
                System.out.println(binarySearchNearLeft(arr, value));
                System.out.println("Near Left Error.");
                break;
            }
            if (testBinarySearchNearRight(arr, value) != binarySearchNearRight(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(testBinarySearchNearRight(arr, value));
                System.out.println(binarySearchNearRight(arr, value));
                System.out.println("Near Right Error.");
                break;
            }
        }
        System.out.println("Test End.");
    }
}
