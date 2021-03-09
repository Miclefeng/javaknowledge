package com.baseknowledge.streamapi;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author miclefengzss
 * @create 2020-07-29 8:48 上午
 * <p>
 * Stream 是对数据的计算，CPU密集型
 * 1、Stream 不会自己存储元素
 * 2、Stream 不会改变源对象
 * 3、Stream 操作是链式、延迟的，提交后执行
 * 4、执行完产生结果，无法重复使用，需要重新创建 Stream
 */

public class StreamApiTest {


    @Test
    public void test01() {
        /**
         * 通过 Collection 获取 stream
         */
        List<Person> personList = PersonData.getPerson();

        // default Stream<E> stream(); 返回一个顺序流
        Stream<Person> stream = personList.stream();

        // filter(Predicate p); 接收 lambda 表达式，从流中过滤元素
        stream.filter(p -> p.getSalary() >= 4000).forEach(System.out::println);
        System.out.println("------------------------------------------------------");
        // limit(long n); 限制返回元素个数
        personList.stream().limit(2).forEach(System.out::println);
        System.out.println("------------------------------------------------------");
        // skip(long n); 跳过几个元素
        personList.stream().skip(3).forEach(System.out::println);
        System.out.println("------------------------------------------------------");
        // distinct(); 根据 hashCode() 和 equals() 去重
        personList.stream().distinct().forEach(System.out::println);
        System.out.println("======================================================");
        // map(Function f); 流中的每个元素都被 function 作用,并把元素返回
        personList.stream().map(Person::getName).filter(name -> name.length() > 6).forEach(System.out::println);
        // flatMap(Function f); 接收一个函数作为参数，将流中的每个元素转为为另一个流

        // default Stream<E> parallelStream(); 返回一个并行流
        Stream<Person> parallelStream = personList.parallelStream();

        /**
         * 通过 [] 获取 stream
         */
        // Arrays 的 static <T> Stream<T> stream(T[] array); Arrays.stream(); 方法
        Stream<Person> personStream = Arrays.stream(new Person[]{new Person("PonyMa", 43, 3000), new Person("JackMa", 53, 2800)});

        /**
         * 通过 Stream.of() 创建
         */
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

        System.out.println("*******************************************************");
        /**
         * 创建无限流
         */
        Stream.iterate(0, t -> t + 2).limit(5).forEach(System.out::println);
    }
}

class PersonData {

    public static List<Person> getPerson() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("PonyMa", 43, 4000));
        list.add(new Person("JackMa", 53, 8000));
        list.add(new Person("LeiJun", 50, 2000));
        list.add(new Person("ByteDance", 40, 5000));
        list.add(new Person("PDD", 38, 2800));

        return list;
    }
}


class Person {
    public String name;
    public int age;
    private double salary;

    public Person() {
    }

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}