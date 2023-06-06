package com.middleware.zookeeper.configuration.config01;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 10:55
 */
public class ZookeeperUtil {

    private static ZooKeeper zooKeeper;
    private static ZookeeperConf zookeeperConf;

    private static DefaultWatch defaultWatch;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

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
