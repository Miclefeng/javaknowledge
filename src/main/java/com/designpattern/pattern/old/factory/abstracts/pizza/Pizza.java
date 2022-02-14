package com.designpattern.pattern.old.factory.abstracts.pizza;

/**
 * @author miclefengzss
 */
public abstract class Pizza {

    private String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + " start bake");
    }

    public void cut() {
        System.out.println(name + " start cut.");
    }

    public void box() {
        System.out.println(name + " start box.");
    }

    public void setName(String name) {
        this.name = name;
    }
}
