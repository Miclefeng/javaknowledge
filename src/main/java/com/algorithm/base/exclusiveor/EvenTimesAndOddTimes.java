package com.algorithm.base.exclusiveor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author miclefengzss
 * 2022/3/17 上午10:22
 */
public class EvenTimesAndOddTimes {

    /**
     * arr中，只有一种数，出现奇数次
     *
     * @param arr
     */
    public static int printOddTimesNum(int[] arr) {
        int eor = 0;
        for (int value : arr) {
            eor ^= value;
        }
        return eor;
    }

    public static int test1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if ((map.get(num) & 1) == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static int[] randomArray1(int maxKinds, int range) {
        int oddTimesNum = randomRangeNumber(range);

        int numKinds = (int) (Math.random() * maxKinds) + 1;
        int index = 0;
        int[] arr = new int[2 * numKinds - 1];
        arr[index++] = oddTimesNum;
        numKinds--;

        HashSet<Integer> set = new HashSet<>();
        set.add(oddTimesNum);
        while (numKinds > 0) {
            int curNum;
            do {
                curNum = randomRangeNumber(range);
            } while (set.contains(curNum));

            set.add(curNum);
            numKinds--;
            for (int i = 0; i < 2; i++) {
                arr[index++] = curNum;
            }
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
     * arr中，有两种数，出现奇数次
     *
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int anther = 0;

        // a 和 b 是两种数
        // eor != 0
        // eor 最右侧的1，提取出来
        // 一个数 & 自己的负数，就会只留下二进制位最右边的 1, 其它位都为 0
        int rightOne = eor & (-eor);

        for (int i = 0; i < arr.length; i++) {
            // 两个奇数中其中一个数的最右位肯定是 1
            if ((arr[i] & rightOne) != 0) {
                // 提取出其中一个奇数
                anther ^= arr[i];
            }
        }

        System.out.println(anther + "   " + (anther ^ eor));
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
//        int[] arr = new int[]{8, 6, 3, 5, 5, 7, 3, 6, 8, 9, 10, 12, 9, 12, 10};
//        printOddTimesNum(arr);

//        int[] arr2 = new int[]{7, 3};
//        printOddTimesNum2(arr2);

        int maxKinds = 20;
        int range = 3000;
        int testTimes = 1000000;

        System.out.println("Test Begin:");
        for (int i = 0; i < testTimes; i++) {
            int[] array1 = randomArray1(maxKinds, range);
            final int num1 = printOddTimesNum(array1);
            final int num2 = test1(array1);
            if (num1 != num2) {
                printArray(array1);
                System.out.println(num1);
                System.out.println(num2);
                System.out.println("error.");
                break;
            }
        }
        System.out.println("Test End.");
    }
}
