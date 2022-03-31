package com.designpattern.pattern.structural.facade;

/**
 * @author miclefengzss
 * 2022/3/28 下午3:58
 */
public class QualifyService {

    public boolean isAvailable(PointGift pointGift) {
        // 积分和库存校验
        System.out.println("校验 " + pointGift.getName() + " 积分资格通过，库存通过。");
        return true;
    }
}
