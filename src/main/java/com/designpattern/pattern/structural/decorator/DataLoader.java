package com.designpattern.pattern.structural.decorator;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 17:00
 */
public interface DataLoader {

    void write(String data);

    String read();
}
