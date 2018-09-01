package com.wp.myboot.controller;


import com.boot.commons.utils.DateUtil;
import com.boot.commons.utils.MD5;
import com.boot.commons.utils.SignUtils;
import com.wp.myboot.common.Constants;
import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.service.Mp4Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/wechatPayController")
public class WechatPayController {

    private final Logger log = LoggerFactory.getLogger(WechatPayController.class);

    @Resource
    private Mp4Service mp4Service;

    @PostMapping(value="/wechat_pay")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public void wechat_pay(String userPhoneNumber,String money,HttpServletRequest request,HttpServletResponse response){
        log.info("------wechat_pay------userPhoneNumber:"+userPhoneNumber+"----money:"+money);
        String ip=getIp(request);
        String key= Constants.wechat_key;//秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("mch_id", Constants.wechat_mch_id);//商户/
        params.put("out_trade_no",System.currentTimeMillis()+"");//商户订单
        params.put("body", "vip");//商品
        params.put("total_fee", money);//总价
        params.put("spbill_create_ip", ip);//客户端ip
        params.put("notify_url", Constants.wechat_notify_url);
        params.put("redirect_url", Constants.wechat_redirect_url);//前端跳转地址
        params.put("trade_type", Constants.wechat_trade_type);//支付方式

        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
        try {
            String httUrl = Constants.wechat_url + signRecieve + "&" + preStr;
            //保存订单
            mp4Service.saveOrder(userPhoneNumber,params.get("out_trade_no"),params.get("total_fee"), DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
            //跳转
            response.sendRedirect(httUrl);
        }catch (Exception e){
            log.error("跳转错误"+e);
        }

    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
