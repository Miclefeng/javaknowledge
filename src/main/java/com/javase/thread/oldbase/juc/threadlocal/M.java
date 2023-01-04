package com.javase.thread.oldbase.juc.threadlocal;


/**
 * @author miclefengzss
 * 2021/12/13 上午11:36
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
