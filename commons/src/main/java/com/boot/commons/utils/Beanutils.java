package com.boot.commons.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;


public class Beanutils {

    public static void main(String[] args) throws  Exception{
        Person p=new Person();
        p.setName("wangpeng");
        p.setPass(123);
        try {
            Person pp=(Person)BeanUtils.cloneBean(p);
            System.out.println(pp.getName()+"--"+pp.getPass());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*map转实体类*/
        Map map = new HashMap();
        map.put("name","tom");
        map.put("pass","123");

        Person person = new Person();
        BeanUtils.populate(person,map);
        System.out.println(person.getName()+"--"+person.getPass());

        /*实体类转对象*/
        Map<String, String> maps=BeanUtils.describe(person);
        for (String m:maps.keySet()){
            System.out.println(maps.get(m));
        }

    }
}

