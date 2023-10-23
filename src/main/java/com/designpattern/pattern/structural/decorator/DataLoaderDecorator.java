package com.designpattern.pattern.structural.decorator;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 17:16
 */
public class DataLoaderDecorator implements DataLoader {

    private DataLoader dataLoader;

    public DataLoaderDecorator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void write(String data) {
        this.dataLoader.write(data);
    }

    @Override
    public String read() {
        return this.dataLoader.read();
    }
}
