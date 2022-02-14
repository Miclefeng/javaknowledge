package com.designpattern.pattern.creational.prototype;

/**
 * @author miclefengzss
 * 2022/1/12 下午4:36
 */
public class Mail implements Cloneable {

    private String name;
    private String address;
    private String content;

    public Mail() {
        System.out.println("Mail Class constructor.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("clone mail object.");
        return super.clone();
    }

    @Override
    public String toString() {
        return "Mail{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                '}' + super.toString();
    }
}
