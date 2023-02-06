package com.javase.thread.base.pool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author miclefengzss
 * 2023/2/3 下午3:59
 */
public class MyCompletableFuture {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(MyCompletableFuture::projectTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(MyCompletableFuture::projectTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(MyCompletableFuture::projectJD);

        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();

        long end = System.currentTimeMillis();
        System.out.println("used millis time: " + (end - start));
    }

    public static Double projectTM() {
        delay();
        return 1.0;
    }

    public static Double projectTB() {
        delay();
        return 2.0;
    }

    public static Double projectJD() {
        delay();
        return 3.0;
    }

    public static void delay() {
        Random random = new Random();
        int nextInt = random.nextInt(500);
        System.out.println(nextInt);
        try {
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
