package com.designpattern.adapter.interfaces;

/**
 * 接口适配器
 * 当不需要全部实现接口提供的方法时，可以先设计一个抽象类先实现接口，并为接口中的方法提供一个默认实现(空方法)，
 * 那么该抽象类的子类，可以有选择的覆盖父类中的某些方法
 * <p>
 * 抽象类可以使用匿名内部类的方式实例化
 *
 * @author miclefengzss
 */
public interface IAdapter {

    void m1();

    void m2();

    void m3();

    void m4();
}
