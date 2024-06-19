package com.designpattern.pattern.behavioral.strategy;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 21:50
 */
public class Receipt {

    private String type;

    private String message;

    public Receipt() {
    }

    public Receipt(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
