package com.baseknowledge.iostream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/file/imooc.txt");
            int data;
            while ((data = fileInputStream.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===============================================");
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/file/imooc.txt");
            byte[] buf = new byte[10];
            int len;
            while ((len = fileInputStream.read(buf)) != -1) {
                String s = new String(buf, 0, len);
                System.out.print(s);
            }
            System.out.println();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
