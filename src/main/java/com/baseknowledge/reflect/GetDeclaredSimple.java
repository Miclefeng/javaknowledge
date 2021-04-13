package com.baseknowledge.reflect;

import com.baseknowledge.reflect.entity.Employee;

import java.lang.reflect.*;

/**
 * @author miclefengzss
 * 2021/4/13 下午6:59
 */
public class GetDeclaredSimple {

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.baseknowledge.reflect.entity.Employee");
            Constructor<?> constructor = aClass.getConstructor(int.class, String.class, float.class, String.class);
            Employee employee = (Employee) constructor.newInstance(1001, "micle", 25000f, "aiot");
            Field[] declaredFields = aClass.getDeclaredFields();

            for (Field f : declaredFields) {
                String name = f.getName();
                if (f.getModifiers() == Modifier.PUBLIC) {
                    Object val = f.get(employee);
                    System.out.println(name + " : " + val);
                } else if (f.getModifiers() == Modifier.PRIVATE) {
                    // 私有的成员属性通过 get 方法访问
                    String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method aClassMethod = aClass.getMethod(methodName);
                    Object ret = aClassMethod.invoke(employee);
                    System.out.println(name + " : " + ret);
                }
//                System.out.println(f.getName());
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
