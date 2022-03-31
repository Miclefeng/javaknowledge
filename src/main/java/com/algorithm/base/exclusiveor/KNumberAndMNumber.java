package com.algorithm.base.exclusiveor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author miclefengzss
 * 2022/3/17 上午11:21
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
        int[] help = new int[32];

        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (arr[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        int ans = 0;
        for (int i : map.keySet()) {
            if (map.get(i) == k) {
                ans = i;
                break;
            }
        }

        return ans;
    }


    public static int randomRangeNumber(int range) {
        return (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
