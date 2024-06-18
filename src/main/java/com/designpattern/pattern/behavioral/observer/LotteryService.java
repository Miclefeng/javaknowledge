package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:43
 */
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        this.eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    public void lotteryAndMessage(String uid) {
        LotteryResult lottery = lottery(uid);
        eventManager.notify(EventManager.EventType.MQ, lottery);
        eventManager.notify(EventManager.EventType.Message, lottery);
    }

    public abstract LotteryResult lottery(String uid);
}
