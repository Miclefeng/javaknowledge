package com.middleware.zookeeper.configuration.config03;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 14:42
 */
public class ZookeeperUtil {

    static ZooKeeper zooKeeper;

    static ZookeeperConf zookeeperConfig;

    static DefaultWatcher defaultWatcher;

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZookeeperConf getZookeeperConfig() {
        return zookeeperConfig;
    }

    public static void setZookeeperConfig(ZookeeperConf zookeeperConfig) {
        ZookeeperUtil.zookeeperConfig = zookeeperConfig;
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
            zooKeeper = new ZooKeeper(zookeeperConfig.getAddress(), zookeeperConfig.getTimeout(), defaultWatcher);
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
