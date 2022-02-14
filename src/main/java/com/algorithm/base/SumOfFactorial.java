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

    public static void main(String[] args) {
        System.out.println(f(10));
    }
}
