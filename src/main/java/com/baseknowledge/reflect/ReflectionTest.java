package com.baseknowledge.reflect;

import org.junit.jupiter.api.Test;

/**
 * @author miclefengzss
 * @create 2020-07-28 3:04 下午
 */

public class ReflectionTest {

    @Test
    /**
     * java.lang.Class
     * 1、类的加载过程：
     *  程序经过 javac 命令以后，会生成一个或多个字节码文件(.class结尾的文件)
     *  接着经过 java 命令对某个字节码文件进行解释运行。相当于将字节码文件加入到内存，此过程成为类的加载。
     *  加载到内存中的类，被称为运行时类，此运行时类就是 Class 的一个实例
     *
     *  2、Class 的实例就是一个运行时类
     *
     *  3、加载到内存中的运行时类，会缓存一段时间。在此期间获取到的都是同一个类的实例
     */
    public void test01() throws ClassNotFoundException {
        // 1、调用类运行时属性
        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        // 2、通过运行时对象，调用 getClass()
        Person person = new Person("Tom", 24);
        Class<? extends Person> personClass2 = person.getClass();
        System.out.println(personClass2);

        // 3、调用 Class 的静态方法 forName(String 类的全称(包括包路径))
        Class<?> personClass3 = Class.forName("com.baseknowledge.reflect.Person");
        System.out.println(personClass3);

        // 4、使用类加载器 ClassLoader()
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> personClass4 = classLoader.loadClass("com.baseknowledge.reflect.Person");
        System.out.println(personClass4);
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
