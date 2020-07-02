package com.basic.knowledge.designpattern.priciple.singleresponsibility;

/**
 * 单一职责原则
 * 通过不同的类实现不同的功能
 */
public class SingleResponsibility {

    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("bus");

        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("ship");
    }
}


class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " run.");
    }
}

class WaterVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " run.");
    }
}