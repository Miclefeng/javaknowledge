package com.designpattern.pattern.structural.facade;

/**
 * @author miclefengzss
 * 2022/3/28 下午4:14
 */
public class Test {

    public static void main(String[] args) {
        PointGift computer = new PointGift("computer");
        GiftExchangeService giftExchangeService = new GiftExchangeService();
        giftExchangeService.gitExchange(computer);
    }
}
