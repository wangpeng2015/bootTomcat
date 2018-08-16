package com.boot.commons.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.util.pattern.PathPattern;

public class Codecutils {

    /**
     * 加密
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws  Exception{

        String str="123";
        Base64 base64 = new Base64();
        try {
            str = base64.encodeToString(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Base64 编码后："+str);

    }
}
