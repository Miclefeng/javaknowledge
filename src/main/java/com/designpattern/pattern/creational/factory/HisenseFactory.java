package com.designpattern.pattern.creational.factory;

import com.designpattern.pattern.creational.factory.product.AbstractFreezer;
import com.designpattern.pattern.creational.factory.product.AbstractTV;
import com.designpattern.pattern.creational.factory.product.HisenseFreezer;
import com.designpattern.pattern.creational.factory.product.HisenseTV;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 10:25
 */
public class HisenseFactory implements AbstractFactory{
    @Override
    public AbstractTV createTV() {
        return new HisenseTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HisenseFreezer();
    }
}
