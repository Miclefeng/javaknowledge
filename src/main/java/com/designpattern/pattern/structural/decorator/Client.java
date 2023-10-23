package com.designpattern.pattern.structural.decorator;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 17:26
 */
public class Client {

    public static void main(String[] args) {
        String data = "age:31,area:beijing";
        BaseFileDataLoader fileDataLoader = new BaseFileDataLoader("demo.txt");
        fileDataLoader.write(data);
        System.out.println(fileDataLoader.read());

        System.out.println("======================");
        EncryptionDecorator encryptionDecorator = new EncryptionDecorator(fileDataLoader);
        encryptionDecorator.write(data + " 2");
        System.out.println(encryptionDecorator.read());
    }
}
