package com.basic.knowledge.reflect;

public class Application {

    public static void main(String[] args) {
        String s = "hello world.";
        ClassUtil classUtilString = new ClassUtil(s);
        classUtilString.printClassMessage();
        classUtilString.printFieldMessage();
        classUtilString.printConstructorMessage();

        Integer i = new Integer(1);
        ClassUtil classUtilInteger = new ClassUtil(i);
        classUtilInteger.printClassMessage();
        classUtilInteger.printFieldMessage();
        classUtilInteger.printConstructorMessage();
    }
}
