package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:22
 */
public interface EventManager {

    void subscribe(Enum<EventType> eventType, EventListener eventListener);

    void unSubscribe(Enum<EventType> eventType, EventListener eventListener);

    void publish(Enum<EventType> eventType, Lottery lottery);
}
