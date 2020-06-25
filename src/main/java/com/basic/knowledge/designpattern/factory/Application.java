package com.basic.knowledge.designpattern.factory;

import java.util.Scanner;

/**
 * 1. 单纯interface可以统一方法的调用，不能统一对象的实例化
 * 2. 面向对象主要做的两件事： 实例化对象、调用方法(完成业务逻辑)
 * 3. 只有一段代码中没有new的出现，才能保证代码的相对稳定，才能逐步实现IOC
 * 4. 实质上一段代码要保持稳定，就不应该负责对象的实例化
 * 5. 对象实例化是不可消除的
 * 6. 把对象实例化的过程，转移到其他代码片段中去
 * 7. 代码总是不稳定的，隔离这些不稳定，保证其它的代码是稳定的
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("Enter your select hero:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        SkillImpl skill = HeroFactory.getHero(name);
        skill.r();
    }
}
