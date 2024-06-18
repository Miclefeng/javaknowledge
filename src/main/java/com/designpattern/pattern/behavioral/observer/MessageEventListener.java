package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:30
 */
public class MessageEventListener implements EventListener{

    @Override
    public void doEvent(LotteryResult lotteryResult) {
        System.out.println("发送短信给：" + lotteryResult.getUid() + ", 中奖结果为：" + lotteryResult.getMessage() + ", 时间为：" + lotteryResult.getDateTime());
    }
}
