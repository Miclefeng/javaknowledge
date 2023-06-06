package com.middleware.zookeeper.lock.lock03;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 21:04
 */
public class WatcherCallback implements Watcher, AsyncCallback.StringCallback, AsyncCallback.StatCallback, AsyncCallback.Children2Callback {

    ZooKeeper zooKeeper;

    String threadName;

    String pathName;

    String pathPrefix = "/lock";

    String watchPath = "/";

    CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void tryLock() {
        zooKeeper.create(pathPrefix, threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, threadName);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock() {
        try {
            zooKeeper.delete(pathName, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        if (children != null && children.size() > 0) {
            Collections.sort(children);
            int i = children.indexOf(pathName);
            if (i < 1) {
                System.out.println(threadName + " i am first.");
                countDownLatch.countDown();
            } else {
                // 监视前一个节点的变化，随时准备获取锁
                zooKeeper.exists(watchPath + children.get(i - 1), this, this, ctx);
            }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat == null) {
            zooKeeper.getChildren(watchPath, this, this, ctx);
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            System.out.println(threadName + " create path: " + name);
            pathName = name;
            zooKeeper.getChildren(watchPath, this, this, ctx);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zooKeeper.getChildren(watchPath, this, this, "deleted");
                break;
            case NodeDataChanged:
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
