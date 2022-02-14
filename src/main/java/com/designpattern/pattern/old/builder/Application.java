package com.designpattern.pattern.old.builder;

/**
 * @author miclefengzss
 */
public class Application {

    public static void main(String[] args) {
        NormalBuilder normalBuilder = new NormalBuilder();

        Director director = new Director(normalBuilder);
        House house = director.concreteBuild();
        System.out.println(house);
        System.out.println("======================");
        director.setBuilder(new HighBuilder());
        House highHouse = director.concreteBuild();
        System.out.println(highHouse);
    }
}
