package com.designpattern.pattern.creational.factory;

import com.designpattern.pattern.creational.factory.product.AbstractFreezer;
import com.designpattern.pattern.creational.factory.product.AbstractTV;
import com.designpattern.pattern.creational.factory.product.HairFreezer;
import com.designpattern.pattern.creational.factory.product.HairTV;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 10:24
 */
public class HairFactory implements AbstractFactory{
    @Override
    public AbstractTV createTV() {
        return new HairTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HairFreezer();
    }
}
