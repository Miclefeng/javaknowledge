package com.algorithm.base.random;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author miclefengzss
 * 2022/2/19 下午12:44
 */
public class EqualProbability {

    public static void main(String[] args) throws IOException {

        // 等概率返回 [0,1) 范围的数；计算机的小数都是有精度的所有可以做到等概率
        final double ans = Math.random();

        int testTime = 10000000;
        int count = 0;
        for (int i = 0; i < testTime; i++) {
            if (Math.random() < 0.5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTime);

        System.out.println("=================");

        // [0,1) * 8 -> [0, 8)
        count = 0;
        for (int i = 0; i < testTime; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTime);

        System.out.println("=================");

        int k = 9;
        // [0, 1) * k -> [0,k) -> int [0, k - 1]
        int[] arr = new int[9];
        for (int i = 0; i < testTime; i++) {
            int r = (int) (Math.random() * k);
            arr[r]++;
        }
        for (int i = 0; i < k; i++) {
            System.out.println(i + " 出现次数 : " + arr[i]);
        }

        System.out.println("=====================");

        // [0, 1) * x -> [0, x)  =>  x^2
        count = 0;
        double x = 0.3;
        for (int i = 0; i < testTime; i++) {
            if (xToPower2() < x) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTime);
        System.out.println(Math.pow(x, 2));

        System.out.println("=====================");

        // 1-5 等概率随机转成 1-7 等概率随机
        count = 0;
        for (int i = 0; i < testTime; i++) {
            if (f2() == 0) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTime);

        System.out.println("=====================");

        int[] s = new int[8];
        for (int i = 0; i < testTime; i++) {
            int r = g();
            s[r]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + " 出现次数 : " + s[i]);
        }

        System.out.println("=====================");

        count = 0;
        for (int i = 0; i < testTime; i++) {
            if (y() == 1) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTime);
    }

    /**
     * 1-5 等概率随机
     *
     * @return
     */
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 1-5 等概率随机转成 0,1 等概率
     *
     * @return
     */
    public static int f2() {
        int ans;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * 获取 0 - 7 等概率随机
     *
     * @return
     */
    public static int f3() {
        return (f2() << 2) + (f2() << 1) + f2();
    }

    /**
     * 0-7 转换成 1-7 等概率随机
     *
     * @return
     */
    public static int g() {
        int ans;
        do {
            ans = f3();
        } while (ans == 0);
        return ans;
    }

    /**
     * x 固定概率返回 0 和 1
     * @return
     */
    public static int x() {
        return Math.random() < 0.8 ? 0 : 1;
    }

    /**
     * y 将 x 固定概率返回 0 和 1，转变为 等概率返回 0 和 1
     * 两次返回如果是   0 ， 1  或者  1，0  才会等概率
     * @return
     */
    public static int y() {
        int ans;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }

    /**
     * 返回[0, 1)上的数
     * 任意 x, x 属于 [0,1), [0, x) 范围上的数出现的概率 x 调整成 x^2
     *
     * @return
     */
    private static double xToPower2() {
        return Math.max(Math.random(), Math.random());
    }
}
