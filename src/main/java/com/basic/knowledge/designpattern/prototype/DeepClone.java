package com.basic.knowledge.designpattern.prototype;

import java.io.*;

/**
 * @author miclefengzss
 */
public class DeepClone implements Serializable, Cloneable {

    private String name;

    public DeepCloneTarget deepCloneTarget;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 重写 clone 实现深拷贝
     *
     * @return
     */
    @Override
    protected DeepClone clone() {
        DeepClone deepClone = null;
        try {
            deepClone = (DeepClone) super.clone();
            deepClone.deepCloneTarget = deepCloneTarget.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deepClone;
    }

    /**
     * 通过对象的序列化实现深拷贝(推荐使用)
     *
     * @return
     */
    public DeepClone deepClone() {
        DeepClone deepClone = null;
        // 创建字节流对象
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 序列化
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            // 反序列化
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);

            deepClone = (DeepClone) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
                objectOutputStream.close();
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return deepClone;
    }
}

class DeepCloneTarget implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private String cloneName;

    private String cloneClass;

    public DeepCloneTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    @Override
    protected DeepCloneTarget clone() throws CloneNotSupportedException {
        return (DeepCloneTarget) super.clone();
    }
}

class Client {

    public static void main(String[] args) {
        DeepClone deepClone = new DeepClone();
        deepClone.setName("miclefeng");
        deepClone.deepCloneTarget = new DeepCloneTarget("clone", "cloneClass");

        // DeepClone deepClone2 = deepClone.clone();
        DeepClone deepClone2 = deepClone.deepClone();

        System.out.println("deepClone.name=" + deepClone.getName() + ", deepClone.deepCloneTarget=" + deepClone.deepCloneTarget.hashCode());
        System.out.println("deepClone2.name=" + deepClone2.getName() + ", deepClone2.deepCloneTarget=" + deepClone2.deepCloneTarget.hashCode());
    }
}
