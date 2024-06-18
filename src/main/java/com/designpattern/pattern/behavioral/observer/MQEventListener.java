package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:30
 */
public class MQEventListener implements EventListener{

    @Override
    public void doEvent(LotteryResult lotteryResult) {
        System.out.println("记录用户的摇号结果(MQ),用户ID：" + lotteryResult.getUid() + ", 中奖结果为：" + lotteryResult.getMessage() + ", 时间为：" + lotteryResult.getDateTime());
    }
}
