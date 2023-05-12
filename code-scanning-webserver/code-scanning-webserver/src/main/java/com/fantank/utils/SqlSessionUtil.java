package com.fantank.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SqlSessionUtil {
    private static Map<String, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();

    public static SqlSession getSqlSession(String environment) {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryMap.get(environment);
        System.out.println(environment + " " + sqlSessionFactory);
        if (sqlSessionFactory == null) {
            try {
                // 获取核心文件输入流
                InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

                SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
                // 设置环境属性
                Properties properties = new Properties();
                properties.setProperty("environment", environment);

                // 创建SqlSessionFactory实例
                sqlSessionFactory = sqlSessionFactoryBuilder.build(is,environment ,properties);

                // 将SqlSessionFactory实例存储到Map中
                sqlSessionFactoryMap.put(environment, sqlSessionFactory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 获取SqlSession
        return sqlSessionFactory.openSession(true);
    }
}
