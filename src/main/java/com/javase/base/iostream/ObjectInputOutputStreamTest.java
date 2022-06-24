package com.javase.base.iostream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author miclefengzss
 * @create 2020-07-28 12:24 上午
 *
 * 对象流的使用：
 * ObjectInputStream ObjectOutputStream
 * 用于存储或读取基本数据类型或者对象的处理流
 * java对象要想序列化
 * 1、必须实现 Serializable 接口
 * 2、定义一个静态long类型的常量 serialVersionUID (序列化唯一标识)
 * 3、内部属性必须是可序列化的，如果有其它类属性，那么其它类属性也需要实现 重复实现这 3 点
 *
 * ObjectInputStream ObjectOutputStream 不能序列化 static 和 transient 修饰的成员变量
 */

public class ObjectInputOutputStreamTest {

    private String preName = "src/main/resources/";

    /**
     * 序列化： 将内存中的java对象保存到磁盘或者通过网络传输
     * ObjectOutputStream
     */
    @Test
    public void testOutputStream() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(preName + "objectExec"));
            oos.writeObject("输出无敌麦克风SUCCESSFUL");
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化：将磁盘中的对象或者网络中的数据转为为内存中的java对象
     * ObjectInputStream
     */
    @Test
    public void testInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(preName + "objectExec"));
            Object object = ois.readObject();
            String str = (String) object;
            System.out.println(str);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
