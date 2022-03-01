package com.algorithm.base.bitmap;

/**
 * @author miclefengzss
 * 2022/2/25 下午2:58
 */
public class BitAddMinusMultiDiv {

    /**
     * 加法 = 无进位相加(x ^ y) + 进位信息((x & y)<<1)
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) { // 一直加到没有进位信息
            sum = a ^ b; // a 和 b 无进位相加
            b = (a & b) << 1; // a 和 b 相加的进位信息
            a = sum;
        }
        return sum;
    }

    public static int negNum(int n) {
        return add(~n, 1);
    }

    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * b 如果为 0，直接返回结果 0
     * 如果 b 的最后一位有 1，结果 + a 的值，然后 a 左移一位，b 右移一位，然后重复 b 的最后一位有 1， 结果累加 a 的值
     *
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) { // 一直到 b 为 0
            if ((b & 1) != 0) { // 如果 b 的最后一位为1，结果累加 a 的值
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i--) {
            if ((x >> i) >= y) {
                res |= 1 << i;
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                // (a + 1) / b = c
                // a - (b * c) = d
                // d / b = e
                // c + e = res
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

    public static boolean isNeg(int num) {
        return num < 0;
    }

    public static void main(String[] args) {
        System.out.println(add(2, 3));
        System.out.println(negNum(5));
        System.out.println(minus(5, 2));
        System.out.println(multi(10, 5));
        System.out.println(div(10, 3));
        System.out.println(divide(Integer.MIN_VALUE, 1000));
        System.out.println(Integer.MIN_VALUE / 1000);
    }
}
