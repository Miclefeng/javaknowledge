package com.middleware.zookeeper.firsttime.configuration.config03;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 14:39
 */
public class TestZK {

    ZooKeeper zookeeper;

    ZookeeperConf zookeeperConfig;

    @Before
    public void conn() {
        zookeeperConfig = new ZookeeperConf("172.16.18.11:2181,172.16.18.12:2181,172.16.18.13:2181,172.16.18.14:2181/config", 1000);
        DefaultWatcher defaultWatcher = new DefaultWatcher();
        ZookeeperUtil.setZookeeperConfig(zookeeperConfig);
        ZookeeperUtil.setDefaultWatcher(defaultWatcher);
        zookeeper = ZookeeperUtil.getZookeeper();
    }

    @After
    public void close() {
        ZookeeperUtil.close();
    }

    @Test
    public void getConf() {
        System.out.println(zookeeper);
        MyConf myConf = new MyConf();
        WatcherCallback watcherCallback = new WatcherCallback();
        watcherCallback.setWatchPath("/AppConf");
        watcherCallback.setZooKeeper(zookeeper);
        watcherCallback.setMyConf(myConf);
        watcherCallback.aWait();

        try {
            while (true) {
                if (myConf.getConf() == null || "".equals(myConf.getConf())) {
                    System.err.println("conf lost ....");
                    watcherCallback.aWait();
                } else {
                    System.out.println(myConf.getConf());
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
