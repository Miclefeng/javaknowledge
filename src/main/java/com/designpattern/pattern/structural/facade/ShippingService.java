package com.designpattern.pattern.structural.facade;

/**
 * @author miclefengzss
 * 2022/3/28 下午4:02
 */
public class ShippingService {

    public String shipGift(PointGift pointGift) {
        // 物流系统对接逻辑
        System.out.println(pointGift.getName() + " 进入了物流系统。");
        return "666";
    }
}
