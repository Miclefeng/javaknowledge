package com.designpattern.pattern.structural.adapter;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 22:52
 */
public class TFCardImpl implements TFCard{
    @Override
    public String read() {
        System.out.println("tf card reading...");
        return "tf card read.";
    }

    @Override
    public void write(String data) {
        System.out.println("tf card writing. " + data);
    }
}
