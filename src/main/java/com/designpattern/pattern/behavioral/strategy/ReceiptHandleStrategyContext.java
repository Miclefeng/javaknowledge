package com.designpattern.pattern.behavioral.strategy;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 21:58
 */
public class ReceiptHandleStrategyContext {

    private ReceiptHandleStrategy strategy;

    public ReceiptHandleStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ReceiptHandleStrategy strategy) {
        this.strategy = strategy;
    }

    public void handleReceipt(Receipt receipt) {
        if(receipt != null){
            strategy.doHandle(receipt);
        }
    }
}
