package com.designpattern.pattern.structural.bridge;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 15:34
 */
public class SecretPayMode implements IPayMode{
    @Override
    public boolean security(String uid) {
        System.out.println("密码支付，风险控制-环境完全");
        return true;
    }
}
