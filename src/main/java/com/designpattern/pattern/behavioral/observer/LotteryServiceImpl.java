package com.designpattern.pattern.behavioral.observer;

import java.util.Date;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:48
 */
public class LotteryServiceImpl extends LotteryService {

    private DrawHouseService drawHouseService = new DrawHouseService();

    @Override
    public LotteryResult lottery(String uid) {
        //1.摇号
        String result = drawHouseService.lots(uid);
        return new LotteryResult(uid, result, new Date());
    }
}
