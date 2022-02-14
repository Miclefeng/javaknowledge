package com.designpattern.pattern.old.adapter.interfaces;

/**
 * @author miclefengzss
 */
public class Application {

    public static void main(String[] args) {

        /**
         * 抽象类可以通过匿名内部类方式实现初始化
         */
        AbstractAdapterImpl abstractAdapter = new AbstractAdapterImpl() {
            /**
             * 只需要覆盖需要使用的接口方法
             */
            @Override
            public void m1() {
                System.out.println("使用了 m1 方法.");
            }
        };

        abstractAdapter.m1();
    }
}
