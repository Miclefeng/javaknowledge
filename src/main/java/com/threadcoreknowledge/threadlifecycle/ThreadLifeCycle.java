package com.threadcoreknowledge.threadlifecycle;

/**
 * java 线程的 6 中状态
 * 1、New            线程被新建
 * 2、Runnable       线程被 start() 后
 * 3、Blocked        线程只能是被 synchronized 修饰并且 monitor 锁被其它线程拿走
 * 4、Waiting        Object.wait() / Thread.join() / LockSupport.park()
 * 5、Timed Waiting  Thread.sleep(time) / Object.wait(time) / Thread.join(time) / LockSupport.parkNanos(time) / LockSupport.parkUntil(time)
 * 6、Terminated     线程被终止
 */
public class ThreadLifeCycle {
}
