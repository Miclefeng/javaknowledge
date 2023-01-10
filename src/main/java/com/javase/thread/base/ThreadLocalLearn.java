package com.javase.thread.base;

/**
 * @author miclefengzss
 * 2023/1/9 下午2:14
 */
public class ThreadLocalLearn {

     static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            millisSleep(2000);
            System.out.println(Thread.currentThread().getName() + " tl get " + threadLocal.get());
        }, "t1");

        Thread t2 = new Thread(() -> {
            millisSleep(1000);
            threadLocal.set(new Person());
            System.out.println(Thread.currentThread().getName() + " tl set Person");
            millisSleep(3000);
            threadLocal.remove();
        }, "t2");

        t1.start();

        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void millisSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person {

        private String name;
        private int age;

        public Person() {
        }
    }
}
