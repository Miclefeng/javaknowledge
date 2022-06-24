package com.javase.base.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author miclefengzss
 * 2021/3/19 下午5:38
 */
public class Employee {

    private int empNo;
    private String empName;
    private String job;
    @JSONField(name = "hiredate", format = "yyyy-MM-dd HH:mm:ss")
    private Date hDate;
    private float salary;
    @JSONField(serialize = false)
    private String department;

    public Employee(int empNo, String empName, String job, Date hDate, float salary, String department) {
        this.empNo = empNo;
        this.empName = empName;
        this.job = job;
        this.hDate = hDate;
        this.salary = salary;
        this.department = department;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date gethDate() {
        return hDate;
    }

    public void sethDate(Date hDate) {
        this.hDate = hDate;
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

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", empName='" + empName + '\'' +
                ", job='" + job + '\'' +
                ", hDate=" + hDate +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
