package com.threadcoreknowledge.juc.threadlocal;

import java.lang.ref.SoftReference;

/**
 * @author miclefengzss
 * 2021/12/13 下午2:33
 */
public class T02_SoftReference {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());

        System.gc(); // 调用 gc 之后不会立即回收

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        // 再分配一个数组，heap将装不下去了，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
