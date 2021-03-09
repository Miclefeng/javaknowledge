package com.designpattern.bridge;

/**
 * @author miclefengzss
 */
public class FoldedPhone extends AbstractPhoneBridge {

    public FoldedPhone(PhoneBrand phoneBrand) {
        super(phoneBrand);
    }

    @Override
    protected void open() {
        System.out.println(" FoldedPhone ");
        super.open();
    }

    @Override
    protected void call() {
        System.out.println(" FoldedPhone ");
        super.call();
    }

    @Override
    protected void close() {
        System.out.println(" FoldedPhone ");
        super.close();
    }
}
