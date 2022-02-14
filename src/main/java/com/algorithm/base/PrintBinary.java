package com.algorithm.base;

/**
 * @author miclefengzss
 * 2021/12/16 上午9:50
 */
public class PrintBinary {

    /**
     * 打印int类型的二进制表示
     *
     * @param num
     */
    private static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int a = 8;
        print(a);

        int b = -5;
        int c = ~b + 1; // 一个数的负数等于这个数取反+1
        print(b);
        print(c);
    }
}
