package com.middleware.zookeeper.firsttime.lock.lock01;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 16:59
 */
public class WatcherCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.Children2Callback, AsyncCallback.StringCallback {

    ZooKeeper zookeeper;

    String threadName;

    String pathPrefix;

    String pathName;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZooKeeper getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }

    public void setPathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    public void tryLock() {
        try {
            zookeeper.create(pathPrefix, threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, threadName);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock() {
        try {
            zookeeper.delete(pathName, -1);
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
//                System.out.println(threadName + " is first.");
                countDownLatch.countDown();
            } else {
                // 监控上一个节点 加锁 的情况
                zookeeper.exists("/" + children.get(i - 1), this, this, ctx);
            }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat == null) {
            zookeeper.getChildren("/", false, this, ctx);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zookeeper.getChildren("/", false, this, "deleted");
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

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            System.out.println(threadName + " create lock: " + name);
            pathName = name;
            zookeeper.getChildren("/", false, this, ctx);
        }
    }
}
