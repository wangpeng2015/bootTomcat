package com.wp.myboot.controller;


import com.boot.commons.utils.DateUtil;
import com.boot.commons.utils.MD5;
import com.boot.commons.utils.SignUtils;
import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.service.Mp4Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @RequestMapping(value="/wechat_pay")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public void wechat_pay(String userPhoneNumber,HttpServletRequest request,HttpServletResponse response){

        String ip=getIp(request);
        String key="2ddd253c7256045df0179b5627845d0e";//秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("mch_id", "sl384hgf");//商户/
        params.put("out_trade_no",System.currentTimeMillis()+"");//商户订单
        params.put("body", "vip");//商品
        params.put("total_fee", "1");//总价
        params.put("spbill_create_ip", ip);//客户端ip
//        params.put("notify_url", "http://640661.ichengyun.net:8088/gooSeNew/employeeController/updateCustomersDateEnd");//服务器通知地址
        params.put("notify_url", "http://640661.ichengyun.net:8081/wechatCallbcak/wechatPayCallback");
        params.put("redirect_url", "http://640661.ichengyun.net:8081/gooController/uploadPicture");//前端跳转地址
        params.put("trade_type", "WX");//支付方式

        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
        try {
            String httUrl = "http://weixin.06302s.cn/platform/pay/unifiedorder/video?sign=" + signRecieve + "&" + preStr;
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
