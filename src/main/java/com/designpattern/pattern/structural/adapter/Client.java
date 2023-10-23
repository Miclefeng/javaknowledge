package com.designpattern.pattern.structural.adapter;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 22:57
 */
public class Client {

    public static void main(String[] args) {
        SDCard sdCard = new SDCardImpl();
        Computer computer = new Computer();
        computer.read(sdCard);
        computer.write(sdCard, "miclefeng");
        System.out.println("==========================");
        TFCard tfCard = new TFCardImpl();
        SDAdapterTF sdAdapterTF = new SDAdapterTF(tfCard);
        computer.read(sdAdapterTF);
        computer.write(sdAdapterTF, "tfcard");
    }
}
