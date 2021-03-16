package com.designpattern.priciple.openclose;

/**
 * 开闭原则
 * 1、一个实体类，模块和函数应该对扩展开放，对修改关闭。用抽象构建框架，用实现扩展细节
 * 2、当类有变化时，尽量通过扩展类的行为来实现变化，而不是修改已有的代码
 */
public class OpenClose {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
    }
}