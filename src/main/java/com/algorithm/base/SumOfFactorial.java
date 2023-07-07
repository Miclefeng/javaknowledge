package com.algorithm.base;

/**
 * @author miclefengzss
 * 2021/12/16 上午9:59
 */
public class SumOfFactorial {

    /**
     * 求数的阶乘
     * 1! + 2!(1*2) + 3!(1*2*3) + 4!(1*2*3*4) + N!
     * 当前数的阶乘 == 上一个数的阶乘 * 当前数
     * 可以用临时变量保存上一个数的阶乘
     *
     * @return
     */
    public static long f(int n) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= n; i++) {
            cur = cur * i; // 当前数的阶乘 = 上一个数的阶乘 * 当前数
            ans += cur;
        }
        return ans;
    }

    /**
     * 求 n 个斐波那契数的和
     *
     * @param n
     * @return
     */
    public static long foo(int n) {
        System.out.print("1 ");
        if (n <= 1) {
            return 1;
        }

        System.out.print("1 ");
        if (n == 2) {
            return 2;
        }

        int pre = 1;
        int cur = 1;
        int ans = 0;
        int fac;
        for (int i = 2; i < n; i++) {
            fac = pre + cur;
            pre = cur;
            cur = fac;
            ans = ans + fac;
            System.out.printf("%d ", fac);
        }
        System.out.println();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(f(4));
        System.out.println("========================");

        System.out.println(foo(10));
    }
}
