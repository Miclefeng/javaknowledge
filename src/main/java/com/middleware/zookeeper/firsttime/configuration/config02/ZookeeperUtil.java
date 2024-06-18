package com.middleware.zookeeper.firsttime.configuration.config02;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 13:50
 */
public class ZookeeperUtil {

    static ZooKeeper zooKeeper;

    static ZookeeperConf zookeeperConf;

    static DefaultWatch defaultWatch;

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public static void setZooKeeper(ZooKeeper zooKeeper) {
        ZookeeperUtil.zooKeeper = zooKeeper;
    }

    public static ZookeeperConf getZookeeperConf() {
        return zookeeperConf;
    }

    public static void setZookeeperConf(ZookeeperConf zookeeperConf) {
        ZookeeperUtil.zookeeperConf = zookeeperConf;
    }

    public static DefaultWatch getDefaultWatch() {
        return defaultWatch;
    }

    public static void setDefaultWatch(DefaultWatch defaultWatch) {
        defaultWatch.setCountDownLatch(countDownLatch);
        ZookeeperUtil.defaultWatch = defaultWatch;
    }

    public static ZooKeeper getZookeeper() {
        try {
            zooKeeper = new ZooKeeper(zookeeperConf.getAddress(), zookeeperConf.getTimeout(), defaultWatch);
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
