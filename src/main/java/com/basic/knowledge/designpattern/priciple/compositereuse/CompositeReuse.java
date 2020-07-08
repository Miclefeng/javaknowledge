package com.basic.knowledge.designpattern.priciple.compositereuse;

/**
 * 合成复用原则
 * 尽量使用合成/聚合的方式，而不是使用继承
 * 1、通过方法参数传递 是 使用 依赖
 * 2、通过成员变量直接实例化 是 使用 组合(整体和部分的关系，整体与部分不可以分开)
 * 3、通过成员变量和setter 是 使用 聚合(整体和部分的关系，整体与部分可以分开)
 * @author miclefengzss
 */
public class CompositeReuse {

    public static void main(String[] args) {

    }
}

class ComputerComposite {

    /**
     * computer 和 mouse monitor 是组合关系，不可分离
     */
    private Mouse mouse = new Mouse();
    private Monitor monitor = new Monitor();
}


class ComputerAggregation {
    /**
     * computer 和 mouse monitor 是聚合的关系
     * mouse和monitor 是computer的一部分，可以分离
     */
    private Mouse mouse;
    private Monitor monitor;

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}

class Mouse {

}

class Monitor {

}
