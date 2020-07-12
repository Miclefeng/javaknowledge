package com.basic.knowledge.designpattern.builder;

/**
 * @author miclefengzss
 */
public class HighBuilder extends AbstractBuilder {

    @Override
    public void buildBasic() {
        house.setBasic("start build high basic.");
        System.out.println("high basic.");
    }

    @Override
    public void buildWalls() {
        house.setWall("start build high wall.");
        System.out.println("high wall.");
    }

    @Override
    public void roofed() {
        house.setRoofed("start build high roofed.");
        System.out.println("high roofed.");
    }
}
