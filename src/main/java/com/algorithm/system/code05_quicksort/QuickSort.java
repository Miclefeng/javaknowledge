package com.algorithm.system.code05_quicksort;

/**
 * @author miclefengzss
 * 2022/4/1 下午3:20
 */
public class QuickSort {

    // <= X 放左边
    // >  X 放右边
    public static void partition1(int[] arr) {
        // 小于等于区的起始位置
        int less = -1;
        // 当前元素位置
        int index = 0;
        int n = arr.length;
        while (index < n) {
            // 如果当前数小于等于最后一个元素
            if (arr[index] <= arr[n - 1]) {
                // 小于等于区的下一个元素和当前元素进行交换
                swap(arr, index++, ++less);
            } else {
                index++;
            }
        }
    }

    // < X 放左边
    // = X 放中间
    // > X 放右边
    public static void partition2(int[] arr) {
        int n = arr.length;
        // 小于区的起始位置
        int less = -1;
        // 大于区的起始位置
        int more = n - 1;
        // 当前元素位置
        int index = 0;

        // 当前数和大于区的边界装上的时候停止
        while (index < more) {
            if (arr[index] < arr[n - 1]) {
                // 当前数和小于区的下一个数交换
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[n - 1]) {
                // 当前数和大于区的前一个数交换
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, n - 1);
    }

    /**
     * 快排1.0 每次只能选出一个数
     *
     * @param arr
     */
    public static void quick01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process01(arr, 0, arr.length - 1);
    }

    public static void process01(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int M = partition01(arr, l, r);
        process01(arr, l, M - 1);
        process01(arr, M + 1, r);
    }

    public static int partition01(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int less = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index] <= arr[r]) {
                swap(arr, ++less, index++);
            } else {
                index++;
            }
        }
        swap(arr, ++less, r);
        return less;
    }


    /**
     * 快排2.0 每次可以找出等值的一个或多个数
     *
     * @param arr
     */
    public static void quick02(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process02(arr, 0, arr.length - 1);
    }

    private static void process02(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] m = partition02(arr, l, r);
        process02(arr, l, m[0] - 1);
        process02(arr, m[1] + 1, r);
    }

    public static int[] partition02(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] < arr[r]) {
                swap(arr, ++less, index++);
            } else if (arr[index] > arr[r]) {
                swap(arr, --more, index);
            } else {
                index++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }


    /**
     * 快排3.0 随机快排
     *
     * @param arr
     */
    public static void quick03(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process03(arr, 0, arr.length - 1);
    }

    private static void process03(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] m = partition03(arr, l, r);
        process03(arr, l, m[0] - 1);
        process03(arr, m[1] + 1, r);
    }

    public static int[] partition03(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] < arr[r]) {
                swap(arr, ++less, index++);
            } else if (arr[index] > arr[r]) {
                swap(arr, --more, index);
            } else {
                index++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
        int testTimes = 100000;
        System.out.println("Test Begin:");
        long used1 = 0;
        long used2 = 0;
        long used3 = 0;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            long q3Start = System.currentTimeMillis();
            quick03(arr3);
            long q3Used = System.currentTimeMillis() - q3Start;
            long q2Start = System.currentTimeMillis();
            quick02(arr2);
            long q2Used = System.currentTimeMillis() - q2Start;
            long q1Start = System.currentTimeMillis();
            quick01(arr1);
            long q1Used = System.currentTimeMillis() - q1Start;

//            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
//                System.out.println("Error.");
//                printArray(arr1);
//                printArray(arr2);
//                printArray(arr3);
//                break;
//            }
            used1 += q1Used;
            used2 += q2Used;
            used3 += q3Used;
        }
        System.out.println("used1: " + used1 + " ms");
        System.out.println("used2: " + used2 + " ms");
        System.out.println("used3: " + used3 + " ms");
        System.out.println("Test Finish.");
    }
}
