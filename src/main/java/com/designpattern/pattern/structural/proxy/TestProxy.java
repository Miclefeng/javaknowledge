package com.designpattern.pattern.structural.proxy;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 12:01
 */
public class TestProxy {

    public static void main(String[] args) {
        IUserDao userDao = new IUserDaoImpl();

        ProxyFactory proxyFactory = new ProxyFactory(userDao);

        IUserDao proxyInstance = (IUserDao) proxyFactory.getProxyInstance();

        proxyInstance.save();
    }
}
