package com.basic.knowledge.designpattern.priciple.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则
 * 1、一个对象应该对其它对象保持最少的了解
 * 2、类与类关系越密切，耦合度越大
 * 3、一个类对自己依赖的类资道的越少越好，对于依赖的类不管多么复杂，都尽量将逻辑封装在类的内部
 * 对外提供public方法即可
 * 4、出现在成员变量、方法参数、方法返回值中的类都是直接朋友
 */
public class Demeter {

    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}

class Employee {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class CollegeEmployee {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


class CollegeManager {

    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CollegeEmployee collegeEmployee = new CollegeEmployee();
            collegeEmployee.setId(i);
            list.add(collegeEmployee);
        }

        return list;
    }

    public void printEmployee() {
        List<CollegeEmployee> list = this.getAllEmployee();
        System.out.println("-----------CollegeEmployee-----------");
        for (CollegeEmployee e : list) {
            System.out.println("CollegeEmployee: " + e.getId());
        }
    }
}

class SchoolManager {

    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            list.add(employee);
        }
        return list;
    }

    public void printAllEmployee(CollegeManager collegeManager) {
        collegeManager.printEmployee();

        System.out.println("-----------SchoolEmployee------------");
        List<Employee> list = this.getAllEmployee();
        for (Employee e : list) {
            System.out.println("SchoolEmployee: " + e.getId());
        }
    }
}