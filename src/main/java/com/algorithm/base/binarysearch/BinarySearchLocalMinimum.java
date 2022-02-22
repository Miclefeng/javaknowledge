package com.algorithm.base.binarysearch;

/**
 * @author miclefengzss
 * 2022/2/21 下午5:13
 */
public class BinarySearchLocalMinimum {

    public static void main(String[] args) {

        int maxLen = 50;
        int maxValue = 200;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int minIndex = localMinIndex(arr);
            if (!check(arr, minIndex)) {
                System.out.println(minIndex);
                printArr(arr);
                System.out.println("Error.");
            }
        }
        System.out.println("测试结束。");
    }

    /**
     * 在数组中查找局部最小值，数组中的元素相邻不相等
     *
     * @param arr
     * @return
     */
    public static int localMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N - 2] > arr[N - 1]) {
            return N - 1;
        }

        int l = 0;
        int r = N - 1;

        while (l < r - 1) {
            int mid = (l + r) / 2;
            // mid 同时小于 左边 和 右边
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            } else {
                // 左 < mid  mid > 右
                // 左 < mid  mid < 右
                // 左 > mid  mid > 右
                if (arr[mid] > arr[mid - 1]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return arr[l] > arr[r] ? r : l;
    }

    /**
     * 生成随机数组，相邻不相等
     *
     * @return
     */
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int leftIndex = minIndex - 1;
        int rightIndex = minIndex + 1;
        boolean leftBigger = leftIndex >= 0 ? arr[leftIndex] > arr[minIndex] : true;
        boolean rightBigger = rightIndex < arr.length ? arr[rightIndex] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
