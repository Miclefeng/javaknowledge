package com.baseknowledge.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author miclefengzss
 * 2021/4/13 下午10:26
 */
public class PredicateConsumerFunction {

    public static void main(String[] args) {
        /**
         * Predicate<T> 用于条件判断，固定返回 Boolean 值
         */
        Predicate<Integer> predicate = n -> (n & 1) == 1;
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> numbers = Arrays.asList(arr);
        filter(numbers, predicate);
        System.out.println();
        System.out.println("===========================");
        /**
         * Consumer<T> 有一个输入参数无返回值的函数
         */
        output(s -> System.out.println("console: " + s));
        output(s -> System.out.println("network: " + s));
        System.out.println("===========================");
        /**
         * Function<T,R> 有一个输入参数且有返回值的函数
         */
        Function<Integer, String> randomStringFunction = l -> {
            String seeds = "abcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < l; i++) {
                int pos = random.nextInt(seeds.length());
                stringBuilder.append(seeds.charAt(pos));
            }
            return stringBuilder.toString();
        };
        System.out.println(randomStringFunction.apply(32));
    }

    public static void filter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer num : list) {
            if (predicate.test(num)) {
                System.out.print(num + " ");
            }
        }
    }

    public static void output(Consumer<String> consumer) {
        String text = "this is a test";
        consumer.accept(text);
    }
}
