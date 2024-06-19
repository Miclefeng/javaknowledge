package com.designpattern.pattern.behavioral.oldobserver;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:20
 */
public class MQEventListener implements EventListener{

    @Override
    public void doEvent(Lottery lottery) {
        System.out.println("保存数据到MQ,用户ID: " + lottery.getUserId() + ", 中奖结果: " + lottery.getMessage() + ", 时间: " + lottery.getTime());
    }
}
