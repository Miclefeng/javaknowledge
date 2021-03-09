package com.baseknowledge.object.polymorphism;

/**
 * @author miclefengzss
 * @create 2021-03-09 上午10:39
 */

public class Animal {

    public String name;

    public int month;

    public void eat() {
        System.out.println("Animal eat.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
