package com.middleware.zookeeper.lock.lock03;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 20:55
 */
public class ZookeeperUtil {
    static ZooKeeper zooKeeper;

    static ZookeeperConf zookeeperConf;

    static DefaultWatcher defaultWatcher;

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZookeeperConf getZookeeperConf() {
        return zookeeperConf;
    }

    public static void setZookeeperConf(ZookeeperConf zookeeperConf) {
        ZookeeperUtil.zookeeperConf = zookeeperConf;
    }

    public static DefaultWatcher getDefaultWatcher() {
        return defaultWatcher;
    }

    public static void setDefaultWatcher(DefaultWatcher defaultWatcher) {
        defaultWatcher.setCountDownLatch(countDownLatch);
        ZookeeperUtil.defaultWatcher = defaultWatcher;
    }

    public static ZooKeeper getZooKeeper() {
        try {
            zooKeeper = new ZooKeeper(zookeeperConf.getAddress(), zookeeperConf.getTimeout(), defaultWatcher);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    public static void close() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
