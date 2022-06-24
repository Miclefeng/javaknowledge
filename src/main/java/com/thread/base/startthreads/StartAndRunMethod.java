package com.thread.base.startthreads;

/**
 * 两种方式运行线程
 *
 * @author miclefengzss
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        // 普通方法，覆写了 thread 中的 run 方法
        runnable.run();
        new Thread(runnable).run();

        // 执行启动线程需要通过 start() 方法间接的执行 run() 方法
        new Thread(runnable).start();
    }
}
