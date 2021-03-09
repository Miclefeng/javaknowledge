package com.baseknowledge.iostream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author miclefengzss
 * @create 2020-07-26 9:52 下午
 * <p>
 * <p>
 * 一、流的分类：
 * 1、操作数据单位： 字节流(InputStream/OutputStream) 字符流(Reader/Writer)
 * 2、数据的流向：   输入流(InputStream/Reader)       输出流(OutputStream/Writer)
 * 3、流的角色：     节点流  处理流(作用在节点流之上，不能直接处理流数据)
 * <p>
 * 二、流的体系结构：
 * 抽象基类                 节点流(文件流)                                        处理流(缓冲流)
 * InputStream          FileInputStream   (read(byte[] buffer))             BufferedInputStream
 * OutputStream         FileOutputStream  (write(byte[] buffer,0,len)       BufferedOutputStream
 * Reader               FileReader        (read(char[] cbuf)                BufferedReader          (read(char[] cbuf) / readLine())
 * Writer               FileWriter        (write(char[] cbuf,0,len)         BufferedWriter
 */

public class FileReaderWriterTest {

    private static String preName = "src/main/resources/";

    public static void main(String[] args) {
        String fileName = preName + "hello";
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);

            // read() 读入一个字符，文件末尾返回-1
//        int data = fileReader.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fileReader.read();
//        }
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void test01() {
        String fileName = preName + "hello";
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);

            char[] cbuf = new char[5];
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
//                for (int i = 0; i < len; i++) {
//                    System.out.print(cbuf[i]);
//                }
                String s = new String(cbuf, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testReaderWriter () {
        String fileName = preName + "hello";
        File readFile = new File(fileName);
        File writeFile = new File(preName + "output");
        System.out.println(readFile.getAbsolutePath());

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(readFile);
            fileWriter = new FileWriter(writeFile);
            char[] cbuf = new char[5];
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
                fileWriter.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
