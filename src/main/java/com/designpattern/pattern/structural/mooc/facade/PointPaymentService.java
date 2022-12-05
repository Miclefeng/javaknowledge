package com.designpattern.pattern.structural.mooc.facade;

/**
 * @author miclefengzss
 * 2022/3/28 下午3:59
 */
public class PointPaymentService {

    public boolean pay(PointGift pointGift) {
        // 扣减积分
        System.out.println("支付 " + pointGift.getName() + " 成功。");
        return true;
    }
}
