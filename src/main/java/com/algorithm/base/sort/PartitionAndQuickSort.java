package com.algorithm.base.sort;

import java.util.Arrays;

/**
 * @author miclefengzss
 * 2022/3/15 上午9:22
 */
public class PartitionAndQuickSort {

    /**
     * 将小于等于数组最后一个数的放到数组前面
     *
     * @param arr
     */
    public static void splitNum1(int[] arr) {
        // 小于等于区的起始位置
        int lessEqualR = -1;
        // 当前元素位置
        int index = 0;
        int r = arr.length;
        while (index < r) {
            // 如果当前数小于等于最后一个元素
            if (arr[index] <= arr[r - 1]) {
                // 小于等于区的下一个元素和当前元素进行交换
                swap(arr, ++lessEqualR, index++);
            } else {
                index++;
            }
        }
    }

    /**
     * 将小于数组最后一个数放在前边，等于放在中间，大于的放在后边
     *
     * @param arr
     */
    public static void splitNum2(int[] arr) {
        int r = arr.length;
        // 小于区的起始位置
        int lessR = -1;
        // 大于区的起始位置
        int moreL = r - 1;
        // 当前元素的位置
        int index = 0;

        while (index < moreL) {
            // 如果当前数小于最后一个数
            if (arr[index] < arr[r - 1]) {
                // 小于区的下一个数和当前数进行交换
                swap(arr, ++lessR, index++);
                // 如果当前数大于最后一个数
            } else if (arr[index] > arr[r - 1]) {
                // 当前数和大于区的前一个数进行交换
                swap(arr, --moreL, index);
            } else {
                // 相等的当前数往下走
                index++;
            }
        }

        swap(arr, moreL, r - 1);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] equal = partition(arr, l, r);
        process(arr, l, equal[0] - 1);
        process(arr, equal[1] + 1, r);
    }

    // arr[L...R]范围上，拿arr[R]做划分值，
    // L....R < = >
    private static int[] partition(int[] arr, int l, int r) {
        int index = l;
        int lessR = l - 1;
        int moreL = r; // r = (arr.length - 1) 是又边界，需要包含
        while (index < moreL) {
            if (arr[index] < arr[r]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[r]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, r);
        return new int[]{lessR + 1, moreL};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
//        int[] arr = {7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 4};
//        printArray(arr);
//        splitNum1(arr);
//        printArray(arr);
//        splitNum2(arr);
//        printArray(arr);

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
