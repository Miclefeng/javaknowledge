package com.middleware.zookeeper.configuration.config01;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/31 10:52
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
