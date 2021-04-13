package com.baseknowledge.reflect;

import com.baseknowledge.reflect.entity.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author miclefengzss
 * 2021/4/12 下午10:41
 */
public class FieldSimple {

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.baseknowledge.reflect.entity.Employee");
            Constructor<?> constructor = aClass.getConstructor(int.class, String.class, float.class, String.class);
            Employee employee = (Employee) constructor.newInstance(1001, "micle", 25000f, "aiot");
            Field nameField = aClass.getField("name");
            nameField.set(employee, "dajun");
            String name = (String) nameField.get(employee);
            System.out.println(name);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
