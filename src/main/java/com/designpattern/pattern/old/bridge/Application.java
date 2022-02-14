package com.designpattern.pattern.old.bridge;

/**
 * @author miclefengzss
 */
public class Application {

    public static void main(String[] args) {
        AbstractPhoneBridge upPhone = new UpPhone(new Xiaomi());
        upPhone.open();
        upPhone.call();
        upPhone.close();
        System.out.println("=======================");
        AbstractPhoneBridge foldedPhone = new FoldedPhone(new Vivo());
        foldedPhone.open();
        foldedPhone.call();
        foldedPhone.close();
    }
}
