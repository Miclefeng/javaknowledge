package com.middleware.zookeeper.firsttime.configuration.config01;

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

    WatchCallback watchCallback;

    MyConf configuration;

    @Before
    public void conn() {
        watchCallback = new WatchCallback();

        configuration = new MyConf();

        zookeeperConf = new ZookeeperConf();
        zookeeperConf.setAddress("172.16.18.11:2181,172.16.18.12:2181,172.16.18.13:2181,172.16.18.14:2181/config");
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
    public void getConfiguration() {
        watchCallback.setConfiguration(configuration);
        watchCallback.setZk(zk);
        watchCallback.setWachtPath("/AppConf");
        watchCallback.aWait();

        try {
            while (true) {
                if ("".equals(configuration.getConf()) || null == configuration.getConf()) {
                    System.err.println("conf lost ....");
                    watchCallback.aWait();
                } else {
                    System.out.println(configuration.getConf());
                }
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
