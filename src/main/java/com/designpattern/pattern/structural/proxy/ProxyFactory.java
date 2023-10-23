package com.designpattern.pattern.structural.proxy;

import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 11:54
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("方法执行前");
                    method.invoke(target, args);
                    System.out.println("方法执行后");
                    return null;
                }
        );
    }
}
