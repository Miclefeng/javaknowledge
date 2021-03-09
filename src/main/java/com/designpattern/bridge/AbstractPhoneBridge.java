package com.designpattern.bridge;

/**
 * 桥接模式
 * 将实现与抽象放在两个不同的类层次上，使两个层次可以独立改变
 * 是一种结构型模式
 * 基于类的最小设计原则，通过使用封装、聚合和继承等行为让不同的类承担不同的职责。它的主要特点是把抽象与行为实现分离开来，
 * 从而可以保持各部分的独立性以及应对它们功能的扩展
 * <p>
 * 消息管理应用
 * 消息的类型: 即时消息、延时消息
 * 消息的实现: 短信、邮件、APP通知
 *
 * @author miclefengzss
 */
abstract class AbstractPhoneBridge {

    private PhoneBrand phoneBrand;

    protected AbstractPhoneBridge(PhoneBrand phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    protected void open() {
        phoneBrand.open();
    }

    protected void call() {
        phoneBrand.call();
    }

    protected void close() {
        phoneBrand.close();
    }
}
