package com.designpattern.pattern.old.builder;

/**
 * 建造者模式
 * 1、建造者模式又叫生成器模式，是一种对象构建模式，它可以将复杂对象的构建过程抽象出来，使这个抽象过程的不同实现方法
 * 可以构造出不同的(表现)对象。
 * 2、建造者模式是一步一步创建一个复杂的对象，它允许用户通过指定复杂对象的类型和内容就可以构建它们，用户不需要知道具体的内部构建细节
 * <p>
 * 建造者模式的四个角色
 * 1、Product 具体的产品对象
 * 2、Builder 抽象建造者，抽象类/接口
 * 3、ConcreteBuilder 具体的建造者，实现接口、构建和装配各个部件
 * 4、Director 指挥者，构建使用 Builder 接口的对象，一是：隔离用户和对象产生的过程，二是：负责控制产品对象的生产过程
 * <p>
 * 建造者模式的注意事项和细节
 * 1、使用者不需要知道产品内部组成的细节，将产品本身和产品的构建过程解耦，使用相同的构建方式，可以构建不同的产品对象
 * 2、每一个具体的建造者都是独立的，因此可以很方便的替换、新增具体的建造者
 * 3、更加精细的控制产品的创建过程
 * 4、增加新的建造者无需修改原来的代码，系统扩展方便符合 ocp
 */
abstract class AbstractBuilder {

    protected House house = new House();

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public House build() {
        return house;
    }
}
