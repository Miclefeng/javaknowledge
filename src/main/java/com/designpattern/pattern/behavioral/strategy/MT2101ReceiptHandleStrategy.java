package com.designpattern.pattern.behavioral.strategy;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 21:56
 */
public class MT2101ReceiptHandleStrategy implements ReceiptHandleStrategy {

    @Override
    public void doHandle(Receipt receipt) {
        System.out.println("解析报文MT2101: " + receipt.getMessage());
    }
}
