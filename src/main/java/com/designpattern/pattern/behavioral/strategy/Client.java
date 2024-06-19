package com.designpattern.pattern.behavioral.strategy;

import java.util.List;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 22:03
 */
public class Client {

    public static void main(String[] args) {
        List<Receipt> receiptList = ReceiptBuilder.getReceiptList();

        ReceiptHandleStrategyContext context = new ReceiptHandleStrategyContext();

        ReceiptHandleStrategyFactory.init();

        for (Receipt r : receiptList) {
            // 获取策略
            ReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(r.getType());

            // 设置策略
            context.setStrategy(receiptHandleStrategy);

            // 执行策略
            context.handleReceipt(r);
        }
    }
}
