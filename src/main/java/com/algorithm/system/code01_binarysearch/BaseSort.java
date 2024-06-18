package com.algorithm.system.code01_binarysearch;

import java.util.Arrays;

/**
 * @author miclefengzss
 * 2022/3/17 下午8:45
 */
public class BaseSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0   - N
        // 1   - N
        // 2   - N
        // N-1 - N
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = (arr[minIndex] > arr[j]) ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0  - N
        // 0  - N-1
        // 0  - N-2
        // 0  - 1
        // 结尾位置在变化，i 描述结尾位置的变化
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 小的数放到最左边
        // 0 - 0  =>  10, 8, 9, 2, 4, 1, 7, 6, 5, 3
        // 0 - 1  =>  8, 10, 9, 2, 4, 1, 7, 6, 5, 3
        // 0 - 2  =>  8, 9, 10, 2, 4, 1, 7, 6, 5, 3
        // 0 - 3  =>  2, 8, 9, 10, 4, 1, 7, 6, 5, 3
        // 0 - N
        // 结尾位置在变，i 描述一个结尾的变化
        for (int i = 0; i < arr.length; i++) {
            // 先判断左边是否有数，有的话判断前一个数和当前数大小，小的话进行交换
            for (int pre = i; pre > 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre - 1, pre);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] randomArray(int len, int max) {
        int n = (int) (Math.random() * len + 1);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int v = (int) (Math.random() * max + 1);
            arr[i] = v;
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    private static boolean isEqual(int[] arr, int[] arr2) {
        if (arr.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 30;
        int max = 200;
        int testTimes = 1000000;
        System.out.println("Test begin: ");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(len, max);
            int[] arr2 = copyArray(arr);
            insert04(arr);
            Arrays.sort(arr2);
            if (!isEqual(arr, arr2)) {
                printArray(arr);
                printArray(arr2);
                System.out.println("Error.");
                break;
            }
        }
        System.out.println("Test end.");
    }

    public static void bubble02(int[] arr) {
        // 每次以 i 位置为结尾，i 开始为全部数，结尾位置在变小
        for (int i = arr.length - 1; i >= 0; i--) {
            // 从 0 开始比较到 i，将最大的数移动到 i 位置
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void select02(int[] arr) {
        // 每次以 i 的位置为开始，i 开始为 0，开始位置在变大
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            // 从 i 开始往后比较，选出最小的位置，和 i 进行交换
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void insert02(int[] arr) {
        // 每次以 i 的位置为结尾，i 开始为 1，结尾位置在变大
        for (int i = 1; i < arr.length; i++) {
            // 从 i 开始往前比较，如果前面的数比当前数大，和当前数进行交换
            for (int pre = i; pre > 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre - 1, pre);
            }
        }
    }

    public static void bubble03(int[] arr) {
        // 每次以 i 位置为结尾，i 开始为全部数，结尾位置在变小
        for (int i = arr.length - 1; i >= 0; i--) {
            // 从 0 开始比较到 i，将最大的数移动到 i 位置
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void select03(int[] arr) {
        // 每次以 i 的位置为开始，i 开始为 0，开始位置在变大
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            // 从 i 往后比较，选出一个最小的位置，与 i 交换
            for (int j = i + 1; j < arr.length; j++) {
                min = arr[min] > arr[j] ? j : min;
            }
            swap(arr, i, min);
        }
    }

    public static void insert03(int[] arr) {
        // 每次以 i 的位置为结尾位置，i 开始为 1，结尾位置在变大
        for (int i = 1; i < arr.length; i++) {
            // 每次从 i 的位置向前比较，如果前面的数大就和当前数做交换
            for (int pre = i; pre > 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre, pre - 1);
            }
        }
    }

    public static void insert04(int[] arr) {
        // 每次以 i 的位置为结尾位置，i 开始为 1，结尾位置在变大
        for (int i = 1; i < arr.length; i++) {
            // 每次从 i 位置向前比较，如果前面的数大就和当前数做交换
            for (int pre = i; pre > 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre - 1, pre);
            }
        }
    }
}
