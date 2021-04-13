package com.baseknowledge.reflect.entity;

/**
 * @author miclefengzss
 * 2021/4/12 下午9:57
 */
public class Employee {

    static {
        System.out.println("Employee 类已加载到jvm，并被初始化。");
    }

    private int eno;
    public String name;
    private float salary;
    private String department;

    public Employee() {
        System.out.println("Employee 默认构造方法已被执行。");
    }

    public Employee(int eno, String name, float salary, String department) {
        this.eno = eno;
        this.name = name;
        this.salary = salary;
        this.department = department;
        System.out.println("Employee 带参构造方法已被执行。");
    }

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee updateSalary(float val) {
        this.salary = this.salary + val;
        System.out.println(this.name + " 调薪至：" + this.salary + "元");
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eno=" + eno +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
