package com.wp.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.wp.myboot.entity.TestUser;
import com.wp.myboot.service.TestBootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/testBootController")
public class TestBootController {

    private final Logger log = LoggerFactory.getLogger(TestBootController.class);

    @Autowired
    private TestBootService testBootService;

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public  List<TestUser> home(HttpSession session) {
        log.info("testBootController-home-start");
        log.error("testBootController-home-start");
        session.setAttribute("sessionTest","sessionTest");
        List<TestUser> listUser= testBootService.selectALL();
        log.info("testBootController-home-end");
        log.error("testBootController-home-end");
        log.info(session.getId());
        log.info(session.getAttribute("sessionTest").toString());
        return listUser;
    }

    /**
     * 创建用户
     * @return
     */
    @RequestMapping("/createUser")
    @ResponseBody
    public JSONObject createUser() {
        log.info("--------------------createUser---------------");
        JSONObject obj=new JSONObject();
        TestUser userTemp=new TestUser();
        userTemp.setName("张三");
        userTemp.setPassword("zhangsan");
        Integer listUser= testBootService.createUser(userTemp);
        obj.put("res",listUser);
        return obj;
    }

    /**
     * 获取用户信息 存到redis中
     * @param session
     * @return
     */
//    @RequestMapping("/saveUser")
//    @ResponseBody
//    @Cacheable(value = "user",key = "123456")
//    public  List<TestUser> saveUser(HttpSession session) {
//        log.info("--------------------saveUser---------------");
//        List<TestUser> listUser= testBootService.selectALL();
//        return listUser;
//    }

}
