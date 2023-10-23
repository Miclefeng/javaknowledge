package com.designpattern.pattern.creational.factory;

import com.designpattern.pattern.creational.factory.product.AbstractFreezer;
import com.designpattern.pattern.creational.factory.product.AbstractTV;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 10:26
 */
public interface AbstractFactory {

    AbstractTV createTV();

    AbstractFreezer createFreezer();
}
