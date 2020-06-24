package com.leetcode.easy;

/**
 * 辗转相除法求两个数据的最大公约数
 */
public class MaximumCommonDivisor {

    public static void main(String[] args) {
        System.out.println(getMaximumCommonDivisor(20, 30));
    }

    private static int getMaximumCommonDivisor(int a, int b) {
        int t;
        do {
            t = a % b;
            a = b;
            b = t;
            System.out.println("a=" + a + ", b=" + b + ", t=" + t);
        } while (t > 0);
        return a;
    }
}
