package com.designpattern.pattern.creational.prototype.clone;

import java.util.Date;

/**
 * @author miclefengzss
 * 2022/1/18 下午9:16
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Date birthday = new Date(0L);
        Pig pig = new Pig("name", birthday);
        Pig pig1 = (Pig) pig.clone();

        System.out.println(pig);
        System.out.println(pig1);
        System.out.println("After: ");
        pig1.getBirthday().setTime(77777777777L);
        System.out.println(pig);
        System.out.println(pig1);
    }
}
