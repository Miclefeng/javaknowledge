package com.designpattern.pattern.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 22:01
 */
public class ReceiptHandleStrategyFactory {

    private static Map<String, ReceiptHandleStrategy> strategyMap;

    public static void init() {
        strategyMap = new HashMap<>();
        strategyMap.put("MT1101", new MT1101ReceiptHandleStrategy());
        strategyMap.put("MT2101", new MT2101ReceiptHandleStrategy());
    }

    public static ReceiptHandleStrategy getReceiptHandleStrategy(String recepitType) {
        return strategyMap.get(recepitType);
    }
}
