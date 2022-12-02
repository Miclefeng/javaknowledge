package com.javase.thread.base.createthreads;

/**
 * 使用 Runnable 方式实现线程
 * @author miclefengzss
 */
public class RunnableStyle implements Runnable {

    /**
     * 创建线程的方式：
     *  1、实现Runnable接口并传入Thread类
     *  2、继承Thread类然后重写run()
     *  两种方式都是构造Thread()调用start()方法来新建线程，方式一最终调用target.run();方式二重写run()方法
     *
     * 继承 Thread 类是不推荐的
     * 1、从代码的架构角度：具体的任务(run 方法) 应该和“创建和运行线程的机制(Thread类)”解耦，用Runnable对象可以实现解耦
     * 2、使用继承 Thread 类的方式的话，那么每次想新建一个任务，只能新建立一个独立的线程，而这样做的损耗会比较大(比如重头开始创建一个线程、执行完毕以后再销毁等。如果线程的实际工作内容，也就是 run() 函数里只是简单的打印一行文字的话，那么可能线程的实际工作内容还是不如损耗来的大)。如果使用 Runnable 和 线程池，就可以大大减小这样的损耗。
     * 3、继承 Thread 类后，由于 java 不支持双继承，这样就无法再继承其他的类，限制了可扩展性。
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("使用Runnable方法实现线程");
    }
}
