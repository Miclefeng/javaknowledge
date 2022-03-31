package com.algorithm.system.code02_exclusiveor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author miclefengzss
 * 2022/3/19 上午11:06
 */
public class KNumberAndMNumber {

    /**
     * 输入一定能够保证，数组中所有的数都出现了M次，只有一种数出现了K次
     * 1 <= K < M
     *
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int onlyKTimesNum(int[] arr, int k, int m) {
        int len = 32;
        int[] help = new int[len];
        for (int value : arr) {
            for (int j = 0; j < len; j++) {
                help[j] += (value >> j) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (help[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static int testOnlyKTimesNum(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int v : arr) {
            if (map.containsKey(v)) {
                map.put(v, map.get(v) + 1);
            } else {
                map.put(v, 1);
            }
        }
        int ans = 0;
        for (int key : map.keySet()) {
            if (map.get(key) == k) {
                ans = key;
                break;
            }
        }
        return ans;
    }

    /**
     * 生成随机数组
     *
     * @return
     */
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int kinds = (int) (Math.random() * maxKinds) + 2;
        int[] arr = new int[k + m * (kinds - 1)];
        int index = 0;
        HashSet<Integer> set = new HashSet<>();
        int kNum = randomNum(range);
        for (; index < k; index++) {
            arr[index] = kNum;
        }
        kinds--;
        set.add(kNum);
        while (kinds != 0) {
            int num;
            do {
                num = randomNum(range);
            } while (set.contains(num));
            set.add(num);
            for (int i = 0; i < m; i++) {
                arr[index++] = num;
            }
            kinds--;
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static int randomNum(int range) {
        return (int) (Math.random() * range) - (int) (Math.random() * range);
    }

    public static void printArray(int[] arr) {
        for (int v : arr) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxKinds = 20;
        int range = 200;
        int testTimes = 100000;
        int max = 9;
        System.out.println("Test Start:");
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * max) + 1;
            int b = (int) (Math.random() * max) + 1;

            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(maxKinds, range, k, m);
            int n1 = onlyKTimesNum(arr, k, m);
            int n2 = testOnlyKTimesNum(arr, k, m);
            if (n1 != n2) {
                printArray(arr);
                System.out.println("k = " + k + ", m = " + m + ", n1 = " + n1 + ", n2 = " + n2);
                System.out.println("Error.");
                break;
            }
        }
        System.out.println("Test End.");
    }
}
