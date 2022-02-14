package com.designpattern.pattern.old.builder;

/**
 * @author miclefengzss
 */
public class Director {

    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(AbstractBuilder builder) {
        this.builder = builder;
    }

    public House concreteBuild() {
        builder.buildBasic();
        builder.buildWalls();
        builder.roofed();
        return builder.build();
    }
}
