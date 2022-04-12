package com.javafirst.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String configXml = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(configXml);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSqlSession() {
        SqlSession sqlSession = null;
        if (null != sqlSessionFactory) {
            sqlSession = sqlSessionFactory.openSession();
        } else {
            String configXml = "mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(configXml);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                sqlSession = sqlSessionFactory.openSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sqlSession;
    }
}
