package com.javase.base.reflect;

import com.javase.base.reflect.entity.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author miclefengzss
 * 2021/4/12 下午10:23
 */
public class MethodSimple {

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.javase.base.reflect.entity.Employee");
            Constructor<?> constructor = aClass.getConstructor(int.class, String.class, float.class, String.class);
            Employee employee = (Employee) constructor.newInstance(1001, "micle", 25000.0f, "aiot");
            System.out.println(employee);
            Method updateSalary = aClass.getMethod("updateSalary", float.class);
            Employee employee1 = (Employee) updateSalary.invoke(employee, 5000f);
            System.out.println(employee1);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
