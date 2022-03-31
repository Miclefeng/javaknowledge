package com.algorithm.base.sort;

/**
 * @author miclefengzss
 * 2022/2/22 上午11:47
 */
public class SortTest {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;

        // 小的数放到最左边
        // 0 - 0  =>  10, 8, 9, 2, 4, 1, 7, 6, 5, 3
        // 0 - 1  =>  8, 10, 9, 2, 4, 1, 7, 6, 5, 3
        // 0 - 2  =>  8, 9, 10, 2, 4, 1, 7, 6, 5, 3
        // 0 - 3  =>  2, 8, 9, 10, 4, 1, 7, 6, 5, 3
        // 0 - N
        // 结尾位置在变，i 描述一个结尾的变化
        for (int i = 1; i < N; i++) {
            // 先判断左边是否有数，有的话判断前一个数和当前数大小，小的话进行交换
            for (int pre = i - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre + 1, pre);
            }
        }
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 0   - N
        // 1   - N
        // 2   - N
        // N-1 - N
        // 开始位置在变化，i 描述开始位置的变化
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
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
        // 结尾位置在变化，end描述结尾位置的变化
        for (int end = N - 1; end > 0; end--) {
            for (int second = 0; second < end; second++) {
                if (arr[second] > arr[second + 1]) {
                    swap(arr, second, second + 1);
                }
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
        int testTimes = 1000000;
        int maxSize = 20;
        int maxValue = 200;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            insertSort(arr);
            if (!check(arr)) {
                printArray(arr);
                System.out.println("Error.");
                break;
            }
            if (i % 200000 == 0) {
                printArray(arr);
            }
        }
    }

    public static boolean check(int[] arr) {
        boolean r = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                r = false;
                break;
            }
        }
        return r;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }
}
