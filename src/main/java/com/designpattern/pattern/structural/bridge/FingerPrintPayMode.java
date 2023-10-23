package com.designpattern.pattern.structural.bridge;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 15:32
 */
public class FingerPrintPayMode implements IPayMode{
    @Override
    public boolean security(String uid) {
        System.out.println("指纹支付，风险控制-指纹识别");
        return true;
    }
}
