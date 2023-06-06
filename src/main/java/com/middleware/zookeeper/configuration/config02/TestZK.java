package com.middleware.zookeeper.configuration.config02;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 13:57
 */
public class TestZK {

    ZooKeeper zk;
    MyConf myConf;
    WatchCallback watchCallback;

    @Before
    public void conn() {
        ZookeeperConf zookeeperConf = new ZookeeperConf();
        zookeeperConf.setAddress("172.16.18.11:2181,172.16.18.12:2181,172.16.18.13:2181,172.16.18.14:2181/config");
        zookeeperConf.setTimeout(1000);
        DefaultWatch defaultWatch = new DefaultWatch();

        ZookeeperUtil.setZookeeperConf(zookeeperConf);
        ZookeeperUtil.setDefaultWatch(defaultWatch);

        zk = ZookeeperUtil.getZookeeper();

        myConf = new MyConf();

        watchCallback = new WatchCallback();
        watchCallback.setZooKeeper(zk);
        watchCallback.setWatchPath("/AppConf");
        watchCallback.setMyConf(myConf);
    }

    @After
    public void close() {
        ZookeeperUtil.close();
    }

    @Test
    public void getMyConf() {
        watchCallback.aWait();

        while (true) {
            try {
                if (myConf.getConf() == null || "".equals(myConf.getConf())) {
                    System.err.println("myConf lost....");
                    watchCallback.aWait();
                } else {
                    System.out.println(myConf.getConf());
                }

                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
