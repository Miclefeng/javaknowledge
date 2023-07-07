package com.designpattern.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:31
 */
public class EventManagerServer implements EventManager {

    private final Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    @SafeVarargs
    public EventManagerServer(Enum<EventType>... eventTypes) {
        for (Enum<EventType> e : eventTypes) {
            this.listeners.put(e, new ArrayList<>());
        }
    }

    @Override
    public void subscribe(Enum<EventType> eventType, EventListener eventListener) {
        this.listeners.get(eventType).add(eventListener);
    }

    @Override
    public void unSubscribe(Enum<EventType> eventType, EventListener eventListener) {
        this.listeners.get(eventType).remove(eventListener);
    }

    @Override
    public void publish(Enum<EventType> eventType, Lottery lottery) {
        this.listeners.get(eventType).forEach(eventListener -> eventListener.doEvent(lottery));
    }
}
