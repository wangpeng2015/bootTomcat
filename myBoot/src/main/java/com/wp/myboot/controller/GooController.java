package com.wp.myboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.commons.utils.HttpClient;
import com.boot.commons.utils.XmlUtil;
import com.wp.myboot.RedisSessionConfig.RedisUtil;
import com.wp.myboot.common.Constants;
import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.entity.UserSeNew;
import com.wp.myboot.service.GooService;
import com.wp.myboot.service.Mp4Service;
import com.wp.myboot.service.UserSeNewService;
import com.wp.myboot.sms_253.ChuangLanSmsUtil;
import com.wp.myboot.sms_253.request.SmsSendRequest;
import com.wp.myboot.sms_253.response.SmsSendResponse;
import org.apache.commons.lang3.StringUtils;
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
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/gooController")
public class GooController {

    private final Logger log = LoggerFactory.getLogger(GooController.class);

    @Autowired
    private UserSeNewService userSeNewService;

    @Autowired
    private Mp4Service mp4Service;

    @Autowired
    private GooService gooService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 首页信息
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView getIndexJsp(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 文件上传界面
     * @return
     */
    @RequestMapping("/upLoad")
    public ModelAndView getUploadJsp(ModelAndView modelAndView){
          modelAndView.setViewName("upload");
//        List<Map<String, String>> list = Lists.newArrayList();
//        Map<String, String> map=new HashMap<String, String>();
//        map.put("type_id","123");
//        map.put("name","123");
//        list.add(map);map

        List<Map<String, String>> list=mp4Service.getCateType();
        modelAndView.addObject("movieList",list);
        return modelAndView;
    }

    /**
     * 图片轮播上传界面
     * @return
     */
    @RequestMapping("/uploadPicture")
    public ModelAndView getUploadPicture(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("uploadPicture");
        return modelAndView;
    }

    /**
     * 支付页面
     * @return
     */
    @RequestMapping("/pay")
    public ModelAndView pay(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pay");
        return modelAndView;
    }

    /**
     * 支付页面
     * @return
     */
    @RequestMapping("/payBackResult")
    public ModelAndView payBackResult(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("payBackResult");
        return modelAndView;
    }

    @RequestMapping(value="/doLogin")
    @ResponseBody
    public Object loginIn(String name,String passwd,String uid,HttpServletRequest request){
        SpringResult result=new SpringResult();
        UserSeNew userSeNew=userSeNewService.findUser(name,passwd,uid);
        return  result;
    }


    /**
     *
     * @Description:android的登录
     * @param phone
     * @param passwd
     * @param confirmPass
     * @param code
     * @param tuijianCode
     * @param request
     * @param response1
     * @return
     *
     * @author wp
     * @date 2018年1月20日 上午11:35:33
     * @throws
     */
    @RequestMapping(value="/doSign")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONObject doSign(String phone, String passwd, String confirmPass, String code, String tuijianCode, HttpServletRequest request, HttpServletResponse response1){
        JSONObject obj = new JSONObject();
        if(StringUtils.isBlank(phone)){
            obj.put("message","用户名为空");
            obj.put("resultCode","300");
            return obj;
        }
        if(StringUtils.isBlank(code)){
            obj.put("message","验证码为空");
            obj.put("resultCode","300");
            return obj;
        }
        //HttpSession session=request.getSession();
        //System.out.println(session.getId());
        //Object yanzhengma=session.getAttribute(phone);
        String ranTime=(String)redisUtil.get(phone);
        //yanzhengma=yanzhengma.toString();
        String yanzhengma=ranTime;
        if(yanzhengma==null || !yanzhengma.equals(code) || yanzhengma.equals("")){
            obj.put("message","验证码有误");
            obj.put("resultCode","300");
            return obj;
        }
        if(StringUtils.isBlank(passwd)){
            obj.put("message","请输入密码");
            obj.put("resultCode","300");
            return obj;
        }
        if(StringUtils.isBlank(confirmPass)){
            obj.put("message","请输入确认码");
            obj.put("resultCode","300");
            return obj;
        }
        phone=phone.trim();
        passwd=passwd.trim();
        confirmPass=confirmPass.trim();

        if(!passwd.equals(confirmPass)){
            obj.put("message","密码和确认码不相符");
            obj.put("resultCode","300");
            return obj;
        }
        Integer res=gooService.addSignUserMsg(phone,passwd,confirmPass,tuijianCode);
        if (res>0){
            obj.put("message","注册成功");
            obj.put("resultCode","200");
            return obj;
        }else if(res==-2){
            obj.put("message","该用户已经注册");
            obj.put("resultCode","300");
            return obj;
        }else{
            obj.put("message","注册失败");
            obj.put("resultCode","300");
            return obj;
        }
    }


    /**
     *
     * @Description:android的登录   企业信使运营管理平台 350元  5000条
     * @param phoneNumber
     * @param request
     * @return
     */
    //@RequestMapping(value="/getYanZhengMa")
    @ResponseBody
    public JSONObject getYanZhengMa(String phoneNumber,HttpServletRequest request){
        log.info("--------------获取验证码---------------");
        JSONObject obj = new JSONObject();
        if(StringUtils.isBlank(phoneNumber)  || phoneNumber.length()!=11){
            obj.put("message","电话信息为空或者格式有问题");
            obj.put("resultCode","300");
            return obj;
        }
        //发短信
        HttpSession session=request.getSession();
        try {
            //存session
            int ran=(int)((Math.random()*9+1)*10000);
            log.info("验证码--"+phoneNumber+"--"+ran);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("action", "send");
            paramMap.put("userid", Constants.xishi_userid);
            paramMap.put("account", Constants.xishi_account);
            paramMap.put("password", Constants.xishi_password);
            paramMap.put("mobile", phoneNumber.trim());
            paramMap.put("content", "【小小播】尊敬的用户,验证码为:"+ran+",该验证码5分钟之内有效");
            paramMap.put("sendTime", "");
            paramMap.put("extno", "");
//			    response = SmsDemo.sendSms(phoneNumber,ran);
            log.info("短信接口返回的数据----------------");
            //查明细
            String xml= HttpClient.doPostHp(Constants.url_xinshi,paramMap);
			      /*返回状态值：成功返回Success 失败返回：Faild*/
            String res= XmlUtil.getValueByNameXml(xml,"returnstatus");
            log.info("------------------"+phoneNumber+"-------"+res+"-------------------------");
            if("Success".equals(res)){
                //session.setAttribute(phoneNumber, ran);
                redisUtil.set(phoneNumber,String.valueOf(ran),Long.valueOf("300"));
            }else{
                log.info("短信发送失败");
                obj.put("message","短信发送失败");
                obj.put("resultCode","400");
            }
        } catch (Exception e) {
            log.info("短信发送失败");
            log.info(e.toString());
            obj.put("message","短信发送失败");
            obj.put("resultCode","400");
        }
        return obj;
    }


    /**
     *
     * @Description:android的登录   253云通讯平台
     * @param phoneNumber
     * @param request
     * @return
     */
    @RequestMapping(value="/getYanZhengMa")
    @ResponseBody
    public JSONObject getYanZhengMa2(String phoneNumber,HttpServletRequest request){
        log.info("--------------获取验证码---------------");
        JSONObject obj = new JSONObject();
        if(StringUtils.isBlank(phoneNumber)  || phoneNumber.length()!=11){
            obj.put("message","电话信息为空或者格式有问题");
            obj.put("resultCode","300");
            return obj;
        }

        try {
            //存session
            int ran=(int)((Math.random()*9+1)*10000);
            log.info("验证码--"+phoneNumber+"--"+ran);
            //发短信
            HttpSession session=request.getSession();
//          判断时间段
            Calendar cal=Calendar.getInstance();
            int time=cal.get(Calendar.HOUR_OF_DAY);
            log.info("--------------------------------------------------------------------------------------------------");
            if(time>Constants.startTime && time<Constants.endTime){
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("action", "send");
                paramMap.put("userid", Constants.xishi_userid);
                paramMap.put("account", Constants.xishi_account);
                paramMap.put("password", Constants.xishi_password);
                paramMap.put("mobile", phoneNumber.trim());
                paramMap.put("content", "【小小播】尊敬的用户,验证码为:"+ran+",该验证码5分钟之内有效");
                paramMap.put("sendTime", "");
                paramMap.put("extno", "");
//			    response = SmsDemo.sendSms(phoneNumber,ran);
                log.info("短信接口返回的数据----------------");
                //查明细
                String xml= HttpClient.doPostHp(Constants.url_xinshi,paramMap);
                /*返回状态值：成功返回Success 失败返回：Faild*/
                String res= XmlUtil.getValueByNameXml(xml,"returnstatus");
                log.info("------------------"+phoneNumber+"-------"+res+"-------------------------");
                if("Success".equals(res)){
                    session.setAttribute(phoneNumber, ran);
                    redisUtil.set(phoneNumber,String.valueOf(ran),Long.valueOf("300"));
                }else{
                    log.info("短信发送失败");
                    obj.put("message","短信发送失败");
                    obj.put("resultCode","400");
                }
            }else {
                log.info("--------------------------------------------------------------------------------------------------");
                //短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
//                String smsSingleRequestServerUrl = "https://smssh1.253.com/msg/send/json";
                // 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
                String msg = "【小小播】尊敬的用户,验证码为"+ran+",该验证码5分钟之内有效";
                //手机号码
                String phone = phoneNumber;
                //状态报告
                String report= "true";

                SmsSendRequest smsSingleRequest = new SmsSendRequest(Constants.account, Constants.password, msg, phone,report);

                String requestJson = JSON.toJSONString(smsSingleRequest);

                System.out.println("before request string is: " + requestJson);

                String response = ChuangLanSmsUtil.sendSmsByPost(Constants.url_253_smsSingleRequestServerUrl, requestJson);

                System.out.println("response after request result is :" + response);

                SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

                System.out.println("response  toString is :" + smsSingleResponse);
                log.info("--------------------------------------------------------------------------------------------------");
                //查明细
                if("0".equals(smsSingleResponse.getCode())){
                    session.setAttribute(phoneNumber, ran);
                    redisUtil.set(phoneNumber,String.valueOf(ran),Long.valueOf("300"));
                }else{
                    log.info("短信发送失败");
                    obj.put("message","短信发送失败");
                    obj.put("resultCode","400");
                }
            }
        } catch (Exception e) {
            log.info("短信发送失败");
            log.info(e.toString());
            obj.put("message","短信发送失败");
            obj.put("resultCode","400");
        }
        return obj;
    }



}
