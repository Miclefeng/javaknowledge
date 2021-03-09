package com.baseknowledge.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

    private Object object;
    private final Class<?> c;

    public ClassUtil(Object object) {
        this.object = object;
        this.c = this.object.getClass();
    }

    public void printClassMessage() {
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
            // 获取参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println(")");
        }
    }

    public void printFieldMessage() {
        System.out.println("类的名称是：" + c.getName());
        /**
         * 获取类的域，返回 Field 类
         * getFields() 获取所有属性为 public 的域
         * getDeclaredFields() 获取该类自己声明的所有域
         */
        Field[] fields = c.getFields();
        for (Field field : fields
        ) {
            // 获取域的类型
            Class<?> fieldType = field.getType();
            System.out.print(fieldType.getName() + " ");
            System.out.println(field.getName());
        }
    }

    public void printConstructorMessage() {
        System.out.println("类的名称是：" + c.getName());
        /**
         * 获取类的构造函数信息
         * getConstructors() 获取所有属性为 public 的构造函数
         * getDeclaredConstructors() 获取该类自己声明的构造函数
         */
        Constructor<?>[] constructors = c.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            String constructorName = constructor.getName();
            System.out.print(constructorName + "(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println(")");
        }
    }
}
