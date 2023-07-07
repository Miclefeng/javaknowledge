package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:04
 */
public class Lottery {

    private String userId;

    private String message;

    private int time;

    public Lottery(String userId, String message, int time) {
        this.userId = userId;
        this.message = message;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
