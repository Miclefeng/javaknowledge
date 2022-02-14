package com.baseknowledge.iostream;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author miclefengzss
 * @create 2020-07-27 2:10 下午
 * <p>
 * 转换流：
 * 1、转换流属于字符流(字节流和字符流之间的相互转换)
 * InputStreamReader  将输入的字节流转换为输入的字符流
 * OutputStreamWriter 将输入的字符流转为输出的字节流
 * <p>
 * 多用于字符编码的转换
 */

public class StreamReaderWriterTest {

    private String preName = "src/main/resources/";

    @Test
    public void streamReaderWriterTest() {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File srcFile = new File(preName + "hello");
            File destFile = new File(preName + "hello_gbk");

            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);

//          转换流可以包在缓冲流之上
            isr = new InputStreamReader(new BufferedInputStream(fileInputStream), StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(new BufferedOutputStream(fileOutputStream), "GBK");

            char[] cbuf = new char[1024];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test02() {
//        缓冲流不能包在转换流上
//        File srcFile = new File(preName + "hello");
//        File destFile = new File(preName + "hello_gbk");
//
//        FileInputStream fileInputStream = new FileInputStream(srcFile);
//        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
//        BufferedOutputStream bos = new BufferedOutputStream(new OutputStreamWriter(fileOutputStream, "GBK"));
//
//        char[] cbuf = new char[1024];
//        int len;
//        while ((len = bufferedReader.read(cbuf)) != -1) {
//            bos.write(cbuf, 0, len);
//        }
    }
}
