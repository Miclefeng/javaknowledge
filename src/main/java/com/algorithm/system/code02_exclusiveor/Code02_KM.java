package com.algorithm.system.code02_exclusiveor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/14 10:53
 */
public class Code02_KM {

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
        int[] bitArray = new int[32];
        for (int a : arr) {
            // 计算 所有数 每一位二进制位 相加的和，
            for (int i = 0; i < len; i++) {
                bitArray[i] += (a >> i) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            // m 个数的每一个 二进制位相加 一定是 m 的倍数
            // 如果不是 肯定是包含了出现 k 次的一种数
            // 把每一位对 m 取余不为 0 的位 取出来 进行 | 运算，得到出现 k 次的数的值
            if (bitArray[i] % m == 0) {
                continue;
            }
            if (bitArray[i] % m == k) {
                ans |= (1 << i);
            } else {
                return -1;
            }
        }

        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }

        return ans;
    }

    public static int testOnlyKTimesNum(int[] arr, int k, int m) {
        Map<Integer, Integer> help = new HashMap<>();
        for (int a : arr) {
            help.put(a, help.getOrDefault(a, 0) + 1);
        }

        for (int a : help.keySet()) {
            if (help.get(a) == k) {
                return a;
            }
        }
        return -1;
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int kinds = (int) (Math.random() * maxKinds + 2);
        int[] arr = new int[k + (kinds - 1) * m];

        int kNumber = randomNumber(range);
        Set<Integer> help = new HashSet<>();
        help.add(kNumber);
        int index = 0;
        for (; index < k; ) {
            arr[index++] = kNumber;
        }
        kinds--;
        while (kinds > 0) {
            int mNumber;
            do {
                mNumber = randomNumber(range);
            } while (help.contains(mNumber));
            help.add(mNumber);

            for (int i = 0; i < m; i++) {
                arr[index++] = mNumber;
            }
            kinds--;
        }

        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int p = (int) (Math.random() * arr.length);
            arr[i] = arr[p];
            arr[p] = tmp;
        }
        return arr;
    }

    public static int randomNumber(int range) {
        return (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);
    }

    public static void printArray(int[] arr) {
        for (int v : arr) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{7, 3, 3, 4, 4, 6, 6, 7, 9, 8, 8};
//        System.out.println("onlyKTimesNum(arr, 1, 2) = " + onlyKTimesNum(arr, 1, 2));
//
//        System.out.println("testOnlyKTimesNum(arr, 1, 2) = " + testOnlyKTimesNum(arr, 1, 2));

        int maxKinds = 20;
        int range = 200;
        int testTimes = 1000000;
        int max = 9;

        System.out.println("Test Begin:");
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
