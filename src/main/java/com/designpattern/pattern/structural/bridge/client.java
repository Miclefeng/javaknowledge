package com.designpattern.pattern.structural.bridge;

import java.math.BigDecimal;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 15:40
 */
public class client {

    public static void main(String[] args) {
        WxPay wxPay = new WxPay(new FacePayMode());
        wxPay.transfer("wx_10001", "1000880", new BigDecimal(100));
        System.out.println("============================");

        AliPay aliPay = new AliPay(new SecretPayMode());
        aliPay.transfer("ali_10001", "10009090", new BigDecimal(300));
    }
}
