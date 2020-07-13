package com.basic.knowledge.designpattern.adapter.object;

/**
 * @author miclefengzss
 */
public class Voltage220 {

    private static final int SRC = 220;

    public int output() {
        System.out.println("output 220v voltage.");
        return SRC;
    }
}
