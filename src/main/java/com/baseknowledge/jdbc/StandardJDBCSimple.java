package com.baseknowledge.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @author miclefengzss
 * 2021/3/31 下午10:44
 */
public class StandardJDBCSimple {

    public static void main(String[] args) {

        Connection connection = null;
        try {
            // Class.forName 方法的作用，就是初始化给定的类
            // 1. 加载注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
//            DriverManager.registerDriver(new Driver()); == Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "zss25456585");
            // 3. 创建 statement 对象，执行sql语句
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `employee`");
            // 4. 遍历查询结果
            while (resultSet.next()) {
                int eno = resultSet.getInt(1);
                String name = resultSet.getString("ename");
                float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + " - " + eno + " - " + name + " - " + salary);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    // 5. 关闭连接
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
