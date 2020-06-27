package com.basic.knowledge.reflect;

public class Application {

    public static void main(String[] args) {
        String s = "hello world.";
        new ClassUtil(s).printClassMessage();
    }
}
