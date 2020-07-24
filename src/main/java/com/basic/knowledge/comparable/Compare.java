package com.basic.knowledge.comparable;

import java.util.Arrays;
import java.util.Comparator;

class Employee implements Comparable {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public int compareTo(Object other) {
        return Double.compare(salary, ((Employee) other).salary);
    }
}

/**
 * @author miclefengzss
 */
public class Compare {

    public static void main(String[] args) {

        /**
         * Comparable接口: 自然排序,永久性的排序
         * 实现Comparable接口，重写compareTo(Object o) 方法
         */
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);

        Arrays.sort(staff);

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
        }
        System.out.println("=================================");
        /**
         * Comparator接口: 定制排序,临时性的排序
         * 实现Comparator接口，重写compare(Object o1, Object o2) 方法
         */
        Arrays.sort(staff, (o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return Double.compare(o1.getSalary(), o2.getSalary());
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
        }
    }
}
