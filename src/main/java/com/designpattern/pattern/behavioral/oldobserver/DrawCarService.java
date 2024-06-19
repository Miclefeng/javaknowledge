package com.designpattern.pattern.behavioral.oldobserver;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:45
 */
public class DrawCarService {
    public String lots(String userId) {
        String msg;
        if ((userId.hashCode() & 1) == 0) {
            msg = "恭喜用户ID:" + userId + " 中签";
        } else {
            msg = "很遗憾,用户ID: " + userId + " 未中签";
        }
        return msg;
    }
}
