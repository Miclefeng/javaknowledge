package com.javase.base.object.polymorphism;

/**
 * @author miclefengzss
 * @create 2021-03-09 上午10:40
 */

public class Cat extends Animal {

    @Override
    public void eat() {
        System.out.println("Cat eat.");
    }

    public void run() {
        System.out.println("Cat run.");
    }
}
