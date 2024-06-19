package com.designpattern.pattern.behavioral.observer;

import java.util.Date;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:30
 */
public class LotteryResult {

    private String uid;

    private String message;

    private Date dateTime;

    public LotteryResult(String uid, String message, Date dateTime) {
        this.uid = uid;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LotteryResult{" +
                "uid='" + uid + '\'' +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
