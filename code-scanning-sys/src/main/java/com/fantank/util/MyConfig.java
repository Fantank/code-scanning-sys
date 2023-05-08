package com.fantank.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {
    public static Properties prop = new Properties();
    static {
        try {
            prop.load(new FileInputStream("src/main/resources/Configuration.properties"));
        } catch (IOException e) {
            System.out.println("配置文件加载错误");
            throw new RuntimeException(e);
        }
    }

    public static String getConfig(String config) throws IOException {
        return prop.getProperty(config);
    }
}
