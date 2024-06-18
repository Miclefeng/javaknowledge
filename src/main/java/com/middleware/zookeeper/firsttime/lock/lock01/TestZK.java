package com.middleware.zookeeper.firsttime.lock.lock01;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 10:45
 */
public class TestZK {

    ZooKeeper zk;

    ZookeeperConf zookeeperConf;

    DefaultWatch defaultWatch;

    @Before
    public void conn() {
        zookeeperConf = new ZookeeperConf();
        zookeeperConf.setAddress("172.16.18.11:2181,172.16.18.12:2181,172.16.18.13:2181,172.16.18.14:2181/lock");
        zookeeperConf.setTimeout(1000);

        defaultWatch = new DefaultWatch();

        ZookeeperUtil.setDefaultWatch(defaultWatch);
        ZookeeperUtil.setZookeeperConf(zookeeperConf);

        zk = ZookeeperUtil.getZookeeper();
    }

    @After
    public void close() {
        ZookeeperUtil.close();
    }

    @Test
    public void testLock() {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    WatcherCallback watcherCallback = new WatcherCallback();
                    watcherCallback.setZookeeper(zk);
                    watcherCallback.setThreadName(threadName);
                    watcherCallback.setPathPrefix("/lock");

                    watcherCallback.tryLock();
                    System.out.println(threadName + " working...");
                    watcherCallback.unLock();
                }
            }).start();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        try {
            while (true) {

                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
