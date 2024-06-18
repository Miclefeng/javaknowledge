package com.middleware.zookeeper.firsttime.configuration.config01;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 11:17
 */
public class WatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private String wachtPath;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    ZooKeeper zk;

    MyConf configuration;

    public String getWachtPath() {
        return wachtPath;
    }

    public void setWachtPath(String wachtPath) {
        this.wachtPath = wachtPath;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public MyConf getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MyConf configuration) {
        this.configuration = configuration;
    }

    public void aWait() {
        try {
            zk.exists(wachtPath, this, this, "ctx");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
        Event.EventType type = event.getType();
        switch (type) {
            case None:
                break;
            case NodeCreated:
                System.out.println("node[" + wachtPath + "] created.");
                zk.getData(wachtPath, this, this, "node->create");
                break;
            case NodeDeleted:
                System.out.println("node[" + wachtPath + "] deleted.");
                configuration.setConf("");
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                System.out.println("node[" + wachtPath + "] data-changed.");
                zk.getData(wachtPath, this, this, "node->data-changed");
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

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zk.getData(wachtPath, this, this, ctx);
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            configuration.setConf(new String(data));
            countDownLatch.countDown();
        }
    }
}
