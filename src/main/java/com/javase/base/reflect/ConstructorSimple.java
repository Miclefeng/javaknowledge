package com.javase.base.reflect;

import com.javase.base.reflect.entity.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author miclefengzss
 * 2021/4/12 下午10:17
 */
public class ConstructorSimple {

    public static void main(String[] args) {
        try {
            Class<?> employeeClass = Class.forName("com.javase.base.reflect.entity.Employee");
            Constructor<?> constructor = employeeClass.getConstructor(int.class, String.class, float.class, String.class);
            Employee employee = (Employee) constructor.newInstance(1001, "micle", 25000.0f, "aiot");
            System.out.println(employee);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
