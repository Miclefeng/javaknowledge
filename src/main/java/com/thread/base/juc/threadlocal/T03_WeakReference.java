package com.thread.base.juc.threadlocal;

import java.lang.ref.WeakReference;

/**
 * @author miclefengzss
 * 2021/12/13 上午11:35
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc(); // 一调用gc 弱引用就回被回收
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
