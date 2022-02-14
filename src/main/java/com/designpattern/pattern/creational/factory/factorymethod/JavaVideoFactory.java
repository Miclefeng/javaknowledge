package com.designpattern.pattern.creational.factory.factorymethod;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:34
 */
public class JavaVideoFactory extends VideoFactory {

    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
