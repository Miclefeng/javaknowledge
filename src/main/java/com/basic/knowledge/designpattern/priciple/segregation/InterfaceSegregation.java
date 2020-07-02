package com.basic.knowledge.designpattern.priciple.segregation;

/**
 * 接口隔离原则
 * 一个类对另一个类的依赖应该建立在最小接口上，将接口拆分为几个
 * 独立的接口以实现隔离
 */
public class InterfaceSegregation {

    public static void main(String[] args) {
        A a = new A();
        a.dependy1(new B());
        a.dependy2(new B());
    }
}

/**
 * 需要将 interface 拆分成多个
 */
interface interface1 {
    void method1();

    void method2();

    void method3();
}

interface interface2 {

    void method4();

    void method5();
}

class B implements interface1, interface2 {

    @Override
    public void method1() {
        System.out.println("B implement method1");
    }

    @Override
    public void method2() {
        System.out.println("B implement method2");
    }

    @Override
    public void method3() {
        System.out.println("B implement method3");
    }

    @Override
    public void method4() {
        System.out.println("B implement method4");
    }

    @Override
    public void method5() {
        System.out.println("B implement method5");
    }
}

class A {
    // A 类通过interface1 依赖B，但只会使用1,2,3 方法
    public void dependy1(interface1 i) {
        i.method1();
    }

    public void dependy2(interface2 i) {
        i.method4();
    }
}