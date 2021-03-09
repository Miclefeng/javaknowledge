package com.designpattern.builder;

/**
 * @author miclefengzss
 */
public class NormalBuilder extends AbstractBuilder {

    @Override
    public void buildBasic() {
        house.setBasic("start build normal basic.");
        System.out.println("normal basic");
    }

    @Override
    public void buildWalls() {
        house.setWall("start build normal wall.");
        System.out.println("normal wall.");
    }

    @Override
    public void roofed() {
        house.setRoofed("start build normal roofed.");
        System.out.println("normal roofed");
    }
}
