package com.javase.jvm.bytecode;

/**
 * @author miclefengzss
 * 2022/1/6 上午9:27
 */
public class ByteCode01 {

    private static volatile int number = 0;

    public static void main(String[] args) {
        /**
         * 0 new #2 <com/javase/jvm/bytecode/ByteCode01>   实例化对象并对成员变量赋予初始值
         * 3 dup
         * 4 invokespecial #3 <com/javase/jvm/bytecode/ByteCode01.<init> : ()V>   执行对象构造方法，并对成员变量赋值
         * 7 astore_1  将实例对象赋值给 变量
         * 8 return
         *
         * 如果不禁止指令重排序，在高并发的情况下，4 和 7 可能进行指令重排序，导致其中的一个线程拿到半初始化状态的对象(成员变量只是被赋予默认值，而没有正确赋值)
         */
        ByteCode01 byteCode01 = new ByteCode01();
    }
}
