package com.algorithm.system.code05_quicksort;

import java.util.Arrays;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/29 12:49
 */
public class QuickSort03 {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + ((int) (Math.random() * (r - l + 1))), r);
        int[] pos = partition(arr, l, r);
        process(arr, l, pos[0] - 1);
        process(arr, pos[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{l, r};
        }
        if (l > r) {
            return new int[]{-1, -1};
        }
        int less = l - 1;
        int more = r;
        int index = l;

        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }

        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
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

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 500;
        int testTimes = 1000000;
        System.out.println("Test Begin:");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("Error.");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("Test Finish.");
    }
}
