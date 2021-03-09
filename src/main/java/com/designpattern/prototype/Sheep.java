package com.designpattern.prototype;

/**
 * 原型模式
 * 1、用原型实例指定创建对象的种类，通过拷贝这些对象的原型创建新的对象
 * 2、通过将一个原型对象传递给要创建的对象，创建的对象通过请求原型对象拷贝它们自己进行创建新的对象
 *
 * 浅拷贝
 * 1、对于数据类型是基本类型的成员变量，浅拷贝会直接进行值拷贝
 * 2、对于数据类型是引用类型的成员变量(数据、类的对象)，浅拷贝会进行引用传递
 * 3、浅拷贝默认使用 clone() 方法来实现
 * 深拷贝
 * 1、复制对象的所有基本类型的成员变量
 * 2、为所有引用类型的成员变量申请存储空间，并复制每个引用类型的成员变量所引用的对象
 * 3、重新 clone() 来实现
 * 4、通过对象的序列化来实现
 *
 * 原型模式有点和注意事项
 * 1、简化对象的创建过程，同时提高效率
 * 2、不用重新初始化对象，动态的获取对象运行时的状态
 * 3、原始对象发生变化，克隆对象也会发生相应的变化，无需更改代码
 * 4、对已有的对象进行改造时，违背了 ocp 原则(注意事项)
 * @author miclefengzss
 */
public class Sheep implements Cloneable {

    private String name;
    private int age;
    private String color;
    private String address;
    public Sheep friends;

    public Sheep() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    protected Sheep clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }
}

class Application {

    public static void main(String[] args) {
        Sheep sheep = new Sheep();
        sheep.setName("tom");
        sheep.setAge(1);
        sheep.setColor("white");
        sheep.setAddress("内蒙古");

        Sheep friends = new Sheep();
        friends.setName("friends");
        sheep.friends = friends;

        Sheep sheep2 = sheep.clone();
        Sheep sheep3 = sheep.clone();
        Sheep sheep4 = sheep.clone();
        Sheep sheep5 = sheep.clone();

        System.out.println(sheep2 + ", sheep2.friends=" + sheep2.friends.hashCode());
        System.out.println(sheep3 + ", sheep3.friends=" + sheep3.friends.hashCode());
        System.out.println(sheep4 + ", sheep4.friends=" + sheep4.friends.hashCode());
        System.out.println(sheep5 + ", sheep5.friends=" + sheep5.friends.hashCode());
    }
}
