package com.designpattern.pattern.structural.bridge;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 15:31
 */
public class FacePayMode implements IPayMode {
    @Override
    public boolean security(String uid) {
        System.out.println("人脸支付，风向控制-面部识别");
        return true;
    }
}
