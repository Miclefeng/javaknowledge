package com.javase.base.iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {

    public static void main(String[] args) {
        try {
            String fileName = "src/main/resources/file/imooc_out.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            FileInputStream fileInputStream = new FileInputStream(fileName);
            fileOutputStream.write(50);
            fileOutputStream.write('a');

            int data;
            while ((data = fileInputStream.read()) != -1) {
                System.out.print("'");
                System.out.print((char) data);
                System.out.print("' -> ");
                System.out.println(data);
            }
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
