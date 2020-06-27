package com.basic.knowledge.reflect;

import java.lang.reflect.Method;

public class ClassUtil {

    private Object object;

    public ClassUtil(Object object) {
        this.object = object;
    }

    public void printClassMessage() {
        Class c = this.object.getClass();
        // 获取去类的名称
        System.out.println("类的名称是：" + c.getName());

        /**
         * 获取类的方法，返回Method类
         * 一个成员方法就是一个Method对象
         * getMethods() 获取所有属性为 public 的方法，包括父类继承来的
         * getDeclaredMethods() 获取该类自己声明的所有方法
         */
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            /**
             * 获取方法的返回类型
             */
            Class<?> methodType = method.getReturnType();
            System.out.print(methodType.getName() + "  ");
            System.out.print(method.getName() + "( ");
            /**
             * 获取参数类型
             */
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println(")");
        }
    }
}
