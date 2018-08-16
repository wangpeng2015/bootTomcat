package com.boot.commons.utils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

public class DbcpUtils {

    public static void main(String[] args) throws  Exception{

        Connection conn = null;
        String url = "jdbc:mysql://101.132.168.202:3306/goo?useUnicode=true&characterEncoding=utf8";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String user = "goouser";
        String password = "Haier,123";

        DbUtils.loadDriver(jdbcDriver);
        try {
            conn = DriverManager.getConnection(url, user, password);
            QueryRunner qr = new QueryRunner();
            List results = (List) qr.query(conn, "select id,username from user limit 0,10",new MapListHandler());
            for (int i = 0; i < results.size(); i++) {
                Map map = (Map) results.get(i);
                System.out.println("id:" + map.get("id") + ",name:" + map.get("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }
}
