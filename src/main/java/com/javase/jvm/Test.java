package com.javase.jvm;

/**
 * @author miclefengzss
 * 2022/8/1 下午4:42
 */
public class Test {

    public static void main(String[] args) {
        String s3 = "yzt";       // 常量池中以后的字符串不会重复生成，直接返回
        String s1 = new String("yzt");
        // 当调用 intern方法时，如果常量池已经包含一个等于此String对象的字符串，则返回池中的字符串。否则，将intern返回的引用指向当前字符串
        String s2 = s1.intern(); // 返回的常量池中的字符串
        System.out.println(s1 == s2); //false
        System.out.println(s2 == s3); // true
        System.out.println(s1 == s3); // false
    }
}
