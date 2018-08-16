package com.boot.commons.utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigurationUtils {

    /**
     * 文件配置
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws  Exception{

        PropertiesConfiguration config = new PropertiesConfiguration("D:\\usergui.properties");
        config.setProperty("colors.background", "#000000");
        config.setProperty("window.width", "400");
        config.save();

    }
}
