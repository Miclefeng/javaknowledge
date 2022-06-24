package com.javase.base.lambda.methodreference;

/**
 * @author miclefengzss
 * 2022/6/23 下午2:24
 */
public class ConstructorMethodReference {

    public static void printPerson(String name, PersonBuilder pb) {
        Person person = pb.buildPerson(name);
        System.out.println(person.getName());
    }

    public static void main(String[] args) {
        // 1. lambda
        printPerson("迪丽热巴", name -> new Person(name));

        // 2. method reference
        printPerson("古力娜扎", Person::new);
    }
}
