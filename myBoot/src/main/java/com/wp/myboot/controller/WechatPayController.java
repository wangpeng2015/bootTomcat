package com.wp.myboot.controller;


import com.boot.commons.utils.MD5;
import com.boot.commons.utils.SignUtils;
import com.wp.myboot.controller.result.SpringResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/wechatPayController")
public class WechatPayController {

    private final Logger log = LoggerFactory.getLogger(WechatPayController.class);

    @RequestMapping(value="/wechat_pay")
    @ResponseBody
    public void wechat_pay(){

        String key="2ddd253c7256045df0179b5627845d0e";//秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("mch_id", "sl384hgf");//商户/
        params.put("out_trade_no",System.currentTimeMillis()+"");//商户订单
        params.put("body", "vip");//商品
        params.put("total_fee", "1");//总价
        params.put("spbill_create_ip", "223.81.193.121");//客户端ip
//        params.put("notify_url", "http://640661.ichengyun.net:8088/gooSeNew/employeeController/updateCustomersDateEnd");//服务器通知地址
        params.put("notify_url", "http://223.81.193.121:8081/wechatCallbcak/wechatPayCallback");
        params.put("redirect_url", "http://223.81.193.121:8081/gooController/uploadPicture");//前端跳转地址
        params.put("trade_type", "WX");//支付方式

        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
        String httUrl = "http://weixin.cf90v5.cn/platform/pay/unifiedorder/video?sign=" + signRecieve + "&" + preStr;
        System.err.println(httUrl);

    }



}
