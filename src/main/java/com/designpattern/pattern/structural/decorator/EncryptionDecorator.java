package com.designpattern.pattern.structural.decorator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 17:00
 */
public class EncryptionDecorator extends DataLoaderDecorator {

    public EncryptionDecorator(DataLoader dataLoader) {
        super(dataLoader);
    }

    @Override
    public String read() {
        return decode(super.read());
    }

    @Override
    public void write(String data) {
        super.write(encode(data));
    }

    public String encode(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    public String decode(String data) {
        return new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
    }
}
