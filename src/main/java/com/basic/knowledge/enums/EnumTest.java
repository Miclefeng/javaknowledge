package com.basic.knowledge.enums;

/**
 * @author miclefengzss
 * @create 2020-07-24 12:33 下午
 */

public class EnumTest {

    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
        System.out.println("===============");
        Season[] values = Season.values();
        for (Season value : values) {
            System.out.println(value);
        }
        System.out.println("===============");
        Season winter = Season.valueOf("WINTER");
        System.out.println(winter);
        winter.show();
    }
}

interface info {
    void show();
}

enum Season implements info {

    SPRING("春天", "春暖花开") {
        @Override
        public void show() {
            System.out.println("春天");
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        @Override
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天", "秋高气爽") {
        @Override
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天", "冰天雪地") {
        @Override
        public void show() {
            System.out.println("冬天");
        }
    };

    Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    private final String name;
    private final String desc;

    @Override
    public void show() {
        System.out.println("季节");
    }
}
