package com.javase.base.string;

/**
 * @author miclefengzss
 * @create 2021-03-11 上午9:59
 */

public class StringDemo {

    public static void main(String[] args) {
        /**
         *
         * Integer 类型的  -128 ~ 127 存储在常量池中
         *
         * String 是不可变的，一旦创建，其指向的空间值是不可变的
         * 所谓的修改是创建了新的对象(开辟了新的空间存储)，将原来的变量指向新的空间，原来的空间不动
         * 在大量操作字符串的时候，会产生很多中间变量、常量池中很多废弃的变量，浪费内存空间
         */
        String hello = "hello world!";
        // "hello world!" 存储在常量池中， new String() 是 Heap 中的 String 对象
        String hello1 = new String("hello world!");
        // false
        System.out.println("hello == hello1 : " + (hello == hello1));

        String hello2 = "hello world!";
        // true
        System.out.println("hello == hello2 : " + (hello == hello2));

        // 通过常量表达式运算得到的字符串是在编译时计算得出的，并且之后会将其当作字符串常量对待；
        String append = "hello" + " world!";
        // true
        System.out.println("hello == append : " + (hello == append));

        final String pig = "length: 10";
        // 在运行时通过连接运算得到的字符串是新创建的
        // false
        final String dog = "length: " + pig.length();
        System.out.println("pig == dog : " + (pig == dog));

        // intern 方法会从字符串常量池中查询当前字符串是否存在，若不存在就会将当前字符串放入常量池中
        final String dog1 = ("length: " + pig.length()).intern();
        // true
        System.out.println("pig == dog1 : " + (pig == dog1));
    }
}
