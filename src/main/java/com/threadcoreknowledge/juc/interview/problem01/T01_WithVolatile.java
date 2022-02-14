package com.threadcoreknowledge.juc.interview.problem01;

import java.util.LinkedList;
import java.util.List;

/**
 * @author miclefengzss
 * 2021/12/7 下午2:36
 */
public class T01_WithVolatile {

    /**
     * volatile 不要修饰引用类型
     */
    volatile List<Integer> list = new LinkedList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final T01_WithVolatile t01WithVolatile = new T01_WithVolatile();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t01WithVolatile.add(i);
                System.out.println("add " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (t01WithVolatile.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }).start();
    }
}
