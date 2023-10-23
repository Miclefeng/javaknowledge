package com.designpattern.pattern.structural.bridge;

import java.math.BigDecimal;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 15:34
 */
public abstract class Pay {

    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    public abstract String transfer(String  uid, String tradeId, BigDecimal amount);
}
