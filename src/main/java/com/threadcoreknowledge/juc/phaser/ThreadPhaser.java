package com.threadcoreknowledge.juc.phaser;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 分阶段的栅栏，所有线程都完成后进入下一个阶段或者退出
 *
 * @author miclefengzss
 * 2021/12/6 下午5:23
 */
public class ThreadPhaser {

    static Random r = new Random();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
