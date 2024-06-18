package com.algorithm.system.code06_heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/13 12:49
 */
public class SortArrayDistanceLessK02 {

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        if (arr == null || arr.length < 2) {
            return;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int index = 0;

        // 移动距离不超过 k，即移动位置最远的数在 k，这个区间数最多会有 k+1 个
        // 先将k个数放入小根堆中
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }

        int j = 0;
        for (; index < arr.length; j++, index++) {
            // // 堆里目前有 k 个数，需要判断的是 k+1 个数的距离，所以先把数放入小根堆中
            heap.add(arr[index]);
            // 依次弹出小根堆中最小的数放到 i 位置
            arr[j] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[j++] = heap.poll();
        }
    }


    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int k) {
        // 生成随机长度的数组
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        // 填充数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (k + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
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

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) / 2 + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortArrayDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
//            System.out.println("k : " + k);
//            printArray(arr);
//            printArray(arr1);
//            printArray(arr2);
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
