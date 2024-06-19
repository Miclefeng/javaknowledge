package com.designpattern.pattern.behavioral.oldobserver;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:04
 */
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManagerServer(EventType.Message, EventType.MQ);
        eventManager.subscribe(EventType.Message, new MessageEventListener());
        eventManager.subscribe(EventType.MQ, new MQEventListener());
    }

    public Lottery lotteryAndMessage(String userId) {

        Lottery lottery = lottery(userId);

        eventManager.publish(EventType.Message, lottery);
        eventManager.publish(EventType.MQ, lottery);
        return lottery;
    }

    public abstract Lottery lottery(String userId);
}
