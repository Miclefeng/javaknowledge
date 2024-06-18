package com.middleware.zookeeper.firsttime.lock.lock02;


import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 17:45
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

        zooKeeper = ZookeeperUtil.getZookeeper();
    }

    @After
    public void close() {
        ZookeeperUtil.close();
    }

    @Test
    public void testLock() {
        System.out.println(zooKeeper);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                WatcherCallback watcherCallback = new WatcherCallback();
                watcherCallback.setThreadName(threadName);
                watcherCallback.setZooKeeper(zooKeeper);

                watcherCallback.tryLock();
                System.out.println(threadName + " working...");
                watcherCallback.unLock();
            }).start();
        }

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
