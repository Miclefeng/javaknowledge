package com.middleware.zookeeper.firsttime.lock.lock02;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 17:41
 */
public class ZookeeperUtil {

    static ZooKeeper zookeeper;

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

    public static ZooKeeper getZookeeper() {
        try {
            zookeeper = new ZooKeeper(zookeeperConf.getAddress(), zookeeperConf.getTimeout(), defaultWatcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zookeeper;
    }

    public static void close() {
        if (zookeeper != null) {
            try {
                zookeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
