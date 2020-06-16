package com.threadcoreknowledge.startthreads;

/**
 * 不能两次调用 start 方法，否则会报错
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();

        // start 在启动线程的时候会检查线程状态，如果不为初始值就会报异常
        // 线程执行完会更新线程状态为终止状态

        /**
         * Start 执行的流程
         * 1、检查线程状态，只有在 NEW 状态下的线程才能继续执行，否则抛出异常(IllegalThreadStateException)
         * 2、把线程加入线程组
         * 3、调用 start0() 方法启动线程
         */
        thread.start();
//        thread.start();
    }
}
