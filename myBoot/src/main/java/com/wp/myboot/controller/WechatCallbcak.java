package com.wp.myboot.controller;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping(value="/wechatCallbcak")
public class WechatCallbcak {

    private final Logger log = LoggerFactory.getLogger(WechatCallbcak.class);

    @RequestMapping(value="/wechatPayCallback")
    @ResponseBody
    public void wechat_pay(HttpServletRequest request, HttpServletResponse response){
        log.info("微信服务器发来支付成功通知");
        OutputStream writer = null;
        boolean flag = false;
        String backxmlInfo = "";
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        try {
            InputStream reader = request.getInputStream();
            int count = 0;
            byte[] buffer = new byte[1024];
            StringBuilder inputString = new StringBuilder();
            while ((count = reader.read(buffer, 0, 1024)) > 0) {
                inputString.append(new String(buffer, 0, count, "UTF-8"));
            }
            try {
                writer = response.getOutputStream();
                if (reader != null) {
                    reader.close();
                }

                log.info("----[微信回调]接收到的报文---" + inputString.toString());

                if (!StringUtils.isEmpty(inputString.toString())) {

                }
            }catch (Exception e){

            }
        }catch (Exception e){

        }

    }
}
