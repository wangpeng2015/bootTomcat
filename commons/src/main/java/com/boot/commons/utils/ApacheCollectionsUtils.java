package com.boot.commons.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class ApacheCollectionsUtils {

    public static void main(String[] args) {
          List<String> list = Lists.newArrayList();
          list.add("123");
          list.add("456");
          System.out.println(list.toString());
          String str1=(String) CollectionUtils.get(list,0);
          String str=(String) CollectionUtils.get(list,1);
          System.out.println(str1+"-"+str);


        List<String> list1 = Lists.newArrayList();
        list1.add("123");

        List<String> res = Lists.newArrayList();
        res=(List<String>) CollectionUtils.removeAll(list,list1);
        System.out.println(res.toString());
    }

}
