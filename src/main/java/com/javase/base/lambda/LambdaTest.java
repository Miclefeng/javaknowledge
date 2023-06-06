package com.javase.base.lambda;


import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author miclefengzss
 * @create 2020-07-28 10:48 下午
 * <p>
 * lambda 表达式(函数式接口的实例)
 * 如果一个接口中只声明了一个抽象方法，则此接口称为函数式接口(可以在接口上使用 @FunctionalInterface 注解)
 * 接口实现类的对象时，并且接口只有一个抽象方法
 * (o1, o2) -> Integer.compare(o1, o2);
 * -> : lambda 操作符
 * -> 左边 : lambda 形参列表(接口中抽象方法的形参列表)
 * -> 右边 : lambda 体(重写的抽象方法体)
 * <p>
 * 6种情况:
 */

public class LambdaTest {

    @Test
    public void test01() {

        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        Integer res1 = comparator1.compare(32, 12);
//        System.out.println(res1);
//        System.out.println("===============");

        // lambda 表达式
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        Integer res2 = comparator2.compare(32, 12);
//        System.out.println(res2);
//        System.out.println("===============");

        /**
         * 方法引用
         * 当要传递 lambda 体的操作，已经有实现的方法了，就可以使用方法引用
         * 本质上就是 lambda ,也是函数式接口的实例
         * 要求接口中的抽象方法的形参列表和返回类型与方法引用的形参列表和返回类型相同
         * 3种情况：
         * 对象::非静态方法
         * 类::静态方法
         * 类::非静态方法
         */

        // 对象调用实例方法
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("hello world.");
        System.out.println("*********************");
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("very good.");
        Person mike = new Person("mike", 28);
        Supplier<String> supplier = () -> mike.getName();
        System.out.println(supplier.get());
        System.out.println("*********************");
        Supplier<String> supplier1 = mike::getName;
        System.out.println(supplier1.get());
        System.out.println("=====================");

        // 类调用静态方法
        Comparator<Integer> comparator = Integer::compare;
        Integer res = comparator.compare(23, 32);
        System.out.println(res);

        // 类调用非静态方法
        Person tom = new Person("Tom", 29);
        Function<Person, String> function = person -> person.getName();
        System.out.println(function.apply(tom));
        System.out.println("----------------------");
        Function<Person, Integer> function1 = Person::getAge;
        System.out.println(function1.apply(tom));
    }
}


class Person {
    private String name;
    public int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void show() {
        System.out.println("this is a class.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
