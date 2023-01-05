package com.javase.thread.base;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2023/1/3 上午10:32
 */
public class CountDownLatchCyclicBarrierPhaserExchanger {

    static Random r = new Random();

    static MarriagePhaser marriagePhaser = new MarriagePhaser();

    public static void main(String[] args) {
        usingCountDownLatch();
        System.out.println("=================");
        usingJoin();
        System.out.println("=================");
        usingCyclicBarrier();
        System.out.println("=================");
        usingPhaser();
        System.out.println("=================");
        milliSleep(5000);
        usingExchanger();
    }

    public static void usingCountDownLatch() {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                latch.countDown();
//                System.out.println(result);
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CountDownLatch used time : " + (System.currentTimeMillis() - start));
    }

    public static void usingJoin() {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
//                System.out.println(result);
            });
        }

        for (Thread t : threads) {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("join used time : " + (System.currentTimeMillis() - start));
    }

    public static void usingCyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满人，发车"));

        for (int i = 0; i < 60; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void usingPhaser() {
        // 注册的线程数
        marriagePhaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    private static void usingExchanger() {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange("T2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }

    static class Person implements Runnable {

        private String name;

        public Person(String name) {
            this.name = name;
        }

        private void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 到达现场。");
            marriagePhaser.arriveAndAwaitAdvance();
        }

        private void eat() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 开始吃饭。");
            marriagePhaser.arriveAndAwaitAdvance();
        }

        private void leave() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 离开了。");
            marriagePhaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if ("新郎".equals(name) || "新娘".equals(name)) {
                milliSleep(r.nextInt(1000));
                System.out.println("洞房" + name);
                marriagePhaser.arriveAndAwaitAdvance();
            } else {
                marriagePhaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    static class MarriagePhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了，" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了，" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了，" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束，新郎新娘拥抱。" + registeredParties);
                    System.out.println();
                    return true;
                default:
                    return false;
            }
        }
    }

    public static void milliSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
