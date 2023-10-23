package com.designpattern.pattern.structural.proxy;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/23 11:57
 */
public class IUserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("执行Dao方法");
    }
}
