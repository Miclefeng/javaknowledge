package com.designpattern.pattern.creational.factory;

import com.designpattern.pattern.creational.factory.product.AbstractFreezer;
import com.designpattern.pattern.creational.factory.product.AbstractTV;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 10:41
 */
public class Client {

    private AbstractTV tv;

    private AbstractFreezer freezer;

    public Client(AbstractFactory factory) {
        this.tv = factory.createTV();
        this.freezer = factory.createFreezer();
    }

    public AbstractTV getTv() {
        return tv;
    }

    public void setTv(AbstractTV tv) {
        this.tv = tv;
    }

    public AbstractFreezer getFreezer() {
        return freezer;
    }

    public void setFreezer(AbstractFreezer freezer) {
        this.freezer = freezer;
    }

    public static void main(String[] args) {
        Client client = new Client(new HairFactory());

        AbstractTV tv1 = client.getTv();
        System.out.println("tv1 = " + tv1);
        AbstractFreezer freezer1 = client.getFreezer();
        System.out.println("freezer1 = " + freezer1);
    }
}
