package com.basic.knowledge.designpattern.bridge;

/**
 * @author miclefengzss
 */
public class UpPhone extends AbstractPhoneBridge {

    public UpPhone(PhoneBrand phoneBrand) {
        super(phoneBrand);
    }

    @Override
    public void open() {
        System.out.println(" UpPhone ");
        super.open();
    }

    @Override
    public void call() {
        System.out.println(" UpPhone ");
        super.call();
    }

    @Override
    public void close() {
        System.out.println(" UpPhone ");
        super.close();
    }
}
