package com.designpattern.pattern.old.adapter.object;

/**
 * @author miclefengzss
 */
public class Application {

    public static void main(String[] args) {
        Phone phone = new Phone();
        VoltageAdapter voltageAdapter = new VoltageAdapter(new Voltage220());
        phone.charging(voltageAdapter);
    }
}
