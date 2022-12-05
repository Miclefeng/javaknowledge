package com.designpattern.pattern.structural.mooc.facade;

/**
 * @author miclefengzss
 * 2022/3/28 下午4:03
 */
public class GiftExchangeService {

    private QualifyService qualifyService = new QualifyService();

    private PointPaymentService pointPaymentService = new PointPaymentService();

    private ShippingService shippingService = new ShippingService();

    public void gitExchange(PointGift pointGift) {
        if (qualifyService.isAvailable(pointGift)) {
            if (pointPaymentService.pay(pointGift)) {
                final String shippingOrderNo = shippingService.shipGift(pointGift);
                System.out.println("物流系统下单成功，订单号: " + shippingOrderNo);
            }
        }
    }
}
