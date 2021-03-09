package com.baseknowledge.lambda;

import java.util.function.IntConsumer;

public class Repeat {

    public static void main(String[] args) {
        Repeat repeat = new Repeat();
        repeat.repeatRunnable(10, () -> System.out.println("Hello World!"));
        repeat.repeatConsumer(10, i -> System.out.println("Countdown: " + (9 - i)));
    }

    public void repeatRunnable(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }

    public void repeatConsumer(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }
}
