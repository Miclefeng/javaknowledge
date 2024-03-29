package com.algorithm.base.sort;

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
        // 0   - N
        // 1   - N
        // 2   - N
        // N-1 - N
        // 开始位置在变化，i 描述开始位置的变化
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            // 找出最小位置的索引，每次把最小的数放到 i 位置
            for (int j = i; j < N; j++) {
                minIndex = (arr[minIndex] > arr[j]) ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
//        for (int i = 0; i < N; i++) {
//            int minValueIndex = i;
//
//            for (int j = i + 1; j < N; j++) {
//                minValueIndex = (arr[minValueIndex] > arr[j]) ? j : minValueIndex;
//            }
//            swap(arr, minValueIndex, i);
//        }
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
            // 从0开始，当前数和后一个数进行比较，大的话就交换
            for (int j = 0; j < end; j++) {
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
        int N = arr.length;
        // 小的数放到最左边
        // 0 - 0  =>  10, 8, 9, 2, 4, 1, 7, 6, 5, 3
        // 0 - 1  =>  8, 10, 9, 2, 4, 1, 7, 6, 5, 3
        // 0 - 2  =>  8, 9, 10, 2, 4, 1, 7, 6, 5, 3
        // 0 - 3  =>  2, 8, 9, 10, 4, 1, 7, 6, 5, 3
        // 0 - N
        // 结尾位置在变，end描述一个结尾的变化
        for (int end = 1; end < N; end++) {
            // 插入排序只能从后往前插，每次结尾新加进来的数都要和前边的所有数比较，所以需要从后往前遍历
            // 先判断左边是否有数，有的话判断前一个数和当前数大小，小的话进行交换
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
        System.out.println("===============");
//        selectionSort(arr);
        bubbleSort(arr);
//        insertSort(arr);
//        insertSortBigToLittle(arr);
        System.out.println("===============");
        printArray(arr);

    }

    // 插入排序只能从后往前插
    public static void insertSortLittleToBig(int[] arr) {
        int n = arr.length;
        // 前插排序,从小到大排序
        for (int end = 1; end < n; end++) {
            // 需要保证前面的数不能比后边的数小,所以 end 需要每次+1，然后把新数和前边排好序的作比较
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
            printArray(arr);
        }
    }

    public static void insertSortBigToLittle(int[] arr) {
        int n = arr.length;
        // 前插排序,从小到大排序
        for (int end = 1; end < n; end++) {
            // 需要保证前面的数不能比后边的数小,所以 end 需要每次+1，然后把新数和前边排好序的作比较
            for (int pre = end - 1; pre >= 0 && arr[pre] < arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
            printArray(arr);
        }
    }
}
