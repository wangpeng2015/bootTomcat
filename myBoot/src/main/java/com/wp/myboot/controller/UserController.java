package com.wp.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.wp.myboot.service.Mp4Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value="/userController")
public class UserController {

    @Autowired
    private Mp4Service mp4Service;

    @RequestMapping(value="/checkoutUser")
    @ResponseBody
    public JSONObject checkoutUser(String  phoneNumber){
        JSONObject obj=new JSONObject();
        Map<String,String> user=mp4Service.checkoutUser(phoneNumber);
        if(user!=null){
            obj.put("code","200");
            obj.put("message","存在该用户");
        }else{
            obj.put("code","300");
            obj.put("message","请注册小小播");
        }
        return obj;
    }

}