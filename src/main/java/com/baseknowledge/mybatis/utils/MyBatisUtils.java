package com.baseknowledge.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author miclefengzss
 * 2021/4/22 上午8:29
 */
public class MyBatisUtils {

    /**
     * static 静态属性属于类不属于对象，全局唯一
     */
    private static SqlSessionFactory sqlSessionFactory = null;

    // 初始化 SqlSessionFactory
    static {
        Reader reader;

        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个 SQLSession 对象
     *
     * @return
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 释放一个有效的 SQLSession 资源
     *
     * @param sqlSession
     */
    public static void closeSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
