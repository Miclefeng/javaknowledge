package com.algorithm.system.code04_mergesort;


/**
 * @author miclefengzss
 * 2022/3/29 上午9:40
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    /**
     * 切分数组知道只剩一个元素
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        // 左右两两进行比较，然后排序合并
        merge(arr, l, mid, r);
    }

    /**
     * 切分的分组进行merge操作
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        // 辅助排序数组
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l;
        int p2 = mid + 1;

        // p1 和 p2 都不越界，左右两遍谁小拷贝谁
        while (p1 <= mid && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // p1 不越界，拷贝左边的
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }

        // p2 不越界，拷贝右边的
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }

        // 将排好序的 help 数组中的元素拷贝回 arr 中
        System.arraycopy(help, 0, arr, l, help.length);
    }

    /**
     * 迭代实现归并排序
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 步长，起始为 1，每次翻倍
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int l = 0;
            while (l < N) {
                // 左组刚好凑齐或者凑不齐左组(没有右组)直接返回不用操作
                if (mergeSize >= N - l) {
                    break;
                }
                int m = l + mergeSize - 1;
                // 凑不齐右组就把右组全部返回
                int r = m + Math.min(mergeSize, N - m - 1);
                merge(arr, l, m, r);
                l = r + 1;
            }

            // 防止溢出
            if (mergeSize > (N >> 1)) {
                break;
            }
            // 步长翻倍
            mergeSize = mergeSize << 1;
        }
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

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 50;
        int maxValue = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
