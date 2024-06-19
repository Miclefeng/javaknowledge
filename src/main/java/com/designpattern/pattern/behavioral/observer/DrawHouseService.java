package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:34
 */
public class DrawHouseService {

    //摇号抽签
    public String lots(String uid) {
        if (uid.hashCode() % 2 == 0) {
            return "恭喜ID为: " + uid + " 的用户,在本次摇号中中签!";
        } else {
            return "很遗憾,ID为: " + uid + "的用户,您本次未中签!";
        }
    }
}
