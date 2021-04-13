package com.baseknowledge.reflect;

import com.baseknowledge.reflect.entity.Employee;

/**
 * @author miclefengzss
 * 2021/4/12 下午10:00
 */
public class ClassSimple {

    public static void main(String[] args) {
        try {
//            Class.forName() 将指定的类加载到jvm，并返回class对象
            Class employeeClass = Class.forName("com.baseknowledge.reflect.entity.Employee");
            System.out.println("Employee 已被加载到jvm");
            // newInstance 通过默认构造方法创建对象
            Employee e = (Employee) employeeClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
