package com.basic.knowledge.reflect;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author miclefengzss
 * @create 2020-07-28 4:23 下午
 */

public class ClassLoaderTest {

    @Test
    public void test() {
        Properties properties = new Properties();

        FileReader fileReader = null;
        try {
            String preName = "src/";
            String fileName = "main/resources/jdbc.properties";

            // 1、文件默认位置在 module 下
            fileReader = new FileReader(preName + fileName);
            properties.load(fileReader);

            // 2、文件默认位置在 module/src/main/resources 下
            ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
            properties.load(resourceAsStream);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            System.out.println(user + " : " + password);
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
}
