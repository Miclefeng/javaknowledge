package com.designpattern.pattern.structural.adapter;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 22:53
 */
public class Computer {

    public String read(SDCard sdCard) {
        return sdCard.read();
    }

    public void write(SDCard sdCard, String data) {
        sdCard.write(data);
    }
}
