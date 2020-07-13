package com.basic.knowledge.designpattern.adapter.object;

/**
 * 对象适配器模式
 * adapter 类通过聚合方式持有 src 类的实例，实现 dst 类的接口，完成 src->dst 适配
 * 根据"合成复用原则"，使用关联关系来替代继承关系
 *
 * @author miclefengzss
 */
public class VoltageAdapter implements IVoltage5 {

    private Voltage220 voltage220;

    public VoltageAdapter(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }

    public int transform() {
        int dst = 0;
        if (voltage220 != null) {
            dst = voltage220.output() / 44;
            System.out.println("transform 200v to 5v.");
            System.out.println("now voltage is " + dst + "v.");
        }
        return dst;
    }

    @Override
    public int output() {
        return transform();
    }
}
