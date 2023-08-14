package com.algorithm.system.code02_exclusiveor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/8/11 11:03
 */
public class Code02_EvenAndOdd {

    /**
     * 数组中，只有一种数出现奇数次，其他出现偶数次，求奇数是多少
     * <p>
     * 偶数个相同的数 异或运算 为 0，奇数个相同的数 异或 得到 本身
     *
     * @param arr
     * @return
     */
    public static int findOneOdd(int[] arr) {
        int eor = 0;
        for (int n : arr) {
            eor ^= n;
        }
        return eor;
    }

    public static int testFindOneOdd(int[] arr) {
        Map<Integer, Integer> help = new HashMap<>();
        for (int n : arr) {
            if (help.containsKey(n)) {
                help.put(n, help.get(n) + 1);
            } else {
                help.put(n, 1);
            }
        }
        for (Integer k : help.keySet()) {
            if ((help.get(k) & 1) == 1) {
                return k;
            }
        }
        return 0;
    }

    /**
     * 数组中，有两种数出现奇数次，其他出现偶数次，求奇数是多少
     *
     * @param arr
     */
    public static int findTwoOdd(int[] arr) {
        int eor = 0;
        for (int n : arr) {
            eor ^= n;
        }

        // 取出两个奇数异或后的结果最右边的 1，这位上的 1 肯定属于某一个奇数
        // 一个数 & 自己的负数，就会只留下二进制位最右边的 1, 其它位都为 0
        int m = eor & (-eor);

        int anther = 0;

        for (int n : arr) {
            // 这个位为 1 的数 只能是其中一个奇数 和 其他的偶数
            if ((n & m) != 0) {
                // 偶数个数 异或运算 为 0
                // 把其中一个奇数提取出来
                anther ^= n;
            }
        }

        return anther + (anther ^ eor);
    }

    public static int testFindTwoOdd(int[] arr) {
        Map<Integer, Integer> help = new HashMap<>();
        for (int n : arr) {
            if (help.containsKey(n)) {
                help.put(n, help.get(n) + 1);
            } else {
                help.put(n, 1);
            }
        }

        int r = 0;
        for (Integer k : help.keySet()) {
            if ((help.get(k) & 1) == 1) {
                r += k;
            }
        }

        return r;
    }

    public static int[] randomArr(int oddKinds, int maxKinds, int range) {
        int kinds = (int) (Math.random() * maxKinds + 1);
        if (kinds <= oddKinds) {
            kinds = oddKinds + 1;
        }

        int[] arr = new int[oddKinds + (kinds - oddKinds) * 2];
        int index = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < oddKinds; i++) {
            int odd;
            do {
                odd = randomNumber(range);
            } while (set.contains(odd));
            set.add(odd);
            arr[index++] = odd;
            kinds--;
        }

        while (kinds != 0) {
            int even;
            do {
                even = randomNumber(range);
            } while (set.contains(even));
            set.add(even);
            for (int i = 0; i < 2; i++) {
                arr[index++] = even;
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

    public static int randomNumber(int range) {
        return ((int) (Math.random() * range)) - ((int) (Math.random() * range));
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
        int testTimes = 1000000;

        System.out.println("Test Begin:");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArr(1, maxKinds, range);
            int v1 = findOneOdd(arr);
            int v2 = testFindOneOdd(arr);
            if (v1 != v2) {
                printArray(arr);
                System.out.println("v1 = " + v1 + ", v2 = " + v2);
            }
            int[] arr2 = randomArr(2, maxKinds, range);
            int n1 = findTwoOdd(arr2);
            int n2 = testFindTwoOdd(arr2);
            if (n1 != n2) {
                printArray(arr2);
                System.out.println("n1 = " + n1 + ", n2 = " + n2);
            }
        }
        System.out.println("Test End.");
    }
}
