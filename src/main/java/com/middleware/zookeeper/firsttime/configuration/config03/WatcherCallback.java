package com.middleware.zookeeper.firsttime.configuration.config03;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 14:53
 */
public class WatcherCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    ZooKeeper zooKeeper;

    String watchPath;

    MyConf myConf;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public String getWatchPath() {
        return watchPath;
    }

    public void setWatchPath(String watchPath) {
        this.watchPath = watchPath;
    }

    public MyConf getMyConf() {
        return myConf;
    }

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }

    public void aWait() {
        try {
            zooKeeper.exists(watchPath, this, this, "ctx");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            myConf.setConf(new String(data));
            countDownLatch.countDown();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zooKeeper.getData(watchPath, this, this, ctx);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
        Event.EventType type = event.getType();
        switch (type) {
            case None:
                break;
            case NodeCreated:
                System.out.println("[" + watchPath + "] NodeCreated.");
                zooKeeper.getData(watchPath, this, this, "NodeCreated");
                break;
            case NodeDeleted:
                System.out.println("[" + watchPath + "] NodeDeleted.");
                myConf.setConf("");
                // 重置 state 的值为 1，只有值为 1 CountDownLatch.await() 才能阻塞
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                System.out.println("[" + watchPath + "] NodeDataChanged.");
                zooKeeper.getData(watchPath, this, this, "NodeDataChanged");
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }
    }
}
