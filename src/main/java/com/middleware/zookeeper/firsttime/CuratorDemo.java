package com.middleware.zookeeper.firsttime;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/22 14:07
 */
public class CuratorDemo {


    /**
     * 1 1 2 3 5 8 7
     *
     * @param n
     * @return
     */
    public static long fib(int n) {
        long a = 1;
        long b = 1;
        long count = 1;

        for (; n > 2; n--) {
            count = a + b;
            a = b;
            b = count;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(5));
        System.out.println(fib(6));
        System.out.println(fib(7));
        System.out.println(fib(8));
        System.out.println(fib(50));
    }
}
