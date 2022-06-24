package com.javase.base.generic;


/**
 * @author miclefengzss
 * @create 2021-03-14 下午10:29
 */


public class GenericMethod<T extends Number> {

    private T num;

    public T getNum() {
        return this.num;
    }

    public void setNum(T num) {
        this.num = num;
    }

    /**
     * @param t
     * @param <T> <T extends Number> 表示只能是 Number 类型和其子类
     */
    public <T extends Number> void printValue(T t) {
        System.out.println(t);
    }
}


class GenericMethodTest {
    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();

        genericMethod.printValue(10);
        genericMethod.printValue(10.0);
        genericMethod.printValue(10.0f);
    }
}
