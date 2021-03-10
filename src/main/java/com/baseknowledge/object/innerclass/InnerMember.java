package com.baseknowledge.object.innerclass;

/**
 * @author miclefengzss
 * @create 2021-03-09 下午10:47
 */

public class InnerMember {

    public int age = 20;

    public void work() {
        System.out.println(new Inner().age);
        System.out.println("in outer class.");
    }

    /**
     * 内部类：
     * 1、成员内部类
     * 1) 内部类在外部使用时，无法直接实例化，需要借助外部类才能完成实例化;
     * 外部类.内部类 内部类引用 = new 外部类().new 内部类();
     * 2) 内部类可以直接访问外部类的属性、方法，如果有重名根据就行访问原则优先访问内部类定义的
     * 3) 可以使用 外部类.this.成员，访问外部类同名的信息
     * 4) 外部类无法直接访问内部类的成员方法，需要实例化内部类才行
     * <p>
     * 2、静态内部类
     * 1) 静态内部类实例化时，不需要依赖外部类;  外部类.内部类 内部类引用 = new 外部类.内部类();
     * 2) 静态内部类只能访问外部类的静态成员，如果需要访问非静态成员，需要实例化外部类
     * <p>
     * 3、方法内部类(局部内部类)
     * 1) 定义在方法内部，作用范围也在方法内
     * 2) 类中不能包含静态成员
     * 3) class 不可以添加修饰符，类中可以添加 final 、 abstract 修饰符
     * <p>
     * 4、匿名内部类
     * 1) 匿名内部类没有类型名称和实例对象名称
     * 2) class 无法使用修饰符
     * 3) 类中不能包含静态成员，无法编写构造方法，可以添加构造代码块
     * 4) 可以实现接口也可以继承父类，但不可兼得
     */
    class Inner {

        public int age = 30;

        public int weight = 150;

        public void work() {
            InnerMember.this.work();
            System.out.println(InnerMember.this.age);
            System.out.println("in inner class.");
        }
    }

    public Inner getInnerClass() {
        return new Inner();
    }
}


class InnerClassTest {

    public static void main(String[] args) {
        InnerMember.Inner inner = new InnerMember().new Inner();
        inner.work();
        new InnerMember().getInnerClass().work();
    }
}
