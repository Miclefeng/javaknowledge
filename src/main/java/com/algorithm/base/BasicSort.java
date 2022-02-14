package com.algorithm.base;

/**
 * @author miclefengzss
 * 2021/12/16 上午10:21
 */
public class BasicSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 0   - N     找出最小位置的索引的范围，每次把最小的数放到头部
        // 1   - N
        // 2   - N
        // N-1 - N
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;

            for (int j = i + 1; j < N; j++) {
                minValueIndex = (arr[minValueIndex] > arr[j]) ? j : minValueIndex;
            }
            swap(arr, minValueIndex, i);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 0  - N-1    每次把最大的数放到尾部
        // 0  - N-2
        // 0  - N-3
        // 0  - N-(N-1)
        for (int end = N - 1; end >= 0; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }
        }
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 0 - 0   有序 完成
        // 0 - 1
        // 0 - 2
        // 0 - N
        for (int end = 1; end < N; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 8, 9, 2, 4, 1, 7, 6, 5, 3};
        printArray(arr);
//        selectionSort(arr);
//        bubbleSort(arr);
        insertSort(arr);
        printArray(arr);
    }
}
