package com.javase.base.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author miclefengzss
 */
public class Application {

    public static void say() {
        try {
            // 加载 properties 配置文件
            Properties properties = new Properties();
            InputStream resourceAsStream = Application.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(resourceAsStream);
            String language = properties.getProperty("language");
            System.out.println(language);
            // 通过反射获取类的实例
            I18N o = (I18N) Class.forName(language).newInstance();
            o.say();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String s = "hello world.";
        ClassUtil classUtilString = new ClassUtil(s);
        classUtilString.printClassMessage();
        classUtilString.printFieldMessage();
        classUtilString.printConstructorMessage();

        Integer i = 1;
        ClassUtil classUtilInteger = new ClassUtil(i);
        classUtilInteger.printClassMessage();
        classUtilInteger.printFieldMessage();
        classUtilInteger.printConstructorMessage();
        System.out.println("========================================");
        say();
    }
}
