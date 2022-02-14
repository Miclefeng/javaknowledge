package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/1/17 下午10:01
 */
public enum EnumSingleton {
    INSTANCE {
        @Override
        protected void print() {
            System.out.println("enum print.");
        }
    };

    abstract protected void print();

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
