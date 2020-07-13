package com.basic.knowledge.designpattern.adapter.object;

/**
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
