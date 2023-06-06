package com.middleware.zookeeper.lock.lock02;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 17:40
 */
public class ZookeeperConf {

    private String address;

    private int timeout;

    public ZookeeperConf() {
    }

    public ZookeeperConf(String address, int timeout) {
        this.address = address;
        this.timeout = timeout;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
