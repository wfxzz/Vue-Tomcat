package com.jmbon.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource  = "mybatis-config.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            sqlSessionFactory = factory;
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    private SqlSessionFactoryUtils(){}
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
