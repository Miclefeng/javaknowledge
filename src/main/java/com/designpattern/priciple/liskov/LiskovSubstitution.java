package com.designpattern.priciple.liskov;

/**
 * 里氏替换
 * 1、使用继承时，在子类中尽量不要重写父类的方法
 * 2、通过聚合、组合、依赖来解决继承的耦合问题
 * <p>
 * 解决方法
 * 原有的父类和子类都继承一个更通俗的类，原有的继承关系去掉，采用
 * 依赖、聚合、组合等关系替代
 */
public class LiskovSubstitution {

    public static void main(String[] args) {
        A a = new A();
        System.out.println("3 + 6 = " + a.func1(3, 6));
        System.out.println("-----------------");
        B b = new B();
        System.out.println("4 - 5 = " + b.func1(4, 5));

        // 使用组合
        System.out.println("11 + 3 = " + b.func3(11, 3));
    }
}

class Base {
    // 把更基础的方法和域放到Base类
}

class A extends Base {

    public int func1(int a, int b) {
        return a + b;
    }
}

class B extends Base {

    /**
     * 使用组合关系实现B需要使用A类的方法
     */
    private A a = new A();

    public int func1(int a, int b) {
        return a - b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}
