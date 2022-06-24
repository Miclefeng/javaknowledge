package com.javase.base.lambda;

import java.util.function.Consumer;

/**
 * @author miclefengzss
 * 2022/6/22 下午8:26
 */
public class ConsumerLambda {

    public static void main(String[] args) {
        consumerString(System.out::println);
        consumerString2(s -> System.out.println(s.toUpperCase()), s -> System.out.println(s.toLowerCase()));
    }

    public static void consumerString(Consumer<String> func) {
        func.accept("Hello");
    }

    public static void consumerString2(Consumer<String> func, Consumer<String> func2) {
        func.andThen(func2).accept("World");
    }
}
