package com.middleware.zookeeper.lock.lock03;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 21:00
 */
public class TestLock {

    ZooKeeper zooKeeper;

    @Before
    public void conn() {
        ZookeeperConf zookeeperConf = new ZookeeperConf();
        zookeeperConf.setAddress("172.16.18.11:2181,172.16.18.12:2181,172.16.18.13:2181,172.16.18.14:2181/lock");
        zookeeperConf.setTimeout(1000);

        DefaultWatcher defaultWatcher = new DefaultWatcher();

        ZookeeperUtil.setZookeeperConf(zookeeperConf);
        ZookeeperUtil.setDefaultWatcher(defaultWatcher);

        zooKeeper = ZookeeperUtil.getZooKeeper();
    }

    @After
    public void close() {
        ZookeeperUtil.close();
    }

    @Test
    public void testLock() {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                WatcherCallback watcherCallback = new WatcherCallback();
                watcherCallback.setZooKeeper(zooKeeper);
                watcherCallback.setThreadName(threadName);

                watcherCallback.tryLock();
                System.out.println(threadName + " working...");
                watcherCallback.unLock();
            }).start();
        }

        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
