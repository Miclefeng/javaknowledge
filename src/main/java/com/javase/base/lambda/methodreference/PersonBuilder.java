package com.javase.base.lambda.methodreference;

/**
 * @author miclefengzss
 * 2022/6/23 下午2:25
 */
@FunctionalInterface
public interface PersonBuilder {

    Person buildPerson(String name);
}
