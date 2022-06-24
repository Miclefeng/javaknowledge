package com.javase.base.iostream;

import java.io.*;

/**
 * @author miclefengzss
 */
public class ObjectStreamDemo {

    public static void main(String[] args) {
        Goods macBookPro = new Goods("0001", "macBook pro", 18000.0);
        Goods iphoneXr = new Goods("0002", "iphone xr", 6400.0);
        String fileName = "src/main/resources/file/imooc.txt";
        FileOutputStream fos = null;
        FileInputStream fis = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            // 追加方式写
            fos = new FileOutputStream(fileName, true);
            oos = new ObjectOutputStream(fos);
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);

            // 写入
            oos.writeObject(macBookPro);
            oos.writeObject(iphoneXr);
            oos.flush();

            // 读取
            Goods o = (Goods) ois.readObject();
            System.out.println(o);
            o = (Goods) ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
