package com.wp.myboot.controller;


import com.jcraft.jsch.HASH;
import com.wp.myboot.service.Mp4Service;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/wechatCallbcak")
public class WechatCallbcak {
    private final Logger log = LoggerFactory.getLogger(WechatCallbcak.class);

    @Autowired
    private Mp4Service mp4Service;

    /**
     * 微信支付回调
     * @param request
     * @param response
     */
    @RequestMapping(value="/wechatPayCallback")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public void wechat_pay(ModelAndView modelAndView,HttpServletRequest request, HttpServletResponse response){
        log.info("微信服务器发来支付成功通知");
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        try {
            inputStreamReader = new InputStreamReader(request.getInputStream(),"UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuilder builder=new StringBuilder();
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
            log.info("======================报文内容==================="+builder.toString());
            Map<String,String> map=new HashMap<String,String>();
            if(!org.springframework.util.StringUtils.isEmpty(builder)){
                String[] AttayStr=builder.toString().split("&");
                for (int i=0;i<AttayStr.length;i++){
                    String[] qqqU=AttayStr[i].split("=");
                    map.put(qqqU[0],qqqU[1]);
                }
                log.info("map集合:"+map.toString());
                //查询出订单信息
                Map<String, String> order = mp4Service.findMp4OrderInfo(map.get("out_trade_no"), null);
                if(order!=null){
                    //判断订单是否已经支付成功
                    Map<String, String> orderSuccess = mp4Service.findMp4OrderInfo(map.get("out_trade_no"), 1);
                    if (orderSuccess == null || orderSuccess.isEmpty()) {
                        //更新订单并且更该用户时间
                        mp4Service.updateMap4Order(order.get("trade_order"));
                        //判断重置金额
                        int money = Integer.valueOf(order.get("total_fee"));
                        //18元
                        //                  if(1800==money){
                        //                        int time=30;
                        //                      mp4Service.updateUserTime(order.get("phoneNumber"));
                        //                  }else if(18800==money){
                        //                      int time=36500;
                        //                      mp4Service.updateUserTime(order.get("phoneNumber"));
                        //                  }
                        if (1 == money) {
                            int time = 30;
                            mp4Service.updateUserTime(order.get("phoneNumber"), time);
                        } else if (2 == money) {
                            int time = 36500;
                            mp4Service.updateUserTime(order.get("phoneNumber"), time);
                        }
                        log.info("=====================回调成功================");
                    }
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
