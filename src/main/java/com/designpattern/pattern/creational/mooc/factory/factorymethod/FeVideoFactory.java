package com.designpattern.pattern.creational.mooc.factory.factorymethod;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:34
 */
public class FeVideoFactory extends VideoFactory {
    
    @Override
    public Video getVideo() {
        return new FeVideo();
    }
}
