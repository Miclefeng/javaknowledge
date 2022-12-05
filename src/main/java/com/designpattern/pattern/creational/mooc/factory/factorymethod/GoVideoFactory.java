package com.designpattern.pattern.creational.mooc.factory.factorymethod;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:34
 */
public class GoVideoFactory extends VideoFactory {
    
    @Override
    public Video getVideo() {
        return new GoVideo();
    }
}
