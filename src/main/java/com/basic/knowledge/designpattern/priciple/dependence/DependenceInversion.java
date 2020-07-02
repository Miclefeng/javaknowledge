package com.basic.knowledge.designpattern.priciple.dependence;

/**
 * 依赖倒置
 * 1、高层模块不应该依赖低层模块，二者都应该依赖其抽象
 * 2、抽象不应该依赖细节，细节应该依赖抽象
 * 3、中心思想是面向接口编程
 * 4、抽象的东西更稳定(接口/抽象类)
 * 5、使用接口或抽象类定制好规范，把细节下放到实现类
 *
 * 三种传递方式
 * 1、接口
 * 2、构造方法
 * 3、setter方式
 */
public class DependenceInversion {

    public static void main(String[] args) {
        Person person = new Person();
        // 接口传递方式
        person.receive(new Email());
        person.receive(new Wechat());
    }
}

interface IReceiver {

    String getInfo();
}

class Email implements IReceiver {

    @Override
    public String getInfo() {
        return "Email content: Hello email!";
    }
}

class Wechat implements IReceiver {

    @Override
    public String getInfo() {
        return "Wechat content: Hello wechat!";
    }
}

class Person {
    /**
     * 对接口的依赖
     * @param receiver
     */
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}
