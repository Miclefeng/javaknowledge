package com.algorithm.system.code02_exclusiveor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author miclefengzss
 * 2022/3/19 上午11:06
 */
public class EvenTimesAndOddTimes {

    /**
     * 一个数组中只有一个数出现奇数次，其它全部出现偶数次
     * 一个数异或自己的结果为 0
     *
     * @param arr
     * @return
     */
    public static int printOddTimesNum(int[] arr) {
        int eor = 0;
        for (int value : arr) {
            eor ^= value;
        }
        return eor;
    }

    public static int testOddTimesNum(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }
        for (int v : map.keySet()) {
            if ((map.get(v) & 1) == 1) {
                return v;
            }
        }
        return 0;
    }

    /**
     * 生成随机数组
     *
     * @return
     */
    public static int[] randomArray(int maxKinds, int range) {
        int kinds = (int) (Math.random() * maxKinds + 1);
        int oddNum = randomNum(range);
        int[] arr = new int[2 * kinds - 1];
        int index = 0;
        arr[index++] = oddNum;
        HashSet<Integer> set = new HashSet<>();
        set.add(oddNum);
        kinds--;
        while (kinds != 0) {
            int num;
            do {
                num = randomNum(range);
            } while (set.contains(num));
            set.add(num);
            for (int i = 0; i < 2; i++) {
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

    /**
     * 数组中有两个奇数数量不同的数，剩下的数全是偶数个
     *
     * @param arr
     * @return
     */
    public static int printTwoOddTimesNum(int[] arr) {
        int eor = 0;
        for (int v : arr) {
            eor ^= v;
        }
        int anther = 0;
        // a 和 b 是两种数
        // eor != 0
        // eor 最右侧的1，提取出来
        // 一个数 & 自己的负数，就会只留下二进制位最右边的 1, 其它位都为 0
        // 10110100
        // 01001100
        // 00000100
        // 取出两个奇数异或后的结果最右边的 1，这位上的 1 肯定属于某一个奇数
        int rightOne = eor & (-eor);
        for (int i = 0; i < arr.length; i++) {
            // 通过其中某一个数的某一位的 1，提取出其中一个奇数
            // 不用管偶数个的数，因为 异或运算 他们的结果始终为 0
            if ((arr[i] & rightOne) != 0) {
                anther ^= arr[i];
            }
        }
        return anther + (anther ^ eor);
    }

    public static int testPrintTwoOddTimesNum(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }
        int ans = 0;
        for (int v : map.keySet()) {
            if ((map.get(v) & 1) == 1) {
                ans += v;
            }
        }
        return ans;
    }

    /**
     * 生成随机数组
     *
     * @return
     */
    public static int[] randomArray2(int maxKinds, int range) {
        int kinds = (int) (Math.random() * maxKinds + 2);
        int[] arr = new int[2 * kinds - 2];
        int index = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 2; i++) {
            int oddNum = randomNum(range);
            arr[index++] = oddNum;
            set.add(oddNum);
            kinds--;
        }

        while (kinds != 0) {
            int num;
            do {
                num = randomNum(range);
            } while (set.contains(num));
            set.add(num);
            for (int i = 0; i < 2; i++) {
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
        int maxKinds = 10;
        int range = 200;
        int testTimes = 1000000;

        System.out.println("Test Begin:");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxKinds, range);
            int v1 = printOddTimesNum(arr);
            int v2 = testOddTimesNum(arr);
            if (v1 != v2) {
                printArray(arr);
                System.out.println("v1 = " + v1 + ", v2 = " + v2);
            }
            int[] arr2 = randomArray2(maxKinds, range);
            int n1 = printTwoOddTimesNum(arr2);
            int n2 = testPrintTwoOddTimesNum(arr2);
            if (n1 != n2) {
                printArray(arr2);
                System.out.println("n1 = " + n1 + ", n2 = " + n2);
            }
        }
        System.out.println("Test End.");
    }
}
