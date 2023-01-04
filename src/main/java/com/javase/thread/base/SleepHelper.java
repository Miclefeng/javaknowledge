package com.javase.thread.base;

/**
 * @author miclefengzss
 * 2022/12/26 下午2:58
 */
public class SleepHelper {

    public static void sleepSecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
