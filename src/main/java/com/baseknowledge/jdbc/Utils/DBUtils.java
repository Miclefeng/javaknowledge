package com.baseknowledge.jdbc.Utils;

import java.sql.*;

/**
 * @author miclefengzss
 * 2021/4/1 上午7:57
 */
public class DBUtils {

    /**
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        String urlParams = "jdbc:mysql://localhost:3306/imooc?useSSL=false&useEncoding=true&CharacterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
//        String user = "root";
//        String password = "zss25456585";
        String urlParams = "jdbc:mysql://47.92.239.56:33060/village?useSSL=false&useEncoding=true&CharacterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=Asia/Shanghai";
        String user = "smartcity";
        String password = "ruGMXmy66n9Y";

        return DriverManager.getConnection(urlParams, user, password);
    }

    /**
     * @param rs
     * @param ps
     * @param conn
     */
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
