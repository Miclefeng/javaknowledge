package com.javase.thread.base.container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author miclefengzss
 * 2023/1/9 下午4:10
 */
public class CollectionSynchronizedHashMap {

    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<>());

    static int count = 1000000;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = 100;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MapPutRunnable implements Runnable {

        int start;
        int gap = count / THREAD_COUNT;

        public MapPutRunnable(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    static class MapGetRunnable implements Runnable {

        int start;
        int gap = count / THREAD_COUNT;

        public MapGetRunnable(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.get(keys[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            int begin = i * (count / THREAD_COUNT);
            threads[i] = new Thread(new MapPutRunnable(begin));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("put used: " + (end - start));
        System.out.println("m size: " + m.size());

        System.out.println("==============================");

        start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new MapGetRunnable(i * (count / THREAD_COUNT)));
        }
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("get used: " + (end - start));
    }
}
