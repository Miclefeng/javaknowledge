package com.db.jdbc;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author miclefengzss
 * 2023/3/3 下午1:49
 */
public class TestJDBC {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/learn?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&cachePrepStmts=true&rewriteBatchedStatements=true";
    private static String user = "root";
    private static String password = "zss25456585";

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        LinkedList<Savepoint> savepointList = new LinkedList<>();

        try {
//            Driver driver = new com.mysql.cj.jdbc.Driver(); // 类中通过 静态代码块 进行了加载和注册，所以可以通过反射加载驱动 Driver
//        DriverManager.registerDriver(driver);
            // 通过反射加载 Driver 类，并注册驱动
            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false); // 设置事务手动提交
            // 查询
//            preparedStatement = connection.prepareStatement("select * from dept where loc=?");
//            preparedStatement.setString(1, "beijing");
//            final ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getInt(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4));
//            }

            // 新增
//            preparedStatement = connection.prepareStatement("insert into dept values(DEFAULT ,?,?,?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, 6);
//            preparedStatement.setString(2, "hard");
//            preparedStatement.setString(3, "beijing");
//            final int rows = preparedStatement.executeUpdate();
//            if (rows > 0) {
//                final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    int id = generatedKeys.getInt(1);
//                    System.out.println("increment id: " + id);
//                }
//            }

            // 批处理
            preparedStatement = connection.prepareStatement("insert into dept values(DEFAULT ,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            for (int i = 101; i <= 200; i++) {
                preparedStatement.setInt(1, i + 7);
                preparedStatement.setString(2, "name" + i);
                preparedStatement.setString(3, "loc" + i);
                preparedStatement.addBatch(); // 将修改放到一个批次中
                if (i % 50 == 0) {
                    // 设置回滚点
                    Savepoint savepoint = connection.setSavepoint();
                    savepointList.add(savepoint);
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch(); // 清除批处理的数据
        } catch (Exception e) {
            if (null != connection) {
                try {
                    Savepoint savepoint = savepointList.getLast();
                    if (null != savepoint) {
                        // 回滚到最后一个点
                        connection.rollback(savepoint);
                    } else {
                        connection.rollback();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
