package com.designpattern.pattern.behavioral.observer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:35
 */
public class EventManager {

    public enum EventType {
        Message, MQ
    }

    private Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... eventTypes) {
        for (Enum<EventType> et : eventTypes) {
            listeners.put(et, new ArrayList<>());
        }
    }

    public void subscribe(Enum<EventType> et, EventListener el) {
        listeners.get(et).add(el);
    }

    public void unsubscribe(Enum<EventType> et, EventListener el) {
        listeners.get(et).remove(el);
    }

    public void notify(Enum<EventType> et, LotteryResult lotteryResult) {
        for (EventListener el : listeners.get(et)) {
            el.doEvent(lotteryResult);
        }
    }
}
