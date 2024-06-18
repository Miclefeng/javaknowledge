package com.middleware.zookeeper.firsttime.configuration.config02;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 13:49
 */
public class ZookeeperConf {

    private String address;

    private int timeout;

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
