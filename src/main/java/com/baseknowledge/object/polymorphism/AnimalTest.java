package com.baseknowledge.object.polymorphism;

/**
 * @author miclefengzss
 * @create 2021-03-09 上午10:40
 */

public class AnimalTest {

    public static void main(String[] args) {

        /**
         * 多态可以由向上转型和动态绑定来实现
         *
         * 向上转型：父类类型 父类引用 = new 子类类型(参数);
         * 自动转型，父类引用子类的实例，子类的对象可以赋值给父类的对象
         * 向上转型适用于有多个父子俩共同应用的场景，可以借由父类引用，根据实际需求转换为特定的子类型实现多态
         * 当一个子类向上转型为父类以后，就被当成了父类对象，只能调用子类重写了的父类方法和派生自父类的方法
         * 父类中的静态方法无法被重写，所有向上转型只能调用父类的静态方法
         *
         * 绑定机制：
         * 1、静态绑定：程序在运行之前进行绑定(由编译器和链接程序完成)，也叫前期绑定
         * 2、动态绑定：程序在运行过程中由JVM根据对象的类型自动判断调用哪个方法，也叫后期绑定
         *
         * 向下转型：子类类型 子类引用 = (子类类型) 父类引用
         * 向下转型是为了获取由向上转型而丢失的子类特性而存在，通行需要先向上转型，在配合 instanceof 使用
         * 子类引用指向父类对象，必须进行强制转换，可以调用子类特有的方法
         */

        Animal a1 = new Dog();
        Animal a2 = new Cat();

        a1.eat();
        a2.eat();

        if (a1 instanceof Dog) {
           Dog d1 = (Dog) a1;
           d1.run();
        } else if (a2 instanceof Dog) {
            Dog c1 = (Dog) a2;
            c1.run();
        }
    }
}
