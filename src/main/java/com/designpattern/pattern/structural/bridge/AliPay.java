package com.designpattern.pattern.structural.bridge;

import java.math.BigDecimal;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 15:38
 */
public class AliPay extends Pay {

    public AliPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uid, String tradeId, BigDecimal amount) {
        System.out.println("支付宝渠道支付划账开始......");
        boolean security = payMode.security(uid);
        System.out.println("支付宝渠道支付风险校验: " + uid + "," + tradeId + "," + security);

        if (!security) {
            System.out.println("支付宝渠道支付划账失败! ! ");
            return "500";
        }

        System.out.println("支付宝渠道划账成功! 金额: " + amount);
        return "200";
    }
}
