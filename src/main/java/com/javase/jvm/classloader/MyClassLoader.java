package com.javase.jvm.classloader;

import java.io.*;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/4/20 15:06
 */
public class MyClassLoader extends ClassLoader {

    static int seed = 0x02468ACE;

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        File f = new File("", name.replace(".", "/").concat(".myclass"));
        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b;
            while ((b = fis.read()) != -1) {
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();
            defineClass(name, bytes, 0, bytes.length);

            fis.close();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return super.findClass(name);
    }

    public static void main(String[] args) {

    }

    /**
     * 加密 class 文件
     *
     * @param name
     * @throws IOException
     */
    private static void encryFile(String name) throws IOException {
        File f = new File("", name.replace(".", "/").concat(".myclass"));
        FileInputStream fis = new FileInputStream(f);
        FileOutputStream fos = new FileOutputStream(new File("", name.replace(".", "/").concat(".myencryclass")));
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b ^ seed);
        }
        fis.close();
        fos.close();
    }
}
