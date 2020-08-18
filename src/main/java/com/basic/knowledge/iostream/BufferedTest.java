package com.basic.knowledge.iostream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author miclefengzss
 * @create 2020-07-27 11:27 上午
 */

public class BufferedTest {

    private String preName = "src/main/resources/";

    @Test
    public void bufferedInputStreamTest() {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File inputFile = new File(preName + "hello");
            File outputFile = new File(preName + "helloOutput");

            FileInputStream fileInputStream = new FileInputStream(inputFile);
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void bufferedReaderWriterTest() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File inputFile = new File(preName + "hello");
            File outputFile = new File(preName + "helloOutput");

            FileReader fileReader = new FileReader(inputFile);
            FileWriter fileWriter = new FileWriter(outputFile);

            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = bufferedReader.read(cbuf)) != -1) {
//                bufferedWriter.write(cbuf, 0, len);
//                bufferedWriter.newLine();
//            }
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                bufferedWriter.write(data + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
