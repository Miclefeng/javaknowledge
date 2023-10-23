package com.designpattern.pattern.structural.adapter;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 22:50
 */
public class SDCardImpl implements SDCard{
    @Override
    public String read() {
        System.out.println("sd card reading...");
        return "sd card read.";
    }

    @Override
    public void write(String data) {
        System.out.println("sd card writing. " + data);
    }
}
