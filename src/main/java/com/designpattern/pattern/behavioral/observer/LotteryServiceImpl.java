package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:43
 */
public class LotteryServiceImpl extends LotteryService{

    private DrawCarService drawCarService = new DrawCarService();

    @Override
    public Lottery lottery(String userId) {

        String result = drawCarService.lots(userId);

        return new Lottery(userId, result, (int) (System.currentTimeMillis() / 1000));
    }
}
