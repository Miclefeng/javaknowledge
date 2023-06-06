package com.middleware.zookeeper.configuration.config02;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 14:01
 */
public class WatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

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
            zooKeeper.exists(watchPath, this, this, "node->if exists");
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
                System.out.println("node[" + watchPath + "] created.");
                zooKeeper.getData(watchPath, this, this, "node->created");
                break;
            case NodeDeleted:
                System.out.println("node[" + watchPath + "] deleted.");
                myConf.setConf("");
                // 因为 state 已被设置为 0，后续 await() 不生效了，重置为 1 await() 才会进行阻塞等待
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                System.out.println("node[" + watchPath + "] deleted.");
                zooKeeper.getData(watchPath, this, this, "node->data-changed");
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
