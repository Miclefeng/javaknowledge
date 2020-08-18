package com.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author miclefengzss
 * @create 2020-08-02 11:22 下午
 */

public class LocalJDBCTransApplication {

    private static final Logger LOG = LoggerFactory.getLogger(LocalJDBCTransApplication.class);

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        LOG.debug("Begin");
        connection.setAutoCommit(false);
    }

    private static Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dist_trans";
        String user = "root";
        String password = "zss25456585";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
        return DriverManager.getConnection(url, user, password);
    }
}
