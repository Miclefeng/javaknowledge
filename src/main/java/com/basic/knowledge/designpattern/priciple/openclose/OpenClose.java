package com.basic.knowledge.designpattern.priciple.openclose;

/**
 * 开闭原则
 * 1、一个实体类，模块和函数应该对扩展开发，对修改关闭。用抽象构建框架，用实现扩展细节
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

/**
 * 使用方无需改变
 */
class GraphicEditor {

    public void drawShape(Shape s) {
        s.draw();
    }
}

abstract class Shape {
    int m_type;

    public abstract void draw();
}

/**
 * 提供方扩展一个类即可
 */
class Triangle extends Shape {

    public Triangle() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}

class Rectangle extends Shape {

    public Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}

class Circle extends Shape {

    public Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}