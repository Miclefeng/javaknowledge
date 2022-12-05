package com.designpattern.pattern.creational.mooc.factory.factorymethod;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:31
 */
public class BootStart {

    public static void main(String[] args) {
        VideoFactory videoFactory = new JavaVideoFactory();
        VideoFactory videoFactory2 = new GoVideoFactory();
        VideoFactory videoFactory3 = new FeVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }
}
