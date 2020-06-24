package com.basic.knowledge;

public class StringTest {

    public static void main(String[] args) {

        String hello = "hello world!";
        // "hello world!" 存储在常量池中， new String() 是 Heap 中的 String 对象
        String hello1 = new String("hello world!");
        System.out.println(hello == hello1); //1

        String hello2 = "hello world!";
        System.out.println(hello == hello2); //2

        // 通过常量表达式运算得到的字符串是在编译时计算得出的，并且之后会将其当作字符串常量对待；
        String append = "hello" + " world!";
        System.out.println(hello == append); //3

        final String pig = "length: 10";
        // 在运行时通过连接运算得到的字符串是新创建的
        final String dog = "length: " + pig.length();
        System.out.println(pig == dog); //4

        // intern 方法会从字符串常量池中查询当前字符串是否存在，若不存在就会将当前字符串放入常量池中
        final String dog1 = ("length: " + pig.length()).intern();
        System.out.println(pig == dog1); //5

        // + 的操作优先级高于 ==
        // "xxx" + pid == dog  => 用()来改写   ("xxx" + pid) == dog
        System.out.println("Animals are equal: " + pig == dog1);//6
    }
}
