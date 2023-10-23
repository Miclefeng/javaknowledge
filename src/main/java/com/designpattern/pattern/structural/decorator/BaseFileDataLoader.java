package com.designpattern.pattern.structural.decorator;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 17:18
 */
public class BaseFileDataLoader implements DataLoader {

    private String filePath;

    public BaseFileDataLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(String data) {
        try {
            FileUtils.writeStringToFile(new File(filePath), data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String read() {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
