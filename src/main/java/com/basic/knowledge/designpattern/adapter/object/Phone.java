package com.basic.knowledge.designpattern.adapter.object;

/**
 * @author miclefengzss
 */
public class Phone {

    public void charging(IVoltage5 iVoltage5) {
        if (iVoltage5.output() == 5) {
            System.out.println("charging success.");
        }
    }
}
