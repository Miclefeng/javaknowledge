package com.middleware.zookeeper.configuration.config01;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 10:58
 */
public class DefaultWatch implements Watcher {

    CountDownLatch countDownLatch;

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
        Event.KeeperState state = event.getState();
        switch (state) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                countDownLatch.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
            case Closed:
                break;
        }
    }
}
