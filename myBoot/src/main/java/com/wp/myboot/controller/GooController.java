package com.wp.myboot.controller;

import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.entity.UserSeNew;
import com.wp.myboot.service.Mp4Service;
import com.wp.myboot.service.UserSeNewService;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/gooController")
public class GooController {

    private final Logger log = LoggerFactory.getLogger(GooController.class);

    @Autowired
    private UserSeNewService userSeNewService;

    @Resource
    private Mp4Service mp4Service;

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

    @RequestMapping(value="/doLogin")
    @ResponseBody
    public Object loginIn(String name,String passwd,String uid,HttpServletRequest request){
        SpringResult result=new SpringResult();
        UserSeNew userSeNew=userSeNewService.findUser(name,passwd,uid);
        return  result;
    }

}
