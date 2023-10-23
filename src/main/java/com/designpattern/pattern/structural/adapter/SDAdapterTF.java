package com.designpattern.pattern.structural.adapter;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 22:55
 */
public class SDAdapterTF implements SDCard {

    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String read() {
        System.out.println("adapter read tf card...");
        return tfCard.read();
    }

    @Override
    public void write(String data) {
        System.out.println("adapter write tf card...");
        tfCard.write(data);
    }
}
